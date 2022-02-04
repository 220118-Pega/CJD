/**
 * 
 */
package com.revature.Expense.models;

import java.util.Comparator;

/**
 * created purely to sort my user list at end of find all
 * @author 16del
 *
 */
public class SortbyUid implements Comparator<userInfo>{
	public int compare(userInfo a, userInfo b) {
		return a.getEmployid() - b.getEmployid();
	}
	
}
