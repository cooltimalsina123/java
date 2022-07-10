package com.techelevator.vendingmachine.log;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FileAuditLogTests {
	private File inputFile;

	@Before
	public void setup() {
		inputFile = new File("test_inventory.txt");
	}

	@After
	public void tearDown() {
		inputFile.delete();
	}

	@Test
	public void construct_deletes_existing_file() {
		// Arrange
		try {
			inputFile.createNewFile();
		} catch (IOException e) {
			Assert.fail("Could not create file.");
		}
		
		// Act
		new FileAuditLog(inputFile);

		// Assert
		Assert.assertFalse(inputFile.exists());
	}

	
	@Test
	public void logMoneyFed_creates_entry() {
		// Arrange
		FileAuditLog auditLog = new FileAuditLog(inputFile);
		
		// Act
		auditLog.logMoneyFed(BigDecimal.ONE, BigDecimal.ONE);

		// Assert
		Assert.assertTrue(inputFile.exists());
		
		try {
			List<String> lines = Files.readAllLines(inputFile.toPath());
			
			Assert.assertNotNull(lines);
			Assert.assertEquals(1, lines.size());
		} catch (IOException e) {
			Assert.fail("Could not open file.");
		}
	}
	
	@Test
	public void logMoneyFed_formatting_is_correct() {
		// Arrange
		FileAuditLog auditLog = new FileAuditLog(inputFile);
		
		// Act
		auditLog.logMoneyFed(BigDecimal.ONE, BigDecimal.ONE);

		// Assert
		Assert.assertTrue(inputFile.exists());
		
		try {
			List<String> lines = Files.readAllLines(inputFile.toPath());
			
			Assert.assertTrue(lines.get(0).endsWith(" FEED MONEY $1.00 $1.00"));
		} catch (IOException e) {
			Assert.fail("Could not open file.");
		}
	}
	
	@Test
	public void logPurchaseMade_creates_entry() {
		// Arrange
		FileAuditLog auditLog = new FileAuditLog(inputFile);
		
		// Act
		auditLog.logPurchaseMade("Chips!!", BigDecimal.ONE, BigDecimal.ONE);

		// Assert
		Assert.assertTrue(inputFile.exists());
		
		try {
			List<String> lines = Files.readAllLines(inputFile.toPath());
			
			Assert.assertNotNull(lines);
			Assert.assertEquals(1, lines.size());
		} catch (IOException e) {
			Assert.fail("Could not open file.");
		}
	}
	
	@Test
	public void logPurchaseMade_formatting_is_correct() {
		// Arrange
		FileAuditLog auditLog = new FileAuditLog(inputFile);
		
		// Act
		auditLog.logPurchaseMade("Chips!!", BigDecimal.ONE, BigDecimal.ONE);

		// Assert
		Assert.assertTrue(inputFile.exists());
		
		try {
			List<String> lines = Files.readAllLines(inputFile.toPath());
			
			Assert.assertTrue(lines.get(0).endsWith("Chips!! $1.00 $1.00"));
		} catch (IOException e) {
			Assert.fail("Could not open file.");
		}
	}
	
	@Test
	public void logTransactionFinished_creates_entry() {
		// Arrange
		FileAuditLog auditLog = new FileAuditLog(inputFile);
		
		// Act
		auditLog.logTransactionFinished(BigDecimal.ONE, BigDecimal.ZERO);

		// Assert
		Assert.assertTrue(inputFile.exists());
		
		try {
			List<String> lines = Files.readAllLines(inputFile.toPath());
			
			Assert.assertNotNull(lines);
			Assert.assertEquals(1, lines.size());
		} catch (IOException e) {
			Assert.fail("Could not open file.");
		}
	}
	
	@Test
	public void logTransactionFinished_formatting_is_correct() {
		// Arrange
		FileAuditLog auditLog = new FileAuditLog(inputFile);
		
		// Act
		auditLog.logTransactionFinished(BigDecimal.ONE, BigDecimal.ZERO);

		// Assert
		Assert.assertTrue(inputFile.exists());
		
		try {
			List<String> lines = Files.readAllLines(inputFile.toPath());
			
			Assert.assertTrue(lines.get(0).endsWith(" GIVE CHANGE $1.00 $0.00"));
		} catch (IOException e) {
			Assert.fail("Could not open file.");
		}
	}
	
	@Test
	public void log_several_adds_many_lines_in_order() {
		// Arrange
		FileAuditLog auditLog = new FileAuditLog(inputFile);
		
		// Act
		auditLog.logMoneyFed(BigDecimal.valueOf(5), BigDecimal.valueOf(5));
		auditLog.logPurchaseMade("Chips", BigDecimal.valueOf(5), BigDecimal.valueOf(4));
		auditLog.logPurchaseMade("Gum", BigDecimal.valueOf(4), BigDecimal.valueOf(3.5));
		auditLog.logTransactionFinished(BigDecimal.valueOf(3.5), BigDecimal.ZERO);

		// Assert
		Assert.assertTrue(inputFile.exists());
		
		try {
			List<String> lines = Files.readAllLines(inputFile.toPath());
			
			Assert.assertEquals(4, lines.size());
			Assert.assertTrue(lines.get(0).endsWith(" FEED MONEY $5.00 $5.00"));
			Assert.assertTrue(lines.get(1).endsWith(" Chips $5.00 $4.00"));
			Assert.assertTrue(lines.get(2).endsWith(" Gum $4.00 $3.50"));
			Assert.assertTrue(lines.get(3).endsWith(" GIVE CHANGE $3.50 $0.00"));
		} catch (IOException e) {
			Assert.fail("Could not open file.");
		}
	}
}
