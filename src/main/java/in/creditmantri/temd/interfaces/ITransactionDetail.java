package in.creditmantri.temd.interfaces;

import java.math.BigDecimal;
import java.util.List;

public interface ITransactionDetail {


	public String getPayerName();

	public BigDecimal getSpendAmount();

	public String getPurpose();

	public List<String> getToPayeeList();

}
