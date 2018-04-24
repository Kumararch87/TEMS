package in.creditmantri.temd.core;

import java.math.BigDecimal;
import java.util.List;

import in.creditmantri.temd.interfaces.ITransactionDetail;

public class TransactionDetail implements ITransactionDetail {

	private String payerName;
	private BigDecimal spendAmount;
	private String purpose;
	private List<String> toPayeeList;


	public TransactionDetail( final String payerName, final BigDecimal spendAmount, final String purpose,
			final List<String> toPayeeList) {
		super();
		this.payerName = payerName;
		this.spendAmount = spendAmount;
		this.purpose = purpose;
		this.toPayeeList = toPayeeList;
	}

	public boolean isPayerAlsoPayee() {
		return toPayeeList.stream().anyMatch(payee -> payee.equals(payerName));
	}

	@Override
	public String getPayerName() {
		return payerName;
	}

	public void setPayerName(final String payerName) {
		this.payerName = payerName;
	}

	@Override
	public BigDecimal getSpendAmount() {
		return spendAmount;
	}

	public void setSpendAmount(final BigDecimal spendAmount) {
		this.spendAmount = spendAmount;
	}

	@Override
	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(final String purpose) {
		this.purpose = purpose;
	}

	@Override
	public List<String> getToPayeeList() {
		return toPayeeList;
	}

	public void setToPayeeList(final List<String> toPayeeList) {
		this.toPayeeList = toPayeeList;
	}

}
