package com.revature.Expense.dl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
	private Logger logger = LogManager.getLogger(this.getClass());
	private static int LatesttranId = 1;
	private static int LatestempId = 1;// to keep employee id accurrate in memory to prevent double id's
	private static int LatestMId = 1;// to keep manager id accurrate so double id's don't happen wont do anything if manger is moved to god and the number will just be lost
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

//creats a bunch of transactions so i dont have to manually enter for testing
/* with database dont need this
private void LoadTestingData() {
	//yes i should just use a for loop or a while loop thanks for pointing it out
	int user = 0;
	double am = 0;
	String date = "";
	String des = "";
	
	user = 1;
	am = 12.50;
	date = "15";
	
	des = "R1";
	transaction NewReport1 =  new transaction(user,am,date,des);
	NewReport1.Food();
	ReportBL.addtransaction(NewReport1);
	
	des = "R2";
	transaction NewReport2 = new transaction(user,am,date,des);
	NewReport2.Food();
	ReportBL.addtransaction(NewReport2);
	
	des = "R3";
	transaction NewReport3 = new transaction(user,am,date,des);
	NewReport3.Food();
	ReportBL.addtransaction(NewReport3);
	
	des = "R4";
	transaction NewReport4 = new transaction(user,am,date,des);
	NewReport4.Approve();
	NewReport4.Food();
	ReportBL.addtransaction(NewReport4);
	
	user = 2;
	des = "R5";
	transaction NewReport5 = new transaction(user,am,date,des);
	NewReport5.Approve();
	NewReport5.Food();
	ReportBL.addtransaction(NewReport5);
	
	des = "R6";
	transaction NewReport6 = new transaction(user,am,date,des);
	NewReport6.Deny();
	NewReport6.Food();
	ReportBL.addtransaction(NewReport6);
	
	des = "R7";
	transaction NewReport7 = new transaction(user,am,date,des);
	NewReport7.Deny();
	NewReport7.Food();
	ReportBL.addtransaction(NewReport7);
	
	des = "R8";
	transaction NewReport8 = new transaction(user,am,date,des);
	NewReport8.Food();
	ReportBL.addtransaction(NewReport8);
	
	des = "R9";
	transaction NewReport9 = new transaction(user,am,date,des);
	NewReport9.Food();
	ReportBL.addtransaction(NewReport9);
	
	user = 3;
	des = "R10";
	transaction NewReport10 = new transaction(user,am,date,des);
	NewReport10.Food();
	ReportBL.addtransaction(NewReport10);
	
	des = "R11";
	transaction NewReport11 = new transaction(user,am,date,des);
	NewReport11.Deny();
	NewReport11.Food();
	ReportBL.addtransaction(NewReport11);
	
	user = 4;
	des = "R12";
	transaction NewReport12 = new transaction(user,am,date,des);
	NewReport12.Deny();
	NewReport12.Food();
	ReportBL.addtransaction(NewReport12);
	
	des = "R13";
	transaction NewReport13 = new transaction(user,am,date,des);
	NewReport13.Deny();
	NewReport13.Food();
	ReportBL.addtransaction(NewReport13);
	
	des = "R14";
	transaction NewReport14 = new transaction(user,am,date,des);
	NewReport14.Approve();
	NewReport14.Food();
	ReportBL.addtransaction(NewReport14);
	
	des = "R15";
	transaction NewReport15 = new transaction(user,am,date,des);
	NewReport15.Approve();
	NewReport15.Food();
	ReportBL.addtransaction(NewReport15);
	
	user = 5;
	des = "R16";
	transaction NewReport16 = new transaction(user,am,date,des);
	NewReport16.Food();
	ReportBL.addtransaction(NewReport16);
	
	user = 6;
	des = "R17";
	transaction NewReport17 = new transaction(user,am,date,des);
	NewReport17.Food();
	ReportBL.addtransaction(NewReport17);
	
	des = "R18";
	transaction NewReport18 = new transaction(user,am,date,des);
	NewReport18.Food();
	ReportBL.addtransaction(NewReport18);
	
	des = "R19";
	transaction NewReport19 = new transaction(user,am,date,des);
	NewReport19.Approve();
	NewReport19.Food();
	ReportBL.addtransaction(NewReport19);
	
	user = 7;
	des = "R20";
	transaction NewReport20 = new transaction(user,am,date,des);
	NewReport20.Approve();
	NewReport20.Food();
	ReportBL.addtransaction(NewReport20);
	
	des = "R21";
	transaction NewReport21 = new transaction(user,am,date,des);
	NewReport21.Approve();
	NewReport21.Food();
	ReportBL.addtransaction(NewReport21);
	
	des = "R22";
	transaction NewReport22 = new transaction(user,am,date,des);
	NewReport22.Deny();
	NewReport22.Food();
	ReportBL.addtransaction(NewReport22);
	
	des = "R23";
	transaction NewReport23 = new transaction(user,am,date,des);
	NewReport23.Deny();
	NewReport23.Food();
	ReportBL.addtransaction(NewReport23);
	
	des = "R24";
	transaction NewReport24 = new transaction(user,am,date,des);
	NewReport24.Food();
	ReportBL.addtransaction(NewReport24);
	
	user = 8;
	des = "R25";
	transaction NewReport25 = new transaction(user,am,date,des);
	NewReport25.Food();
	ReportBL.addtransaction(NewReport25);
	
	des = "R26";
	transaction NewReport26 = new transaction(user,am,date,des);
	NewReport26.Food();
	ReportBL.addtransaction(NewReport26);
	
	des = "R27";
	transaction NewReport27 = new transaction(user,am,date,des);
	NewReport27.Deny();
	NewReport27.Food();
	ReportBL.addtransaction(NewReport27);
	
	des = "R28";
	transaction NewReport28 = new transaction(user,am,date,des);
	NewReport28.Deny();
	NewReport28.Food();
	ReportBL.addtransaction(NewReport28);
	
	des = "R29";
	transaction NewReport29 = new transaction(user,am,date,des);
	NewReport29.Deny();
	NewReport29.Food();
	ReportBL.addtransaction(NewReport29);
	
	des = "R30";
	transaction NewReport30 = new transaction(user,am,date,des);
	NewReport30.Approve();
	NewReport30.Food();
	ReportBL.addtransaction(NewReport30);
	
}
 with database dont need this
public void IntializeEmployees() {
	//yes a for loop would be nice
	int empid = LatestempId;
	LatestempId++;
	String name = "the lord";
	boolean isM = true;
	int Mid = 1;
	//1 god
	Listofemp.add(new userInfo(empid, name, isM, Mid));
	
	//3 managers
	empid = LatestempId;
	LatestempId++;
	name = "London Revival";
	isM = true;
	Mid = LatestMId;
	LatestMId++;
	Listofemp.add(new userInfo(empid, name, isM, Mid));
	empid = LatestempId;
	LatestempId++;
	name = "Allo Cats";
	isM = true;
	Mid = LatestMId;
	LatestMId++;
	Listofemp.add(new userInfo(empid, name, isM, Mid));
	empid = LatestempId;
	LatestempId++;
	name = "King Blond";
	isM = true;
	Mid = LatestMId;
	LatestMId++;
	Listofemp.add(new userInfo(empid, name, isM, Mid));
	
	//9 employees
	empid = LatestempId;
	LatestempId++;
	name = "Oscar Jeffery";
	isM = false;
	Mid = 2;
	Listofemp.add(new userInfo(empid, name, isM, Mid));
	empid = LatestempId;
	LatestempId++;
	name = "Xander Jolly";
	isM = false;
	Mid = 2;
	Listofemp.add(new userInfo(empid, name, isM, Mid));
	empid = LatestempId;
	LatestempId++;
	name = "Pete Malfoy";
	isM = false;
	Mid = 2;
	Listofemp.add(new userInfo(empid, name, isM, Mid));
	empid = LatestempId;
	LatestempId++;
	name = "Madeleine Colton";
	isM = false;
	Mid = 3;
	Listofemp.add(new userInfo(empid, name, isM, Mid));
	empid = LatestempId;
	LatestempId++;
	name = "Millie Johnson";
	isM = false;
	Mid = 3;
	Listofemp.add(new userInfo(empid, name, isM, Mid));
	empid = LatestempId;
	LatestempId++;
	name = "Maisie Morgan";
	isM = false;
	Mid = 3;
	Listofemp.add(new userInfo(empid, name, isM, Mid));
	empid = LatestempId;
	LatestempId++;
	name = "Jess Davis";
	isM = false;
	Mid = 4;
	Listofemp.add(new userInfo(empid, name, isM, Mid));
	empid = LatestempId;
	LatestempId++;
	name = "Oli Jones";
	isM = false;
	Mid = 4;
	Listofemp.add(new userInfo(empid, name, isM, Mid));
	empid = LatestempId;
	LatestempId++;
	name = "Charlotte Rodriguez";
	isM = false;
	Mid = 4;
	Listofemp.add(new userInfo(empid, name, isM, Mid));
}
*/



}