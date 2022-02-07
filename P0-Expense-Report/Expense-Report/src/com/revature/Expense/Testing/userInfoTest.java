package com.revature.Expense.Testing;
import static org.junit.jupiter.api.Assertions.*;

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
		TestUser.setEmployid(5);;
		assertEqual(5, TestUser.getEmployid());
	}
	@Test
	void testName() {
		TestUser.setName("TestPassed");
		assertEqual("TestPassed",TestUser.getName());
	}
	@Test
	void testisManager() {
		TestUser.setManager(true);
		assertEqual(true, TestUser.isManager());
	}
	@Test 
	void testManagerid(){
		TestUser.setManagerid(45);
		assertEqual(45, TestUser.getManagerid());
	}

}
