package com.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
@Entity
@Table(name = "CITY")
public class City {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "NAME", length = 128)
	private String name;

	@Column(name = "PROV", length = 128)
	private String prov;
	
	@Column(name = "CODE", length = 128)
	private String code;
	
	public City() {
		super();
	}
	
}