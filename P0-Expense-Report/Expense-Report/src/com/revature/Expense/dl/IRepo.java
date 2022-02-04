package com.revature.Expense.dl;

import java.util.List;

import com.revature.Expense.models.transaction;
import com.revature.Expense.models.userInfo;

public interface IRepo {
	public void addtransaction(transaction newtran);
	//public void IntializeEmployees();
	List<transaction> gettransaction();
	transaction gettransactionByTranId(int id);
	List<transaction> gettransactionByUserId(int id);
	void addemployee(userInfo newuser);
	public userInfo getUserById(int id);
	public List<userInfo> getAllUsers();
	/**
	 * @param updatetran
	 */
	public void updateState(transaction updatetran);
	/**
	 * @return
	 */
	public transaction getLatesttransaction();
}
