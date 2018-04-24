package in.creditmantri.temd.interfaces;

import java.math.BigDecimal;

public interface IExpenseValidator {

	void validate(final String payerName, final BigDecimal amount,
			final String purpose, final String payeeList) throws TEMSValidationException;
}
