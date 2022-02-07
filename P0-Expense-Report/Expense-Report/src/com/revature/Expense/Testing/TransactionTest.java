package com.revature.Expense.Testing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import com.revature.Expense.models.transaction;
/**
 * to test transaction class
 

/**
 * @author 16del
 */
 
class TransactionTest {
	transaction TestTran = new transaction(1,1.0,"date","desc");
	
	@Test
	void testApprove() {
		try {
		TestTran.Approve();
		assertEquals("Approved",TestTran.getStatename());
		}
		catch (Exception e) {
			Assert.fail();
		}
		}	
	@Test
	void testDeny() {
		try {
		TestTran.Deny();
		assertEquals("Denied",TestTran.getStatename());
	}catch (Exception e) {
		Assert.fail();
	}
		}
	@Test
	void TestStringStateSet() {
		try {
		TestTran.StringStateSet("Approved");
		assertEquals("Approved",TestTran.getStatename());
		}catch (Exception e) {
			Assert.fail();
		}
		
		try {
		TestTran.StringStateSet("Denied");
		assertEquals("Denied", TestTran.getStatename());
		}catch (Exception e) {
			Assert.fail();
		}
		
		try {
		TestTran.StringStateSet("Pending");
		assertEquals("Pending", TestTran.getStatename());
		}catch (Exception e) {
			Assert.fail();
		}
		}
	@Test
	void testStringTypeSet() {
		try {
		TestTran.StringTypeSet("Food");
		assertEquals("Food",TestTran.getTypename());
		}catch (Exception e) {
			Assert.fail();
		}
		try {
		TestTran.StringTypeSet("Lodging");
		assertEquals("Lodging", TestTran.getTypename());
		}catch (Exception e) {
			Assert.fail();
		}
		try {
		TestTran.StringTypeSet("Other");
		assertEquals("Other", TestTran.getTypename());
		}catch (Exception e) {
			Assert.fail();
		}
		try {
		TestTran.StringTypeSet("Travel");
		assertEquals("Travel", TestTran.getTypename());
		}catch (Exception e) {
			Assert.fail();
		}
	}
}
