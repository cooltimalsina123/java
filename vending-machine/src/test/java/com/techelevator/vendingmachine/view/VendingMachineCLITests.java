package com.techelevator.vendingmachine.view;

import com.techelevator.vendingmachine.VendingMachine;
import com.techelevator.vendingmachine.accounting.CashAccount;
import com.techelevator.vendingmachine.accounting.ChangeMaker;
import com.techelevator.vendingmachine.accounting.MinimumChangeMaker;
import com.techelevator.vendingmachine.inventory.FakeInventory;
import com.techelevator.vendingmachine.inventory.Inventory;
import com.techelevator.vendingmachine.inventory.Slot;
import com.techelevator.vendingmachine.log.Auditable;
import com.techelevator.vendingmachine.log.FakeAuditLog;
import com.techelevator.view.Menu;
import org.junit.Assert;
import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import java.io.*;
import java.util.Collections;
import java.util.Map;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class VendingMachineCLITests {

    private final String menuString = System.lineSeparator() +
            "1) Display Vending Machine Items" + System.lineSeparator() +
            "2) Purchase" + System.lineSeparator() +
            "3) Exit" + System.lineSeparator() +
            System.lineSeparator() +
            "Please choose an option >>> ";


    @Test
    public void run_and_exit() {
        Map<String, Slot> fakeInventory = Collections.emptyMap();
        Inventory inventoryDao = new FakeInventory(fakeInventory);

        ChangeMaker changeMaker = new MinimumChangeMaker();
        CashAccount account = new CashAccount(changeMaker);

        Auditable auditLog = new FakeAuditLog();

        VendingMachine vendingMachine = new VendingMachine(inventoryDao, account, auditLog);

        String inputString = "3" + System.lineSeparator();
        ByteArrayInputStream input = new ByteArrayInputStream(inputString.getBytes());
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        PrintStream output = new PrintStream(byteStream);
        System.setOut(output);

        Menu menu = new Menu(input, output);
        PurchaseMenuCLI purchaseMenuCLI = new PurchaseMenuCLI(menu, vendingMachine);
        VendingMachineCLI cli = new VendingMachineCLI(menu, purchaseMenuCLI, vendingMachine);

        cli.run();

        Assert.assertEquals(menuString + "Thanks for stopping by!" + System.lineSeparator(), byteStream.toString());

    }
}
