package com.revature.Expense.Testing;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import com.revature.Expense.models.SortbyUid;
import com.revature.Expense.models.userInfo;

/**
 * to test sortbyUid
 * @author 16del
 *
 */
class SortbyUidTest {
	userInfo testAUser = new userInfo(20, "A", false, 3);
	userInfo testBUser = new userInfo(30, "A", false, 3);
	int A;
	
	@Test
	void testCompare() {
		try {
		SortbyUid testSort = new SortbyUid();
		A = testSort.compare(testAUser, testBUser);
		Assert.assertEquals(-10, A);
		}catch (Exception e) {
			Assert.fail();
		}
	}

}
