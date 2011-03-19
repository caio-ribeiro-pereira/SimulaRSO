package appspot.simularso.exception;

public class ProcessosNaoCarregadosException extends RuntimeException {

	private static final long serialVersionUID = 3195857234974113435L;

	public ProcessosNaoCarregadosException() {
		super();
	}

	public ProcessosNaoCarregadosException(String msg) {
		super(msg);
	}

	public ProcessosNaoCarregadosException(Throwable cause) {
		super(cause);
	}

	public ProcessosNaoCarregadosException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
