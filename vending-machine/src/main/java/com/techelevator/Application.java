package com.techelevator;

import java.io.File;

import com.techelevator.vendingmachine.VendingMachine;
import com.techelevator.vendingmachine.accounting.CashAccount;
import com.techelevator.vendingmachine.accounting.ChangeMaker;
import com.techelevator.vendingmachine.accounting.MinimumChangeMaker;
import com.techelevator.vendingmachine.inventory.FileInventory;
import com.techelevator.vendingmachine.inventory.Inventory;
import com.techelevator.vendingmachine.log.Auditable;
import com.techelevator.vendingmachine.log.FileAuditLog;
import com.techelevator.vendingmachine.view.PurchaseMenuCLI;
import com.techelevator.vendingmachine.view.VendingMachineCLI;
import com.techelevator.view.Menu;

public class Application {

	public static void main(String[] args) {	
		Inventory inventoryDao = new FileInventory(new File("vendingmachine.csv"));
		
		ChangeMaker changeMaker = new MinimumChangeMaker();
		CashAccount account = new CashAccount(changeMaker);

		Auditable auditLog = new FileAuditLog(new File("Log.txt"));
		
		VendingMachine vendingMachine = new VendingMachine(inventoryDao, account, auditLog);

		// Create CLIs
		Menu menu = new Menu(System.in, System.out);
		PurchaseMenuCLI purchaseMenuCLI = new PurchaseMenuCLI(menu, vendingMachine);
		VendingMachineCLI cli = new VendingMachineCLI(menu, purchaseMenuCLI, vendingMachine);

		// Begin
		cli.run();
	}

}
