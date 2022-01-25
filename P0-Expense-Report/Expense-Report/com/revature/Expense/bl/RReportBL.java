package com.revature.Expense.bl;

import java.util.List;

import com.revature.Expense.models.transaction;

public interface RReportBL {
	
	void addtransaction(transaction tran);
	
	List<transaction> gettransaction();
	transaction gettransactionByTranId(int id);
	List<transaction> gettransactionByUserId(int id);
}
