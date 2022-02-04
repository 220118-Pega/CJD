package com.revature.Expense.bl;

import java.util.List;

import com.revature.Expense.dl.IRepo;
import com.revature.Expense.models.transaction;
import com.revature.Expense.models.userInfo;

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
	//forward add transaction to database request
	public  void addtransaction(transaction tran) {
		repo.addtransaction(tran);
	}
	//returns all transactions from database
	public List<transaction> gettransaction(){
		return repo.gettransaction();
	}
	//return transaction that matches id or returns null if not in database
	public transaction gettransactionByTranId(int id) {
		return repo.gettransactionByTranId(id);
	}
	//return transactions that belong to specific user null if non in database
	public List<transaction> gettransactionByUserId(int id) {
		return repo.gettransactionByUserId(id);
	}
	//forward add user to database
	public void addemployee(userInfo newuser) {
		repo.addemployee(newuser);
		
	}
	//return specific user who has id or null if not in database
	public userInfo getUserById(int id) {
		return repo.getUserById(id);
	}
	//returns all users from database
	public List<userInfo> getAllUsers(){
		return repo.getAllUsers();
		
	}
	//used to update State(deny,approve,pending) of the transaction in the database
	public void updateState(transaction updatetran) {
		repo.updateState(updatetran);
		
	}
	//gets the latest made transaction(biggest transaction id) used for when someone adds a transaction
	//to the database to show it in it
	public transaction getLatestReport() {
		return repo.getLatesttransaction();
	}


}

