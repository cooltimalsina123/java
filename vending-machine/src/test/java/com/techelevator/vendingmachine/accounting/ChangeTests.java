package com.techelevator.vendingmachine.accounting;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ChangeTests {
	
	@Test(expected = IllegalArgumentException.class)
	public void quarters_less_than_zero_throws_exception() {
		// Act
		new Change(-1, 0, 0, 0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void dimes_less_than_zero_throws_exception() {
		// Act
		new Change(0, -1, 0, 0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nickels_less_than_zero_throws_exception() {
		// Act
		new Change(0, 0, -1, 0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void pennies_less_than_zero_throws_exception() {
		// Act
		new Change(0, 0, 0, -1);
	}
	
	@Test
	public void toString_returns_no_change() {
		// Arrange
		Change change = new Change(0, 0, 0, 0);
		
		// Act
		String changeString = change.toString();
		
		// Assert
		Assert.assertEquals("No change", changeString);
	}
	
	@Test
	public void toString_prints_singular_change() {
		// Arrange
		Change change = new Change(1, 1, 1, 1);
		
		// Act
		String changeString = change.toString();
		
		// Assert
		Assert.assertEquals("1 quarter 1 dime 1 nickel 1 penny", changeString);
	}
	
	@Test
	public void toString_prints_plural_change() {
		// Arrange
		Change change = new Change(2, 2, 2, 2);
		
		// Act
		String changeString = change.toString();
		
		// Assert
		Assert.assertEquals("2 quarters 2 dimes 2 nickels 2 pennies", changeString);
	}
}
