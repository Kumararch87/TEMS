package in.creditmantri.temd.core;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import in.creditmantri.temd.common.NumberUtils;
import in.creditmantri.temd.interfaces.IExpenseCalculator;
import in.creditmantri.temd.interfaces.ITransactionDetail;

public class ExpenseManagerTest {

	ExpenseManager expenseManager;
	IExpenseCalculator expenseCalculator;
	List<ITransactionDetail> transactionList;

	@Before
	public void setUp() throws Exception {
		expenseCalculator =  new ExpenseCalculator();
		transactionList = new ArrayList<>();
		transactionList.add(new TransactionDetail("KUMAR",BigDecimal.valueOf(500),"breakfast",Arrays.asList("RAJA","RAM","YUVA")));
		transactionList.add(new TransactionDetail("KUMAR",BigDecimal.valueOf(700),"lunch",Arrays.asList("KUMAR","RAJA","RAM","YUVA")));
		transactionList.add(new TransactionDetail("KUMAR",BigDecimal.valueOf(900),"cab",Arrays.asList("RAJA","RAM","YUVA")));
		transactionList.add(new TransactionDetail("RAJA",BigDecimal.valueOf(800),"breakfast",Arrays.asList("KUMAR","RAJA","RAM","YUVA")));
		transactionList.add(new TransactionDetail("RAM",BigDecimal.valueOf(1500),"lunch",Arrays.asList("KUMAR","RAM","YUVA")));
		expenseManager = new ExpenseManager(transactionList);
		expenseManager.setExpenseCalculator(expenseCalculator);
	}

	@Test
	public void testReport() {
		expenseManager.report();
	}

	@Test
	public void testTotalExpense() {
		Assert.assertEquals(NumberUtils.getRoundOffAmount(BigDecimal.valueOf(4400.00)), expenseManager.getTotalExpense());
	}
}
