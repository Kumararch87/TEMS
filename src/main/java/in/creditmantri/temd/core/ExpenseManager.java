package in.creditmantri.temd.core;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import in.creditmantri.temd.common.NumberUtils;
import in.creditmantri.temd.interfaces.IExpenseCalculator;
import in.creditmantri.temd.interfaces.IExpenseManager;
import in.creditmantri.temd.interfaces.ITransactionDetail;

public class ExpenseManager implements IExpenseManager {

	private final List<ITransactionDetail> transactionDetails;
	private IExpenseCalculator expenseCalculator;

	public void setExpenseCalculator(final IExpenseCalculator expenseCalculator) {
		this.expenseCalculator = expenseCalculator;
	}

	public ExpenseManager(final List<ITransactionDetail> transactionDetails) {
		this.transactionDetails = transactionDetails;
	}

	@Override
	public BigDecimal getTotalExpense() {
		BigDecimal totalExpense = BigDecimal.ZERO;
		for (final ITransactionDetail transaction : transactionDetails) {
			totalExpense = totalExpense.add(transaction.getSpendAmount());
		}
		return NumberUtils.getRoundOffAmount(totalExpense);
	}

	@Override
	public void report() {
		System.out.println(
				"***********************************************************************************************");
		System.out.println(
				"***********************************Expense Report *********************************************");
		System.out.println(
				"***********************************************************************************************");
		final Map<String, BigDecimal> shareExpenseMap = expenseCalculator.getShareExpense(transactionDetails);
		for (final Map.Entry<String, BigDecimal> entry : shareExpenseMap.entrySet()) {
			final StringBuilder statement = new StringBuilder();
			final BigDecimal totalSpendAmount = expenseCalculator.getPayerSpendAmount(transactionDetails,
					entry.getKey());
			statement.append(entry.getKey()).append(" is Spends : ");
			statement.append(totalSpendAmount).append(" - Share Amount  : ");
			statement.append(entry.getValue());
			final BigDecimal difference = totalSpendAmount.subtract(entry.getValue());
			if (difference.compareTo(BigDecimal.ZERO) > 0) {
				statement.append(" - Return Amount :  ").append(difference);
			} else {
				statement.append(" - To be pay Amount :  ").append(difference.abs());
			}
			System.out.println(statement.toString());
		}
		excludeSpendReport(shareExpenseMap);
		System.out.println(
				"***********************************************************************************************");
	}

	private void excludeSpendReport(final Map<String, BigDecimal> shareExpenseMap) {
		int countMemeber = 0;
		String excludeMemberName = "";
		for (final ITransactionDetail transaction : transactionDetails) {
			if (shareExpenseMap.containsKey(transaction.getPayerName())) {
				countMemeber++;
			} else {
				excludeMemberName = transaction.getPayerName();
			}
		}
		if (countMemeber == 0) {
			final BigDecimal totalSpendAmount = expenseCalculator.getPayerSpendAmount(transactionDetails,
					excludeMemberName);
			final StringBuilder statement = new StringBuilder();
			statement.append(excludeMemberName).append(" is Spends : ");
			statement.append(totalSpendAmount).append(" - Share Amount  : ");
			statement.append(0);
			statement.append(" - Return Amount :  ").append(totalSpendAmount);
			System.out.println(statement.toString());

		}

	}

}
