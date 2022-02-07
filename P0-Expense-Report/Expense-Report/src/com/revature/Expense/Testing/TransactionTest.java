package com.revature.Expense.Testing;

import static  org.junit.Assert;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions;

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
		TestTran.Approve();
		assertEquals("Approved",TestTran.getStatename());
	}
	
	@Test
	void testDeny() {
		TestTran.Deny();
		assertEquals("Denied",TestTran.getStatename());
	}
	@Test
	void TestStringStateSet() {
		TestTran.StringStateSet("Approved");
		assertEquals("Approved",TestTran.getStatename());
		TestTran.StringStateSet("Denied");
		assertEquals("Denied", TestTran.getStatename());
		TestTran.StringStateSet("Pending");
		assertEquals("Pending", TestTran.getStatename());
	}
	
	@Test
	void testStringTypeSet() {
		TestTran.StringTypeSet("Food");
		assertEquals("Food",TestTran.StringStateSet("Food"));
		TestTran.StringTypeSet("Lodging");
		assertEquals("Lodging", TestTran.StringStateSet("Loging"));
		TestTran.StringTypeSet("Other");
		assertEquals("Other", TestTran.StringStateSet("Other"));
		TestTran.StringTypeSet("Travel");
		assertEquals("Travel", TestTran.StringTypeSet("Travel"));
	}
}
