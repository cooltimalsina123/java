package com.techelevator;


import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MoneyFormatterTests {

	@Test
	public void formatMoney_returns_formatted_result() {
		// Act
		String actual = MoneyFormatter.formatMoney(BigDecimal.ONE);
		
		// Assert
		Assert.assertEquals("$1.00", actual);
	}
	
	@Test
	public void removeCurrencySymbol_null_amount_returns_empty() {
		// Act
		String actual = MoneyFormatter.removeCurrencySymbol(null);
		
		// Assert
		Assert.assertEquals("", actual);
	}
	
	@Test
	public void removeCurrencySymbol_empty_amount_returns_empty() {
		// Act
		String actual = MoneyFormatter.removeCurrencySymbol("");
		
		// Assert
		Assert.assertEquals("", actual);
	}
	
	@Test
	public void removeCurrencySymbol_no_symbol_returns_same() {
		// Arrange
		String amount = "1.00";
		
		// Act
		String actual = MoneyFormatter.removeCurrencySymbol(amount);
		
		// Assert
		Assert.assertEquals(amount, actual);
	}
	
	@Test
	public void removeCurrencySymbol_returns_valid_amount() {
		// Arrange
		
		// Act
		String actual = MoneyFormatter.removeCurrencySymbol("$1.00");
		
		// Assert
		Assert.assertEquals("1.00", actual);
	}
}
