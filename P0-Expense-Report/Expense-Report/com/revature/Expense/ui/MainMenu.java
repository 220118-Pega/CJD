package com.revature.Expense.ui;

import java.util.Scanner;

import com.revature.Expense.bl.ReportBL;
import com.revature.Expense.models.transaction;

public class MainMenu {
	private Scanner myScanner;
	private ReportBL ReportBL;
	public MainMenu(Scanner myScanner,ReportBL ReportBL) {
		this.myScanner = myScanner;
		this.ReportBL = ReportBL;
	}
	
public void start() {
	boolean keepGoing = true;
	do {
		System.out.println("Welcome to expense reports");
		System.out.println("[1] Create a reinburstment request");
		System.out.println("[2] List all requests");
		System.out.println("[x] Exit");
		
		String userInput = myScanner.nextLine();
		switch(userInput) {
		case "1":
			System.out.println("Filing your report");
			createtransaction();
			break;
		case "2":
			System.out.println("Showing all current requests");
			getAlltransaction();
			break;
		case "x":
			System.out.println("Dismissed");
			keepGoing = false;
			break;
		default:
			System.out.println("Please only put listed options");
			break;
		}
		
	}while(keepGoing);
}

private void getAlltransaction() {
	// TODO Auto-generated method stub
	for(transaction transaction:ReportBL.gettransaction()) {
		System.out.println(transaction);
	}
}

private void createtransaction() {
	// TODO Auto-generated method stub
	//getting all variable info from employee
	System.out.println("Please enter you employee id: ");
	String userid = myScanner.nextLine();
	System.out.println("Please enter transaction amount: ");
	String transactionamount = myScanner.nextLine();
	System.out.println("Please enter date of transaction: ");
	String date = myScanner.nextLine();
	System.out.println("please enter type of transaction: ");
	String transactiontype = myScanner.nextLine();
	
	//inserting into transaction
	transaction newReport = new transaction(Integer.parseInt(userid),Float.parseFloat(transactionamount),date, transactiontype);
	//saving
	ReportBL.addtransaction(newReport);
	System.out.println(newReport);
	
}
}