package com.revature.Expense.bl;

import java.util.List;

import com.revature.Expense.dl.IRepo;
import com.revature.Expense.models.transaction;

public class ReportBL implements RReportBL{
	private IRepo repo;
	
	public ReportBL(IRepo repo) {
		this.repo = repo;
	}
	public  void addtransaction(transaction tran) {
		repo.addtransaction(tran);
	}
	public List<transaction> gettransaction(){
		return repo.gettransaction();
	}
	public transaction gettransactionById(int id) {
		return repo.gettransactionbyId(id);
	}
}
