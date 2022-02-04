package com.revature.Expense.bl;

import java.util.List;

import com.revature.Expense.models.transaction;
import com.revature.Expense.models.userInfo;

public interface RReportBL {
	//adders
	void addtransaction(transaction tran);
	void addemployee(userInfo newuser);
	//getters
	userInfo getUserById(int id);
	List<transaction> gettransaction();
	transaction gettransactionByTranId(int id);
	List<transaction> gettransactionByUserId(int id);
	List<userInfo> getAllUsers();
	public transaction getLatestReport();
}
