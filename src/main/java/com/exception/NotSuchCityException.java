package com.exception;

public class NotSuchCityException extends Exception {

	private static final long serialVersionUID = 1L;

	public NotSuchCityException() {
		super("The city name you specified is not a valid city name nor a country name.");
	}

}
