package com.jelena.exceptions;

public class NoImageReaderException extends Exception {
	
	private static final long serialVersionUID = -8491139391463959880L;

	public NoImageReaderException() {
		super();		
	}
	
	public NoImageReaderException (String message) {
		super (message);
	}
}
