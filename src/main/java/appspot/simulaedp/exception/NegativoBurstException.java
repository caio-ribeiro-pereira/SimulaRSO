package appspot.simulaedp.exception;

public class NegativoBurstException extends RuntimeException {

	private static final long serialVersionUID = -8101197800564751304L;

	public NegativoBurstException() {
		super();
	}

	public NegativoBurstException(String msg) {
		super(msg);
	}

	public NegativoBurstException(Throwable cause) {
		super(cause);
	}

	public NegativoBurstException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
