package com.revature.Expense.dl;

import java.util.ArrayList;
import java.util.List;

import com.revature.Expense.models.transaction;
import com.revature.Expense.models.userInfo;

/**
 * is the point where the data lives and can be messed with and returned
 * @author 16del
 *
 */
public class RepoMemory implements IRepo {
	private static List<transaction> reportoftran;
	private static List<userInfo> Listofemp;
	private static int LatestId = 1;
	
	public RepoMemory() {
		reportoftran = new ArrayList<transaction>();
		Listofemp = new ArrayList<userInfo>();
	}

public void IntializeEmployees() {
	//yes a for loop would be nice
	int empid = 0;
	String name = "the lord";
	boolean isM = true;
	int Mid = 0;
	//1 god
	Listofemp.add(new userInfo(empid, name, isM, Mid));
	
	//3 managers
	empid = 99;
	name = "London Revival";
	isM = true;
	Mid++;
	Listofemp.add(new userInfo(empid, name, isM, Mid));
	empid--;
	name = "Allo Cats";
	isM = true;
	Mid++;
	Listofemp.add(new userInfo(empid, name, isM, Mid));
	empid--;
	name = "King Blond";
	isM = true;
	Mid++;
	Listofemp.add(new userInfo(empid, name, isM, Mid));
	
	//9 employees
	empid = 1;
	name = "Oscar Jeffery";
	isM = false;
	Mid = 1;
	Listofemp.add(new userInfo(empid, name, isM, Mid));
	empid++;
	name = "Xander Jolly";
	isM = false;
	Mid = 1;
	Listofemp.add(new userInfo(empid, name, isM, Mid));
	empid++;
	name = "Pete Malfoy";
	isM = false;
	Mid = 1;
	Listofemp.add(new userInfo(empid, name, isM, Mid));
	empid++;
	name = "Madeleine Colton";
	isM = false;
	Mid = 2;
	Listofemp.add(new userInfo(empid, name, isM, Mid));
	empid++;
	name = "Millie Johnson";
	isM = false;
	Mid = 2;
	Listofemp.add(new userInfo(empid, name, isM, Mid));
	empid++;
	name = "Maisie Morgan";
	isM = false;
	Mid = 2;
	Listofemp.add(new userInfo(empid, name, isM, Mid));
	empid++;
	name = "Jess Davis";
	isM = false;
	Mid = 3;
	Listofemp.add(new userInfo(empid, name, isM, Mid));
	empid++;
	name = "Oli Jones";
	isM = false;
	Mid = 3;
	Listofemp.add(new userInfo(empid, name, isM, Mid));
	empid++;
	name = "Charlotte Rodriguez";
	isM = false;
	Mid = 3;
	Listofemp.add(new userInfo(empid, name, isM, Mid));
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

//creats a dummy in case of failure and searches through  the database for a match by transaction id returns match if found returns dummy if not
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

}