package appspot.simulaedp.exception;

public class NegativoQuantumException extends RuntimeException {

	private static final long serialVersionUID = 551823721765611999L;

	public NegativoQuantumException() {
		super();
	}

	public NegativoQuantumException(String msg) {
		super(msg);
	}

	public NegativoQuantumException(Throwable cause) {
		super(cause);
	}

	public NegativoQuantumException(String msg, Throwable cause) {
		super(msg, cause);
	}
}