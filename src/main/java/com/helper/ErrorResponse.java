package com.helper;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class ErrorResponse {

	private String message;
	private List<String> details;

	public ErrorResponse() {
		super();
	}

}
