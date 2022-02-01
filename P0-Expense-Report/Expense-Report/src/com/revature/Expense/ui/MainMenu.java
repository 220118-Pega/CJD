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
	//gets the user item outside of the loops so only recorded once
	userInfo currentUser = ReportBL.getUserById(accessLevel);
	// if not a manager then you are a employee and can make reimpertment requests and view your old ones
	//if you are a manager you have the full range of options
	do {
		if(currentUser.isManager()){
			MainManagerTextMenu();}
		else {
			MainEmployeeMenu();
		}
		String userInput = myScanner.nextLine();
		switch(userInput) {
		//file out a new transaction
		case "1":
			System.out.println("Filing your report");
			createtransaction(currentUser);
			break;
		//access records a employee can get their own a manager can access anyone's
		case "2":
			System.out.println("Welcome to your Records");
			getAlltransaction(currentUser);
			break;
		//manager can approve a transaction
		case "3":
			if(currentUser.isManager()){
			Approvetransaction(currentUser);}
			else{PickRealOption();}
			break;
		//manager can deny a transaction
		case "4":
			if(currentUser.isManager()){
			Denytransaction(currentUser);}
			else{PickRealOption();}
			break;
		//manager can add employee under them
		case "5":// i am stopping now reread instructions i was starting to go mad with making a complete product but that is not my job only make what was asked
			if(currentUser.isManager()) {
			CreateUser(currentUser);
			}
			else {PickRealOption();}
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

//creates a new employee and adds them
private void CreateUser(userInfo currrentUser) {
	System.out.println("Please enter new employee's name");
	QuitText();
	String name = myScanner.nextLine();
	if(name.equals("x")) {
		GoodBye();
	}
	userInfo newperson = new userInfo(name, currrentUser.getManagerid());
	ReportBL.addemployee(newperson);
	System.out.println(name + " has been added as a employee with you as their manager");
}
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
	// if given incorect id num then a dummy is changed
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
			//single transaction by its transaction id can be used by employee's and managers but manger can get any transaction prints it out
			case "2":
				transaction indivual = Singletransaction(currentUser);
				System.out.println(indivual);
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
	QuitText();;
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
				QuitText();}
		}}
	if(transactionamount == 0) {
		GoodBye();
	}
	else {
	keepGoing = true;
	myScanner.nextLine();
	//they can do anything to date need to make them enter a date format
	System.out.println("Please enter date of transaction: ");
	QuitText();
	String date = myScanner.nextLine();
	if(date.equals("x")) {
		GoodBye();
	}else {
	//they can say anything for descrption no need to control it
	System.out.println("Please enter a descrption of the transaction: ");
	QuitText();
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
//prints all transactions of a type manager can print all a employee can only print their own
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
					QuitText();}
	}}
	return id;
}

//contains the simplifications to above code by taking repeating code and turning those to a method

//the similar process of approve and deny turns out thats the process to get one record
private transaction Singletransaction(userInfo currentUser) {
	int tranid = 0;
	System.out.println("Enter the id of the transaction: ");
	tranid = userIntAnswer();
	transaction updatetran = ReportBL.gettransactionByTranId(tranid);
	if(currentUser.isManager()) {
		confirm(updatetran);
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
	System.out.println("[5] Add new employee");
	QuitText();
}
private void MainEmployeeMenu() {
	System.out.println("Welcome to expense reports");
	System.out.println("[1] Create a reinburstment request");
	System.out.println("[2] Records of requests");
	QuitText();
}
private void MainTypeMenu() {
	System.out.println("please enter type of transaction");
	System.out.println("[1] Lodging");
	System.out.println("[2] Travel");
	System.out.println("[3] Food");
	System.out.println("[4] Other");
	QuitText();
}
private void MainRecordsManagerMenu() {
	System.out.println("[1] list all records");
	System.out.println("[2] list 1 transaction");
	System.out.println("[3] list all approved records");
	System.out.println("[4] list all denied records");
	System.out.println("[5] list all pending records");
	System.out.println("[6] list 1 employee's records");
	QuitText();
}
private void MainRecordsEmployeeMenu() {
	System.out.println("[1] list all your records");
	System.out.println("[2] list 1 transaction");
	System.out.println("[3] list all your approved records");
	System.out.println("[4] list all your denied records");
	System.out.println("[5] list all your pending records");
	QuitText();
}
private void EmployeeIdRequest() {
	System.out.println("Please enter a employee id");
	QuitText();
}
private void QuitText() {
	System.out.println("or enter [x] to quit:");
}

}

