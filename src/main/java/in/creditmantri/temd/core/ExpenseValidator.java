package in.creditmantri.temd.core;

import java.math.BigDecimal;

import in.creditmantri.temd.common.NumberUtils;
import in.creditmantri.temd.common.StringUtils;
import in.creditmantri.temd.interfaces.IExpenseValidator;
import in.creditmantri.temd.interfaces.TEMSValidationException;

public class ExpenseValidator implements IExpenseValidator {

	@Override
	public void validate(final String payerName, final BigDecimal amount, final String purpose,
			final String payeeList) throws TEMSValidationException {

		if (StringUtils.isEmpty(payerName)) {
			throw new TEMSValidationException("Payer Name is mandatory");
		}

		if (!NumberUtils.isValidNumber(amount)) {
			throw new TEMSValidationException(" Enter valid amount");
		}

	}

}
