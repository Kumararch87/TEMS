package in.creditmantri.temd.common;

public class StringUtils {

	public static boolean isEmpty(final String value) {
		boolean isValueEmpty = false;
		if (value == null || value.trim() == "") {
			isValueEmpty = true;
		}
		return isValueEmpty;
	}

	public static boolean isNotEmpty(final String value) {
		return !isEmpty(value);
	}

}
