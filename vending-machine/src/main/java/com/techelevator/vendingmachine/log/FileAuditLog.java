package com.techelevator.vendingmachine.log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.techelevator.MoneyFormatter;

public class FileAuditLog implements Auditable {
	private final File inputFile;
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm a");
	private static final String FEED_MONEY = "FEED MONEY";
	private static final String GIVE_CHANGE = "GIVE CHANGE";
	
	
	public FileAuditLog(File inputFile) {
		this.inputFile = inputFile;
		
		// Create from scratch
		if(this.inputFile.exists()) {
			this.inputFile.delete();
		}
	}

	/**
	 * Log to a file that money was successfully fed.
	 * @param amountFed The amount of money that was fed.
	 * @param balance The balance after feeding money.
	 */
	@Override
	public void logMoneyFed(BigDecimal amountFed, BigDecimal balance) {
        log(FEED_MONEY, amountFed, balance);
    }

	/**
	 * Log to a file that an item was successfully purchased.
	 * @param itemPurchased The item that was purchased.
	 * @param priorBalance The balance prior to purchase.
	 * @param postBalance The balance after purchase.
	 */
	@Override
	public void logPurchaseMade(String itemPurchased, BigDecimal priorBalance, BigDecimal postBalance) {
		log(itemPurchased, priorBalance, postBalance);
	}

	/**
	 * Log to a file that a transaction was successfully completed.
	 * @param priorBalance The balance prior to finishing the transaction.
	 * @param postBalance The balance after finishing the transaction.
	 */
	@Override
	public void logTransactionFinished(BigDecimal priorBalance, BigDecimal postBalance) {
		log(GIVE_CHANGE, priorBalance, postBalance);
	}
	
	private void log(String action, BigDecimal priorBalance, BigDecimal postBalance) {
		
		try (PrintWriter writer = new PrintWriter(new FileWriter(inputFile, true))) {
			String formattedDate = LocalDateTime.now().format(formatter);
			String priorBalanceFormatted = MoneyFormatter.formatMoney(priorBalance);
			String postBalanceFormatted = MoneyFormatter.formatMoney(postBalance);
			
			writer.println(formattedDate + " " + action + " " + priorBalanceFormatted + " " + postBalanceFormatted);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
}
