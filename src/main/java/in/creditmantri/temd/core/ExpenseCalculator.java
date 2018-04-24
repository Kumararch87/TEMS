package in.creditmantri.temd.core;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import in.creditmantri.temd.common.NumberUtils;
import in.creditmantri.temd.common.StringUtils;
import in.creditmantri.temd.interfaces.IExpenseCalculator;
import in.creditmantri.temd.interfaces.ITransactionDetail;

public class ExpenseCalculator implements IExpenseCalculator {

	@Override
	public BigDecimal getPayerSpendAmount(final List<ITransactionDetail> transactionDetails, final String payerName) {
		BigDecimal sendAmount = BigDecimal.ZERO;
		for (final ITransactionDetail transactionDetail : transactionDetails) {
			if (StringUtils.isNotEmpty(payerName) && payerName.equals(transactionDetail.getPayerName())) {
				sendAmount=sendAmount.add(transactionDetail.getSpendAmount());
			}
		}
		return NumberUtils.getRoundOffAmount(sendAmount);
	}

	@Override
	public Map<String, BigDecimal> getShareExpense(final List<ITransactionDetail> transactionDetails) {
		final Map<String, BigDecimal> shareExpenseMap = new HashMap<>();
		for (final ITransactionDetail transactionDetail : transactionDetails) {
			final int sharingCount = transactionDetail.getToPayeeList().size();
			final BigDecimal shareAmount = transactionDetail.getSpendAmount().divide(BigDecimal.valueOf(sharingCount), 2, RoundingMode.CEILING);;
			for (final String payee : transactionDetail.getToPayeeList()) {
				{
					if (shareExpenseMap.containsKey(payee)) {
						final BigDecimal tmpAmount = shareExpenseMap.get(payee).add(shareAmount);
						shareExpenseMap.put(payee,tmpAmount );
					} else {
						shareExpenseMap.put(payee, shareAmount);

					}

				}

			}
		}
		return shareExpenseMap;
	}
}
