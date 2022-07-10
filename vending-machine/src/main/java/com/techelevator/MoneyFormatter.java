package com.techelevator;

import java.math.BigDecimal;
import java.text.NumberFormat;

public class MoneyFormatter {

	/**
	 * Format money according to local currency standards e.g. $1.00 for USD.
	 * 
	 * @param amount The amount of money.
	 * @return The String representation for the amount of money.
	 */
	public static String formatMoney(BigDecimal amount) {
		return NumberFormat.getCurrencyInstance().format(amount);
	}

	/**
	 * Remove local currency symbol from a String if it exists. For example $1.00
	 * will return 1.00.
	 * 
	 * @param currencyString The String containing the local currency
	 *                       representation.
	 * @return The String without the local currency symbol.
	 */
	public static String removeCurrencySymbol(String currencyString) {
		if (currencyString == null || currencyString.length() == 0) {
			return "";
		}

		String symbol = NumberFormat.getCurrencyInstance().getCurrency().getSymbol();
		if (currencyString.startsWith(symbol)) {
			return currencyString.substring(symbol.length());
		}

		return currencyString;
	}
}
