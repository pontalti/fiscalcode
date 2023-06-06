package com;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.util.Optional;

import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.dto.ClientDTO;
import com.dto.Gender;
import com.dto.TaxCodeDTO;
import com.exception.NotSuchCityException;
import com.repository.ItalyRepository;
import com.service.FiscalCodeCalculatorService;
import com.service.FiscalCodeExtractorService;
import com.util.Util;


@SpringBootTest(webEnvironment = WebEnvironment.MOCK, classes = FiscalCodeApplication.class)
@AutoConfigureMockMvc
public class TestFiscalCodeApplication {

	private static final String DISCOVER_TAX_CODE_DETAILS_PATH 	= "/discoverTaxCodeDetails";
	private static final String DISCOVER_TAX_CODE_PATH 			= "/discoverTaxCode";

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	private ItalyRepository repository;
	
	@Autowired
	private FiscalCodeExtractorService fceService;

	@Autowired
	private FiscalCodeCalculatorService fccService;
	
	@Before
	public void setUp() {
		this.mockMvc = webAppContextSetup(webApplicationContext).build();
	}

	@ParameterizedTest
	@Tag("Important")
	@DisplayName("fiscal Code Calculator Service Test") 
	@CsvSource({"Edmundo,Souza,27/01/1983,TRENTO,TN,M,SZODND83A27L378E"})
	public void fiscalCodeCalculatorServiceTest(String name, 
												String surname, 
												String dob, 
												String placeOfBirth,
												String state,
												String gender, 
												String taxCode) throws Exception {
		assertThat(this.fccService.calculateFC(	name, 
												surname, 
												gender.charAt(0), 
												Util.convertStringDateToLocalDate(dob), 
												placeOfBirth,
												state))
		.isEqualTo(TaxCodeDTO.builder().taxCode(taxCode).build());
	}
	
	@ParameterizedTest
	@Tag("Important")
	@DisplayName("fiscal Code Calculator Service Failure Test")
	@CsvSource({",,,,,,SZODND83A27L378E"})
	public void fiscalCodeCalculatorServiceFailureTest(	String name, 
														String surname, 
														String dob, 
														String placeOfBirth, 
														String state,
														String gender, 
														String taxCode) throws Exception {
		Assertions.assertThrows(NotSuchCityException.class, () -> {
			this.fccService.calculateFC(	name, 
											surname, 
											null==gender||"".equals(gender)?' ':gender.charAt(0), 
											null==dob?null:Util.convertStringDateToLocalDate(dob), 
											placeOfBirth,
											state);
	  });
	}
	
	@ParameterizedTest
	@Tag("Important")
	@DisplayName("Fiscal Code Extractor Service Test")
	@CsvSource({"SZODND83A27L378E,27/01/1983,TRENTO,TN,M"})
	public void fiscalCodeExtractorServiceTest(	String taxCode, 
												String dob, 
												String placeOfBirth,
												String state,
												String gender) throws Exception {
		
		

		
		
		assertThat(this.fceService.reverseFC(taxCode))
					.isEqualTo(		
								ClientDTO
									.builder()
									.name(null)
									.surname(null)
									.dateOfBirth(dob)
									.gender(Gender.valueOf(String.valueOf(gender)))
									.state(state)
									.placeOfBirth(placeOfBirth)
									.build());
	}
	
	@ParameterizedTest
	@Tag("Important")
	@DisplayName("Fiscal Code Extractor Service Failure Test")
	@CsvSource({"fedfvadvdaf,27/01/1983,TRENTO,M"})
	public void fiscalCodeExtractorServiceFailureTest(	String taxCode, 
												String dob, 
												String placeOfBirth, 
												String gender) throws Exception {
		
		Assertions.assertThrows(Exception.class, () -> {
			this.fceService.reverseFC(taxCode);
		});
	}
	
	@Test
	@Tag("Important")
	@DisplayName("City Repository Find All Test")
	public void cityRepositoryFindAllTest() {
		assertThat(this.repository.findAll()).isNotNull();
	}
	
	@ParameterizedTest(name = "Searching for ''{0}''")
	@Tag("Important")
	@DisplayName("City Repository Find by city name Test")
	@CsvSource({"TRENTO,TN", "ROMA,rm", "TRENTO,TN", "MILAN,MI"})
	public void cityRepositoryFindByCityNameTest(String name, String state) {
		assertThat(this.repository.findByCityName(name, state)).isNotNull();
	}
	
	@ParameterizedTest(name = "Searching for ''{0}''")
	@Tag("Important")
	@DisplayName("City Repository Find by invalid city name Test")
	@CsvSource({"cadcascsaa,fwdfewfdf"})
	public void cityRepositoryFindByInvalidCityNameTest(String name, String state) {
		assertThat(this.repository.findByCityName(name, state)).isEqualTo(Optional.empty());
	}
	
	@Test
	@Tag("Important")
	@DisplayName("Discover Tax Code Details Failure Test")
	public void discoverTaxCodeDetailsFailureTest() throws Exception {
		this.mockMvc.perform(get(DISCOVER_TAX_CODE_DETAILS_PATH)
					.contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isBadRequest());
	}
	
	@Test
	@Tag("Important")
	@DisplayName("Discover Tax Code Failure Test")
	public void discoverTaxCodeFailureTest() throws Exception {
		this.mockMvc.perform(get(DISCOVER_TAX_CODE_PATH)
					.contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isBadRequest());
	}
	
	@Test
	@Tag("Important")
	@DisplayName("Discover Tax Code Whithout Body Content Failure Test")
	public void discoverTaxCodeWhithoutBodyContentFailureTest() throws Exception {
		this.mockMvc.perform(get(DISCOVER_TAX_CODE_PATH)
					.content(Util.asJsonString(new TaxCodeDTO()))
					.contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isBadRequest());
	}

	@Test
	@Tag("Important")
	@DisplayName("Discover Tax Code Details Whithout Body Content Failure Test")
	public void discoverTaxCodeDetailsWhithoutBodyContentFailureTest() throws Exception {
		this.mockMvc.perform(get(DISCOVER_TAX_CODE_DETAILS_PATH)
					.content(Util.asJsonString(new ClientDTO()))
					.contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isBadRequest());
	}

	@ParameterizedTest
	@Tag("Important")
	@DisplayName("Discover Tax Code Details Test")
	@CsvSource({"SZODND83A27L378E,27/01/1983,TRENTO,TN,M"})
	public void discoverTaxCodeDetailsTest(	String taxCode, 
											String dob, 
											String placeOfBirth,
											String state,
											String gender) throws Exception {
		this.mockMvc.perform(get(DISCOVER_TAX_CODE_DETAILS_PATH)
					.content(Util.asJsonString(new TaxCodeDTO(taxCode)))
					.contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					.andExpect(content()
								.json(Util.asJsonString(
														ClientDTO
															.builder()
															.name(null)
															.surname(null)
															.dateOfBirth(dob)
															.gender(Gender.valueOf(String.valueOf(gender)))
															.state(state)
															.placeOfBirth(placeOfBirth)
															.build())));
	}
	
	@ParameterizedTest
	@Tag("Important")
	@DisplayName("Discover Tax Code Test")
	@CsvSource({"Edmundo,Souza,27/01/1983,TRENTO,TN,M,SZODND83A27L378E"})
	public void discoverTaxCodeTest(String name, 
									String surname, 
									String dob, 
									String placeOfBirth,
									String state,
									String gender,
									String taxCode) throws Exception {
		this.mockMvc.perform(get(DISCOVER_TAX_CODE_PATH)
								.content(Util.asJsonString(
															ClientDTO
																.builder()
																.name(name)
																.surname(surname)
																.dateOfBirth(dob)
																.gender(Gender.valueOf(String.valueOf(gender)))
																.state(state)
																.placeOfBirth(placeOfBirth)
																.build()))
								.contentType(MediaType.APPLICATION_JSON))
								.andExpect(status().isOk())
								.andExpect(content().json(Util.asJsonString(new TaxCodeDTO(taxCode))));
	}
	
}
