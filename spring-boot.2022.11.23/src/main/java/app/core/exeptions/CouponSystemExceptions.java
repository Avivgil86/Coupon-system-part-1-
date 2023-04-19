package app.core.exeptions;

public class CouponSystemExceptions extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CouponSystemExceptions() {
	}

	public CouponSystemExceptions(String message) {
		super(message);
	}

	public CouponSystemExceptions(Throwable cause) {
		super(cause);
	}

	public CouponSystemExceptions(String message, Throwable cause) {
		super(message, cause);
	}

	public CouponSystemExceptions(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
