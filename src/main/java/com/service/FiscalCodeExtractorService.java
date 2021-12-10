package com.service;

import java.time.Month;
import java.util.Calendar;

import com.dto.ClientDTO;

@FunctionalInterface
public interface FiscalCodeExtractorService {

	public ClientDTO reverseFC(String fiscalCode);
	
	static Month extractMonth(String fiscalCode) {
		Month month = null;
		char m = fiscalCode.substring(8, 9).toLowerCase().charAt(0);
		switch (m) {
			case 'a':
				month = Month.JANUARY;
				break;
			case 'b':
				month = Month.FEBRUARY;
				break;
			case 'c':
				month = Month.MARCH;
				break;
			case 'd':
				month = Month.APRIL;
				break;
			case 'e':
				month = Month.MAY;
				break;
			case 'h':
				month = Month.JUNE;
				break;
			case 'l':
				month = Month.JULY;
				break;
			case 'm':
				month = Month.AUGUST;
				break;
			case 'p':
				month = Month.SEPTEMBER;
				break;
			case 'r':
				month = Month.OCTOBER;
				break;
			case 's':
				month = Month.NOVEMBER;
				break;
			case 't':
				month = Month.DECEMBER;
				break;
		}
		return month;
	}

	static int extractYear(String fiscalCode) {
		int theYear = 0;
		int thisYear = Integer.parseInt(String.valueOf(Calendar.getInstance().get(Calendar.YEAR)).substring(2, 4));
		String yy = fiscalCode.substring(6, 8);
		int y = Integer.parseInt(yy);
		if (y >= thisYear) {
			theYear = 1900 + y;
		} else {
			theYear = 2000 + y;
		}
		return theYear;
	}
	
}
