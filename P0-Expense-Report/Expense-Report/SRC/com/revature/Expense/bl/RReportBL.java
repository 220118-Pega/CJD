package com.revature.Expense.bl;

import java.util.List;

import com.revature.Expense.models.transaction;
import com.revature.Expense.models.userInfo;

public interface RReportBL {
	
	void addtransaction(transaction tran);
	//public void IntializeEmployees();
	public void addemployee(userInfo newuser);
	public userInfo getUserById(int id);
	List<transaction> gettransaction();
	transaction gettransactionByTranId(int id);
	List<transaction> gettransactionByUserId(int id);
}
