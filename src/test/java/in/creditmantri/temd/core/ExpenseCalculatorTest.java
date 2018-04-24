package in.creditmantri.temd.core;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import in.creditmantri.temd.common.NumberUtils;
import in.creditmantri.temd.interfaces.IExpenseCalculator;
import in.creditmantri.temd.interfaces.ITransactionDetail;
import junit.framework.Assert;

public class ExpenseCalculatorTest{

	IExpenseCalculator expenseCalculator;
	List<ITransactionDetail> transactionList;

	@Before
	public void preSetUp()
	{
		expenseCalculator =  new ExpenseCalculator();
		transactionList = new ArrayList<>();
		transactionList.add(new TransactionDetail("KUMAR",BigDecimal.valueOf(500),"breakfast",Arrays.asList("RAJA","RAM","YUVA")));
		transactionList.add(new TransactionDetail("KUMAR",BigDecimal.valueOf(700),"lunch",Arrays.asList("KUMAR","RAJA","RAM","YUVA")));
		transactionList.add(new TransactionDetail("KUMAR",BigDecimal.valueOf(900),"cab",Arrays.asList("RAJA","RAM","YUVA")));
		transactionList.add(new TransactionDetail("RAJA",BigDecimal.valueOf(800),"breakfast",Arrays.asList("KUMAR","RAJA","RAM","YUVA")));
		transactionList.add(new TransactionDetail("RAM",BigDecimal.valueOf(1500),"lunch",Arrays.asList("KUMAR","RAM","YUVA")));

	}

	@Test
	public void testTotalPaidBasedOnPayer()
	{
		Assert.assertEquals(NumberUtils.getRoundOffAmount(BigDecimal.valueOf(2100)), expenseCalculator.getPayerSpendAmount(transactionList, "KUMAR"));
		Assert.assertEquals(NumberUtils.getRoundOffAmount(BigDecimal.valueOf(800)), expenseCalculator.getPayerSpendAmount(transactionList, "RAJA"));
		Assert.assertEquals(NumberUtils.getRoundOffAmount(BigDecimal.valueOf(1500)), expenseCalculator.getPayerSpendAmount(transactionList, "RAM"));
		Assert.assertEquals(NumberUtils.getRoundOffAmount(BigDecimal.valueOf(0)), expenseCalculator.getPayerSpendAmount(transactionList, "YUVA"));
	}

	@Test
	public void testExpensForEveryOne()
	{
		final Map<String, BigDecimal> shareAmount =expenseCalculator.getShareExpense(transactionList);
		Assert.assertEquals(NumberUtils.getRoundOffAmount(BigDecimal.valueOf(841.67)), shareAmount.get("RAJA"));
		Assert.assertEquals(NumberUtils.getRoundOffAmount(BigDecimal.valueOf(875)), shareAmount.get("KUMAR"));
		Assert.assertEquals(NumberUtils.getRoundOffAmount(BigDecimal.valueOf(1341.67)), shareAmount.get("RAM"));
		Assert.assertEquals(NumberUtils.getRoundOffAmount(BigDecimal.valueOf(1341.67)), shareAmount.get("YUVA"));
	}


}
