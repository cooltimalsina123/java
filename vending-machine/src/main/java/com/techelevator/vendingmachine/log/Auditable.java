package com.techelevator.vendingmachine.log;

import java.math.BigDecimal;

public interface Auditable {

	/**
	 * Log that money was successfully fed.
	 * @param amountFed The amount of money that was fed.
	 * @param balance The balance after feeding money.
	 */
	void logMoneyFed(BigDecimal amountFed, BigDecimal balance);

	/**
	 * Log that an item was successfully purchased.
	 * @param itemPurchased The item that was purchased.
	 * @param priorBalance The balance prior to purchase.
	 * @param postBalance The balance after purchase.
	 */
	void logPurchaseMade(String itemPurchased, BigDecimal priorBalance, BigDecimal postBalance);

	/**
	 * Log that a transaction was successfully completed.
	 * @param priorBalance The balance prior to finishing the transaction.
	 * @param postBalance The balance after finishing the transaction.
	 */
	void logTransactionFinished(BigDecimal priorBalance, BigDecimal postBalance);
}
