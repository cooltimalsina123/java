package com.techelevator.vendingmachine.accounting;

import java.math.BigDecimal;

public interface ChangeMaker {
	/**
	 * Get Change for a given amount.
	 * @param amount The amount to get Change for.
	 * @return The Change.
	 */
	Change getChangeFor(BigDecimal amount);
}
