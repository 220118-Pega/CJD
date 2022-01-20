package com.revature.stacklite.ui;

import java.util.Scanner;

import com.revature.stacklite.models.Issue;

/**
 * Class to present UI to end users to interact with program
 * @author 16del
 *
 */
public class MainMenu {
	//the scanner is very important to a UI
	//declare it as a dependency 
	private Scanner myScanner;
	//inject this dep via constructor
	public MainMenu(Scanner myScanner) {
		this.myScanner = myScanner;
	}
	
	public void start() {
		//method signature: access modifier*, non access modifier*, return type, methodName, {arguments}, throws exceptions*
		boolean keepGoing = true;
		do {
			System.out.println("Welcome to Stacklite, what do you want to do");
			System.out.println("[0] Create an issue");
			System.out.println("[x] Exit");
			
			String userInput = myScanner.nextLine();
			switch (userInput) {
			case "0":
				System.out.println("Creating an issue");
				createIssue();
				break;
			case "x":
				System.out.println("Goodbye");
				keepGoing = false;
				break;
			default:
				System.out.println("Sorry wrong input, please try again");
				break;
			}
		}while(keepGoing);
		
	}

	private void createIssue() {
		
		System.out.println("Enter a title for your issue: ");
		String title = myScanner.nextLine();
		System.out.println("Enter a description for your issue: ");
		String description = myScanner.nextLine();
		Issue newIssue = new Issue(title,description);
		//saving to storage
		System.out.println(newIssue);
		
	}
}
