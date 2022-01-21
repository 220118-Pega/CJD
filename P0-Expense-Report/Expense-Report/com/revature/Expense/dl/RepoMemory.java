package com.revature.Expense.dl;

import java.util.ArrayList;
import java.util.List;

import com.revature.Expense.models.transaction;

/**
 * is the point where the data lives and can be messed with and returned
 * @author 16del
 *
 */
public class RepoMemory implements IRepo {
	private static List<transaction> reportoftran;
	private static int LatestId = 1;
	
	public RepoMemory() {
		reportoftran = new ArrayList<transaction>();			
	}

//adds a transaction to the data base increments global transaction id
@Override
public void addtransaction(transaction newtran) {
	newtran.setTransactionid(LatestId);
	reportoftran.add(newtran);
	LatestId++;
}

//returns the entire list of transactions ie gives everything in memory
@Override
public List<transaction> gettransaction() {
	return RepoMemory.reportoftran;
}

//creats a dummy in case of failure and searches through  the database for a match returns match if found returns dummy if not
@Override
public transaction gettransactionByTranId(int id) {
	//userid,amount,date,type,id,status
	transaction foundtran = new transaction(0,0,"zero","nothing");
	for(transaction transaction:reportoftran) {
		if(transaction.getTransactionid() == id) {
			foundtran = transaction;
		}
	}
	return foundtran;
}

@Override
public transaction gettransactionByUserId(int id) {
	//userid,amount,date,type,id,status
	transaction foundtran = new transaction(0,0,"zero","nothing");
	for(transaction transaction:reportoftran) {
		if(transaction.getUserid() == id) {
			foundtran = transaction;
		}
	}
	return foundtran;
}

}