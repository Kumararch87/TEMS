package in.creditmantri.temd.interfaces;

public class TEMSValidationException extends Exception {

	private String errorMsg;
	private String errorCode;

	public String getErrorCode() {
		return errorCode;
	}

	public TEMSValidationException() {
		super();
	}

	public TEMSValidationException(final String errorMsg) {
		super();
		this.errorMsg = errorMsg;
	}
	public TEMSValidationException(final String errorMsg,final String errorCode) {
		super();
		this.errorMsg = errorMsg;
		this.errorCode = errorCode;
	}
	public String getErrorMsg() {
		return errorMsg;
	}

}
