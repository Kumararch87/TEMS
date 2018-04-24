package in.creditmantri.temd.common;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.regex.Pattern;

public class NumberUtils {


	public static BigDecimal getRoundOffAmount(final BigDecimal amount)
	{
		return amount!=null ? amount.setScale(2, RoundingMode.HALF_UP) : BigDecimal.ZERO.setScale(2,RoundingMode.HALF_UP);
	}

	public static boolean isValidNumber(final BigDecimal amount)
	{
		boolean isValid = false;
		final String pattern = "[+-]?[\\d,]+\\.?\\d+";
		if (Pattern.matches(pattern, String.valueOf(amount)))
		{
			isValid = true;
		}
		return isValid;
	}

}
