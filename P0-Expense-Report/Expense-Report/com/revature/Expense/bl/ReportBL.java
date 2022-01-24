package com.revature.Expense.bl;

import java.util.List;

import com.revature.Expense.dl.IRepo;
import com.revature.Expense.models.transaction;

/**
 * is the midway point for menu and repo to serve as busiuss logic point
 * @author 16del
 *
 */
public class ReportBL implements RReportBL{
	private IRepo repo;
	
	//calls repo with input from the ui
	public ReportBL(IRepo repo) {
		this.repo = repo;
	}
	public  void addtransaction(transaction tran) {
		repo.addtransaction(tran);
	}
	public List<transaction> gettransaction(){
		return repo.gettransaction();
	}
	public transaction gettransactionByTranId(int id) {
		return repo.gettransactionByTranId(id);
	}
	public transaction gettransactionByUserId(int id) {
		return repo.gettransactionByUserId(id);
	}
}

