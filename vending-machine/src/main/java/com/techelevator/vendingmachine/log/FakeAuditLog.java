package com.techelevator.vendingmachine.log;

import java.math.BigDecimal;

public class FakeAuditLog implements Auditable {
	private int logLines = 0;

	public int getLogLines() {
		return logLines;
	}

	@Override
	public void logMoneyFed(BigDecimal amountFed, BigDecimal balance) {
		logLines++;
	}

	@Override
	public void logPurchaseMade(String itemPurchased, BigDecimal priorBalance, BigDecimal postBalance) {
		logLines++;
	}

	@Override
	public void logTransactionFinished(BigDecimal priorBalance, BigDecimal postBalance) {
		logLines++;
	}
}
