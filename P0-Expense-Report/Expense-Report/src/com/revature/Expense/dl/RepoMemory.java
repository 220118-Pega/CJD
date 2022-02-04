package com.revature.Expense.dl;

import java.util.ArrayList;
import java.util.List;
/*
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
*/
import com.revature.Expense.models.transaction;
import com.revature.Expense.models.userInfo;

/**
 * is the point where the data lives and can be messed with and returned
 * since DAO implanted seems no longer needed or called on
 * @author 16del
 *
 */
public class RepoMemory implements IRepo {
	private static List<transaction> reportoftran;
	private static List<userInfo> Listofemp;
	//private Logger logger = LogManager.getLogger(this.getClass());
	private static int LatesttranId = 1;
	private static int LatestempId = 1;// to keep employee id accurrate in memory to prevent double id's
	//private static int LatestMId = 1; now a matter for database// to keep manager id accurrate so double id's don't happen wont do anything if manger is moved to god and the number will just be lost
	//see how latest Mid and latest empId work without doing it myself
public RepoMemory() {
		reportoftran = new ArrayList<transaction>();
		Listofemp = new ArrayList<userInfo>();
	}

//returns the entire list of all employees 
public List<userInfo> getuserInfo(){
	return RepoMemory.Listofemp;
}

//adds a employee to the data base note only managers should have access to this
@Override
public void addemployee(userInfo newuser) {
	newuser.setEmployid(LatestempId);
	Listofemp.add(newuser);
	LatestempId++;
	
}
//gets a employee object out of the list based on the id
@Override
public userInfo getUserById(int id) {
	userInfo foundUser = new userInfo("zero",0);
	for(userInfo UI:Listofemp) {
		if (UI.getEmployid() == id) {
			foundUser = UI;
		}
	}
	return foundUser;
}

//adds a transaction to the data base increments global transaction id
@Override
public void addtransaction(transaction newtran) {
	newtran.setTransactionid(LatesttranId);
	reportoftran.add(newtran);
	LatesttranId++;
}

//returns the entire list of transactions ie gives everything in memory
@Override
public List<transaction> gettransaction() {
	return RepoMemory.reportoftran;
}

//creats a dummy in case of failure and searches through  the database for a match by transaction id returns match if found returns dummy if not
@Override
public transaction gettransactionByTranId(int id) {
	//userid,amount,date,type,id,status
	transaction foundtran = new transaction(0,0,"zero","zero");
	for(transaction transaction:reportoftran) {
		if(transaction.getTransactionid() == id) {
			foundtran = transaction;
		}
	}
	return foundtran;
}
//creates a dummy in case of failure and searches through the database for a match by employee id returns match if found returns dummy if not
@Override
public List<transaction> gettransactionByUserId(int id) {
	//userid,amount,date,type,id,status
	List<transaction> UserReports = new ArrayList<transaction>();
	transaction dummytran = new transaction(0,0,"zero","nothing");
	for(transaction transaction:reportoftran) {
		if(transaction.getUserid() == id) {
			UserReports.add(transaction);
		}
	}
	if(UserReports.isEmpty()) {
		UserReports.add(dummytran);
	}
	return UserReports;
}

@Override
public List<userInfo> getAllUsers() {
	return RepoMemory.Listofemp;
}

@Override
public void updateState(transaction updatetran) {
	// TODO Auto-generated method stub
	
}

@Override
public transaction getLatesttransaction() {
	transaction LatestTran = new transaction(0,0,"zero","zero");
	int testid = 0;
	for (transaction tran:reportoftran) {
		if (tran.getTransactionid() > testid) {
			LatestTran = tran;
			testid = tran.getTransactionid();
		}
	}
	return LatestTran;
}
}
