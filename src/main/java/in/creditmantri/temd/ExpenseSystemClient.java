package in.creditmantri.temd;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import in.creditmantri.temd.core.ExpenseCalculator;
import in.creditmantri.temd.core.ExpenseManager;
import in.creditmantri.temd.core.ExpenseValidator;
import in.creditmantri.temd.core.TransactionDetail;
import in.creditmantri.temd.interfaces.IExpenseValidator;
import in.creditmantri.temd.interfaces.ITransactionDetail;
import in.creditmantri.temd.interfaces.TEMSValidationException;

public class ExpenseSystemClient {

	public static void main(final String[] args) throws TEMSValidationException {
		final List<ITransactionDetail> transactionDetails = getExpenseInput();
		final ExpenseManager expenseManager = new ExpenseManager(transactionDetails);
		expenseManager.setExpenseCalculator(new ExpenseCalculator());
		expenseManager.report();
	}

	private static List<ITransactionDetail> getExpenseInput() throws TEMSValidationException {
		final List<ITransactionDetail> transactionDetails = new ArrayList<>();
		final Scanner scanner = new Scanner(System.in);
		while(true)
		{
			System.out.print("Enter payer name: ");
			final String payerName = scanner.next();
			System.out.print("Enter spend amount : ");
			final BigDecimal amount = scanner.nextBigDecimal();
			System.out.print("Enter purpose : ");
			final String purpose = scanner.next();

			System.out
			.print("Enter payee list (this  (KUMAR,RAJA,RAM) format ) Note : Include your name if you also use : ");
			final String payeeList = scanner.next();

			transactionDetails.add(constructTransaction(payerName, amount, purpose, payeeList));
			System.out.print("Do you want enter next transaction (Y/N): ");
			final String isNext = scanner.next();
			if(isNext.equalsIgnoreCase("N"))
			{
				break;
			}

		}
		return transactionDetails;
	}


	private static ITransactionDetail constructTransaction(final String payerName, final BigDecimal amount,
			final String purpose, final String payeeList) throws TEMSValidationException {
		final IExpenseValidator validator = new ExpenseValidator();
		validator.validate(payerName, amount, purpose, payeeList);
		return new TransactionDetail(payerName, amount, purpose, Arrays.asList(payeeList.split(",")));
	}
}
