/**
 * 
 */
package com.revature.Expense.models;

import java.util.Comparator;

/**
 * this was made to implement 1 function
 *  to ensure database returned entries in
 *   the order i want
 * @author 16del
 *
 */
public class SortbyTid implements Comparator<transaction> {

		public int compare(transaction a, transaction b) {
			return a.getTransactionid() - b.getTransactionid();
		}
}
