package com.revature.Expense.dl;

import java.util.ArrayList;
import java.util.List;

import com.revature.Expense.models.transaction;

public class RepoMemory implements IRepo {
	private static List<transaction> reportoftran;
	private static int LatestId = 1;
	
	public RepoMemory() {
		reportoftran = new ArrayList<transaction>();			
	}


@Override
public void addtransaction(transaction newtran) {
	// TODO Auto-generated method stub
	newtran.setTransactionid(LatestId);
	reportoftran.add(newtran);
	LatestId++;
}
@Override
public List<transaction> gettransaction() {
	// TODO Auto-generated method stub
	return RepoMemory.reportoftran;
}
@Override
public transaction gettransactionbyId(int id) {
	// TODO Auto-generated method stub
	//userid,amount,date,type,id,status
	transaction foundtran = new transaction(0,0,"zero","zero");
	for(transaction transaction:reportoftran) {
		if(transaction.getTransactionid() == id) {
			foundtran = transaction;
		}
	}
	return foundtran;
}}