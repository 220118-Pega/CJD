package com.revature.Expense.dl;

import java.util.List;

import com.revature.Expense.models.transaction;

public interface IRepo {
	public void addtransaction(transaction newtran);
	List<transaction> gettransaction();
	transaction gettransactionbyId(int id);
}
