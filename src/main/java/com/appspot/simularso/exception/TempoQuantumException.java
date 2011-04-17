package com.appspot.simularso.exception;

public class TempoQuantumException extends RuntimeException {

	private static final long serialVersionUID = 551823721765611999L;

	public TempoQuantumException() {
		super();
	}

	public TempoQuantumException(String msg) {
		super(msg);
	}

	public TempoQuantumException(Throwable cause) {
		super(cause);
	}

	public TempoQuantumException(String msg, Throwable cause) {
		super(msg, cause);
	}
}