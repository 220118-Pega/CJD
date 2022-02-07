package com.revature.Expense.Testing;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import com.revature.Expense.models.userInfo;

/**
 * to test userInfo
 * @author 16del
 *
 */
class userInfoTest {
	userInfo TestUser = new userInfo(1,"Test",false,1);
	@Test
	void testEmployid() {
		try {
		TestUser.setEmployid(5);;
		assertEquals(5, TestUser.getEmployid());
		}catch (Exception e) {
			Assert.fail();
		}
	}
	@Test
	void testName() {
		try {
		TestUser.setName("TestPassed");
		assertEquals("TestPassed",TestUser.getName());
		}catch (Exception e) {
			Assert.fail();
		}
	}
	@Test
	void testisManager() {
		try {
		TestUser.setManager(true);
		assertEquals(true, TestUser.isManager());
		}catch (Exception e) {
			Assert.fail();
		}
	}
	@Test 
	void testManagerid(){
		try {
		TestUser.setManagerid(45);
		assertEquals(45, TestUser.getManagerid());
		}catch (Exception e) {
			Assert.fail();
		}
	}

}
