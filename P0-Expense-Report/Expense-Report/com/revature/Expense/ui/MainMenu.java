package com.revature.Expense.ui;

import java.util.Scanner;
import com.revature.Expense.bl.ReportBL;
import com.revature.Expense.models.transaction;
import com.revature.Expense.models.userInfo;

public class MainMenu {
	private Scanner myScanner;
	private ReportBL ReportBL;
	public MainMenu(Scanner myScanner,ReportBL ReportBL) {
		this.myScanner = myScanner;
		this.ReportBL = ReportBL;
	}
	
public void start() {
	//sets up the employee database
	ReportBL.IntializeEmployees();
	//sets up preexisting trasaction data
	LoadTestingData();
	
	//set up inital variables
	boolean keepGoing = true;
	int accessLevel = 0;
	
	//gets the id of user
	EmployeeIdRequest();
	accessLevel = userIntAnswer();
	myScanner.nextLine();
	//if dummy id is returned quits as they aren't a user or quit
	if (accessLevel == 0) {
		System.out.println("you are not a athourized user goodbye");
		keepGoing = false;
	}
	else {
	//gets the user item outside of the loops so only recorded once
	userInfo currentUser = ReportBL.getUserById(accessLevel);
	// if not a manager then you are a employee and can make reimpertment requests and view your old ones
	if(currentUser.isManager() == false){
		do {
		MainEmployeeMenu();
		String userInput = myScanner.nextLine();
		switch(userInput) {
		case "1":
			System.out.println("Filing your report");
			createtransaction(currentUser);
			break;
		case "2":
			System.out.println("Welcome to your Records");
			getAlltransaction(currentUser);
			break;
		case "x":
			GoodBye();
			keepGoing = false;
			break;
		default:
			PickRealOption();
			break;
		}
		}while(keepGoing);
		}
	//if you are a manager you have the full range of options
	else {
		do {
			MainManagerTextMenu();
			String userInput = myScanner.nextLine();
			switch(userInput) {
			case "1":
				System.out.println("Filing your report");
				createtransaction(currentUser);
				break;
			case "2":
				System.out.println("Welcome to Records");
				getAlltransaction(currentUser);
				break;
			case "3":
				Approvetransaction(currentUser);
				break;
			case "4":
				Denytransaction(currentUser);
				break;
			case "x":
				GoodBye();
				keepGoing = false;
				break;
			default:
				PickRealOption();
				break;
			}
			
		}while(keepGoing);
}}}

//approves a transaction
private void Approvetransaction(userInfo currentUser) {
	// if given incorect id num then a dummy is changed need to give warning
	transaction updatetran = Singletransaction(currentUser);
	if(confirm(updatetran)) {
		updatetran.Approve();
		System.out.println("Approved");}
	myScanner.nextLine();
	}
//denys a transaction
private void Denytransaction(userInfo currentUser) {
	// if given incorect id num then a dummy is changed need to give warning
	transaction updatetran = Singletransaction(currentUser);
	if(confirm(updatetran)) {
		updatetran.Deny();
		System.out.println("Denied");
	}	
	myScanner.nextLine();
}
//is the menu to retrive records from memory repo has options for employees and managers currently trusts people to tell the truth
private void getAlltransaction(userInfo currentUser) {
	//variables for program
	Boolean keepGoing = true;
	String choice = "";
	boolean manager = currentUser.isManager();
	
	//employee or manager
	//if employee they only can access their own records
	do {
		if(manager) {
			MainRecordsManagerMenu();
		}
		else {
			MainRecordsEmployeeMenu();
		}
		choice = myScanner.nextLine();
		switch(choice) {
			//prints all of employee's transactions or prints all of everyone's transactions if manager
			case "1":
				if(manager) {
				for(transaction transaction:ReportBL.gettransaction()) {
					System.out.println(transaction);
				}}
				else {
				GetEmpRecords(currentUser.getEmployid());
				}
				keepGoing = false;
				break;
			//single transaction by its transaction id can be used by employee's and managers but manger can get any transaction
			case "2":
				Singletransaction(currentUser);
				keepGoing = false;
				break;
			//gets all employee's approved records or prints everyone's if manager
			case "3":
				GetAllOfState(Integer.parseInt(choice), currentUser);
				keepGoing = false;
				break;
			//gets all employee's denied records or prints everyone's if manager
			case "4":
				GetAllOfState(Integer.parseInt(choice), currentUser);
				keepGoing = false;
				break;
			//gets all  employee's pending records or prints everyone's if manager
			case "5":
				GetAllOfState(Integer.parseInt(choice), currentUser);
				keepGoing = false;
				break;
			case "6":
				if(manager) {
					EmployeeIdRequest();
					int userid = userIntAnswer();
					GetEmpRecords(userid);}
			case "x":
				keepGoing = false;
				break;
			default:
				PickRealOption();
				break;
		}}while(keepGoing);
		}


//creates a transaction from given info and uploads it to memory repo
//takes someone through proccess to create a transaction and upload to memory repo
private void createtransaction(userInfo currentUser) {
	boolean keepGoing = true;
	//all the variables
	int userid = currentUser.getEmployid();
	double transactionamount = 0;
	int quit = 0;// so quiting from transaction type is easy
	//getting all variable info from employee
	//do while loop to get a accurate tran amout
	System.out.println("Please enter transaction amount: ");
	System.out.println("or enter x to quit: ");
	while(keepGoing) {
		if (myScanner.hasNextDouble()){
				transactionamount = myScanner.nextDouble();
				keepGoing = false;
		}
		else {
			String notDoubleAnswer = myScanner.nextLine();
			if(notDoubleAnswer.equals("x")) {
				keepGoing = false;
			}
			else {
				System.out.println("invaild number please enter vaild amount: ");
				System.out.println("or enter x to quit:");}
		}}
	if(transactionamount == 0) {
		GoodBye();
	}
	else {
	keepGoing = true;
	myScanner.nextLine();
	//they can do anything to date need to make them enter a date format
	System.out.println("Please enter date of transaction: ");
	System.out.println("or enter x to quit:");
	String date = myScanner.nextLine();
	if(date.equals("x")) {
		GoodBye();
	}else {
	//they can say anything for descrption no need to control it
	System.out.println("Please enter a descrption of the transaction: ");
	System.out.println("or enter x to quit:");
	String descrption = myScanner.nextLine();
	if(descrption.equals("x")) {
		GoodBye();
	}
	else {
	
	//making new transaction with info
	transaction newReport = new transaction(userid,transactionamount,date,descrption);
	
	// adding the right type
	while(keepGoing) {
	MainTypeMenu();
	String transactiontype = myScanner.nextLine();
	//type
	switch(transactiontype) {
	case "1":
		newReport.Lodging();
		keepGoing = false;
		break;
	case "2":
		newReport.Travel();
		keepGoing = false;
		break;
	case "3":
		newReport.Food();
		keepGoing = false;
		break;
	case "4":
		newReport.Other();
		keepGoing = false;
		break;
	case "x":
		keepGoing = false;
		quit = 1;
		break;
	default:
		PickRealOption();
		break;
	}}
	if( quit == 1) {
		GoodBye();
	}
	else {
	//saving
	ReportBL.addtransaction(newReport);
	System.out.println(newReport);
	System.out.println("");
	
}}}}}


//contains the simplifications to above code by taking repeating code and turning those to a method
//the similar process of approve and deny

private transaction Singletransaction(userInfo currentUser) {
	int tranid = 0;
	System.out.println("Enter the id of the transaction: ");
	tranid = userIntAnswer();
	transaction updatetran = ReportBL.gettransactionByTranId(tranid);
	if(currentUser.isManager()) {
		return updatetran;
	}
	else {
		if(updatetran.getUserid() == currentUser.getEmployid()) {
			return updatetran;
		}
		else {
			transaction noAccess = new transaction(0,0,"zero","zero");
			System.out.println("you did not have access to that transaction");
			return noAccess;
		}
	}
}
//confirms that the transaction is a real transaction and not the dummy transaction
private boolean confirm(transaction updatetran) {
	boolean real = false;
	if(updatetran.getTransactionid() == 0) {
		System.out.println("No Record of Transaction with that id check id used.");
		myScanner.nextLine();
	}else {
		real = true;
	}
	return real;
}
//prints all employee transactions
//common tasks method out for ease of code
private void GetEmpRecords(int userid) {
	for(transaction user:ReportBL.gettransactionByUserId(userid)) {
		if(user.getUserid() == userid) {
			System.out.println(user);
			}}
	myScanner.nextLine();//this is to clear the line so things arent left for future checks
}
//prints all transactions of a type decided by number manager entered
private void GetAllOfState(int choice,userInfo currentUser) {
	transaction helper = new transaction(0,1.0,"zero","zero");
	if (choice == 3) {helper.Approve();}
	if (choice == 4) {helper.Deny();}
	if (choice == 5) {helper.Pending();}
	if(currentUser.isManager()) {
	for(transaction transaction:ReportBL.gettransaction()) {
		if(transaction.getState() == helper.getState()) {
			if(currentUser.isManager()) {
				System.out.println(transaction);}
			else {
				if(transaction.getUserid() == currentUser.getEmployid()) {
					System.out.println(transaction);}	
		}}
	}}}
	//this is the clear it for returning to method
//takes person through a loop to get a accurate id number with 0 being the dummny number if they exit early
private int userIntAnswer() {
	int id = 0;
	boolean keepGoing = true;
	while(keepGoing) {
			if (myScanner.hasNextInt()){
					id = myScanner.nextInt();
					keepGoing = false;
					break;
			}
			else {
				String answer = myScanner.nextLine();
				if(answer.equals("x")) {
					keepGoing = false;
					break;
				}
				else {
					System.out.println("invaild id please enter vaild id: ");
					System.out.println("or enter x to quit:");}
	}}
	return id;
}
//holding text blocks for ease of editing

//these are all just strings that were repeated or long so put them down here to make code above pretty
private void PickRealOption() {
	System.out.println("Please only put listed options");
}
private void GoodBye() {
	System.out.println("Good Bye \n");
}
private void MainManagerTextMenu() {
	System.out.println("Welcome to expense reports");
	System.out.println("[1] Create a reinburstment request");
	System.out.println("[2] Records of requests");
	System.out.println("[3] Approve request");
	System.out.println("[4] Deny request");
	System.out.println("[x] Exit");
	System.out.println("[t] Testbatch");//comment out after testing
}
private void MainEmployeeMenu() {
	System.out.println("Welcome to expense reports");
	System.out.println("[1] Create a reinburstment request");
	System.out.println("[2] Records of requests");
	System.out.println("[x] Exit");
}
private void MainTypeMenu() {
	System.out.println("please enter type of transaction");
	System.out.println("[1] Lodging");
	System.out.println("[2] Travel");
	System.out.println("[3] Food");
	System.out.println("[4] Other");
	System.out.println("[x] to quit");
}
private void MainRecordsManagerMenu() {
	System.out.println("[1] list all records");
	System.out.println("[2] list 1 transaction");
	System.out.println("[3] list all approved records");
	System.out.println("[4] list all denied records");
	System.out.println("[5] list all pending records");
	System.out.println("[6] list 1 employee's records");
	System.out.println("[x] Quit");
}
private void MainRecordsEmployeeMenu() {
	System.out.println("[1] list all your records");
	System.out.println("[2] list 1 transaction");
	System.out.println("[3] list all your approved records");
	System.out.println("[4] list all your denied records");
	System.out.println("[5] list all your pending records");
	System.out.println("[x] Quit");
}
private void EmployeeIdRequest() {
	System.out.println("Please enter a employee id");
	System.out.println("or press x to exit: ");
}


//creats a bunch of transactions so i dont have to manually enter for testing
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






}

