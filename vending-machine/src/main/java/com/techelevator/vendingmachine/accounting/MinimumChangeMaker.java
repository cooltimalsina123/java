package com.techelevator.vendingmachine.accounting;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MinimumChangeMaker implements ChangeMaker {
	private static final BigDecimal QUARTER = BigDecimal.valueOf(.25);
	private static final BigDecimal DIME = BigDecimal.valueOf(.1);
	private static final BigDecimal NICKEL = BigDecimal.valueOf(.05);
	private static final BigDecimal PENNY = BigDecimal.valueOf(.01);

	/**
	 * Get the minimum amount of Change for a given amount.
	 * @param amount The amount to get Change for.
	 * @return The Change.
	 */
	public Change getChangeFor(BigDecimal amount) {
		if (amount.compareTo(BigDecimal.ZERO) < 0) {
			throw new IllegalArgumentException("Invalid amount. Amount must be greater than or equal to 0");
		}

		final int scale = 2;
		final RoundingMode roundingMode = RoundingMode.HALF_UP;

		if (amount.scale() > scale) {
			amount = amount.setScale(scale, roundingMode);
		}
		
		// Quarters
		int quarterCount = amount.divide(QUARTER, scale, roundingMode).intValue();
		amount = amount.remainder(QUARTER);

		// Dimes
		int dimeCount = amount.divide(DIME, scale, roundingMode).intValue();
		amount = amount.remainder(DIME);

		// Nickels
		int nickelCount = amount.divide(NICKEL, scale, roundingMode).intValue();
		amount = amount.remainder(NICKEL);

		// Pennies
		int pennyCount = amount.divide(PENNY, scale, roundingMode).intValue();

		return new Change(quarterCount, dimeCount, nickelCount, pennyCount);
	}
}
