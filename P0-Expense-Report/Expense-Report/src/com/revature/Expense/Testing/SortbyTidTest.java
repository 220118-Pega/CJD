package com.revature.Expense.Testing;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import com.revature.Expense.models.SortbyTid;
import com.revature.Expense.models.transaction;

/**
 * to test sortbyTid
 * @author 16del
 *
 */
class SortbyTidTest {

	transaction testAtransaction = new transaction(1,35.5, "jan", "r1");
	transaction testBtransaction = new transaction(2,42.2, "feb", "r2");
	int A;
	
	@Test
	void testCompare() {
		testAtransaction.setTransactionid(10);
		testBtransaction.setTransactionid(20);
		SortbyTid testSort = new SortbyTid();
		A = testSort.compare(testAtransaction, testBtransaction);
		Assert.assertEquals(-10, A);
	}

}
