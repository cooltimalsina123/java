package com.techelevator.vendingmachine;

import java.math.BigDecimal;

import com.techelevator.snack.Snack;
import com.techelevator.vendingmachine.accounting.Accountable;
import com.techelevator.vendingmachine.accounting.Change;
import com.techelevator.vendingmachine.accounting.InsufficientFundsException;
import com.techelevator.vendingmachine.exception.InvalidSlotLocationException;
import com.techelevator.vendingmachine.exception.SoldOutException;
import com.techelevator.vendingmachine.inventory.Inventory;
import com.techelevator.vendingmachine.inventory.Slot;
import com.techelevator.vendingmachine.log.Auditable;

public class VendingMachine {
	private final Inventory inventoryDao;
	private final Accountable account;
	private final Auditable auditLog;

	/**
	 * Get the balance remaining.
	 *
	 * @return The balance remaining.
	 */
	public BigDecimal getBalance() {
		return account.getBalance();
	}

	public VendingMachine(Inventory inventoryDao, Accountable account, Auditable auditLog) {
		this.inventoryDao = inventoryDao;
		inventoryDao.restock();
		
		this.account = account;
		this.auditLog = auditLog;
	}

	@Override
	public String toString() {
		StringBuilder displayString = new StringBuilder();
		for (String slotName : inventoryDao.getInventory().keySet()) {
			Slot currSlot = inventoryDao.getInventory().get(slotName);

			displayString.append(currSlot.toString()).append(System.lineSeparator());
		}

		return displayString.toString();
	}

	/**
	 * Attempt to feed money into the machine.
	 * 
	 * @param amount The amount of money to feed.
	 * @return True if money was successfully added. False otherwise.
	 */
	public boolean feedMoney(BigDecimal amount) {
		boolean didDeposit = account.deposit(amount);

		if(didDeposit) auditLog.logMoneyFed(amount, account.getBalance());

		return didDeposit;
	}

	/**
	 * Purchase an item from a slot within the machine.
	 * 
	 * @param slotName The name of the slot to purchase from.
	 * @return Details about the purchase
	 */
	public Snack purchaseItem(String slotName) {
		if (slotName == null || !inventoryDao.getInventory().containsKey(slotName)) {
			throw new InvalidSlotLocationException(slotName + " is invalid");
		}

		Slot slot = inventoryDao.getInventory().get(slotName);

		if (slot.getQuantity() == 0) {
			throw new SoldOutException();
		}

		if (account.getBalance().compareTo(slot.getPrice()) < 0) {
			throw new InsufficientFundsException();
		}

		Snack item = slot.vend();

		BigDecimal balancePrior = account.getBalance();
		account.withdraw(item.getPrice());

		auditLog.logPurchaseMade(slotName + " " + item.getName(), balancePrior, account.getBalance());

		return item;
	}

	/**
	 * Finish the transaction and return change to the consumer.
	 * 
	 * @return The amount of change the consumer is owed using the fewest coins
	 *         possible.
	 */
	public Change finishTransaction() {
		BigDecimal priorBalance = account.getBalance();

		Change change = account.refundBalance();

		auditLog.logTransactionFinished(priorBalance, BigDecimal.ZERO);

		return change;
	}
}
