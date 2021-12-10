package com.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dto.TaxCodeDTO;
import com.entity.City;
import com.exception.NotSuchCityException;
import com.repository.CityRepository;

@Service
public class FiscalCodeCalculatorServiceImpl implements FiscalCodeCalculatorService{

	@Autowired
	private CityRepository cityRepository;
	
	public FiscalCodeCalculatorServiceImpl() {
		super();
	}
	
	@Override
	public TaxCodeDTO calculateFC(String name, String surname, char sex, LocalDate dob, String placeOfBirth, String state) throws NotSuchCityException {
		Optional<City> city = this.cityRepository.findByName(placeOfBirth, state);
		if ( city.isEmpty()) {
			throw new NotSuchCityException();
		} else {
			return FiscalCodeCalculatorService.calculateFC(name, surname, sex, dob, city.get());
		}
	}

}