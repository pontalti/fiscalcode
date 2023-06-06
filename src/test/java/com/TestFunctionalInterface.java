package com;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Month;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.junit.jupiter.MockitoExtension;

import com.service.FiscalCodeExtractorService;

@ExtendWith(MockitoExtension.class)
public class TestFunctionalInterface {
	
	@ParameterizedTest
	@DisplayName("Extract month Test")
	@CsvSource({"SZODND83A27L378E,01"})
	public void extractMonth(String fiscalCode, String month) {
		assertEquals(Month.of(Integer.parseInt(month)), FiscalCodeExtractorService.extractMonth(fiscalCode));
	}
	
	@ParameterizedTest
	@DisplayName("Extract year Test")
	@CsvSource({"SZODND83A27L378E,1983"})
	public void extractYear(String fiscalCode, String year) {
		assertEquals(Integer.parseInt(year), FiscalCodeExtractorService.extractYear(fiscalCode));
	}	

}
