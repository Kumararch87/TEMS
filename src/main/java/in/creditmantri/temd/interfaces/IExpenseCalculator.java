package in.creditmantri.temd.interfaces;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface IExpenseCalculator {

	BigDecimal getPayerSpendAmount(List<ITransactionDetail> transactionDetails,final String payerName);

	Map<String,BigDecimal> getShareExpense(List<ITransactionDetail> transactionDetails);

}
