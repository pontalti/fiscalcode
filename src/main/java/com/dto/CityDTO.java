package com.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CityDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;
	private String prov;
	private String code;
	
	public CityDTO() {
		super();
		this.name = "";
		this.prov = "";
		this.code = "";
	}

}