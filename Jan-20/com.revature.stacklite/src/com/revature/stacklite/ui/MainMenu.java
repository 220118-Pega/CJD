package com.revature.stacklite.ui;

import java.util.Scanner;

import com.revature.stacklite.bl.IIssueBL;
import com.revature.stacklite.models.Issue;
import com.revature.stacklite.models.Solution;

/**
 * Class to present UI to end users to interact with program
 * @author 16del
 *
 */
public class MainMenu {
	//the scanner is very important to a UI
	//declare it as a dependency 
	private Scanner myScanner;
	private IIssueBL issueBL;
	//inject this dep via constructor
	public MainMenu(Scanner myScanner,IIssueBL issueBL) {
		this.myScanner = myScanner;
		this.issueBL = issueBL;
	}
	
	public void start() throws Exception {
		//method signature: access modifier*, non access modifier*, return type, methodName, {arguments}, throws exceptions*
		boolean keepGoing = true;
		do {
			System.out.println("Welcome to Stacklite, what do you want to do");
			System.out.println("[0] Create an issue");
			System.out.println("[1] Get all Issues");
			System.out.println("[2] View issue with proposed solution");
			System.out.println("[3] Add solution to issue");
			System.out.println("[x] Exit");
			
			String userInput = myScanner.nextLine();
			switch (userInput) {
			case "0":
				System.out.println("Creating an issue");
				createIssue();
				break;
			case "1":
				System.out.println("Getting issues..");
				getIssues();
				break;
			case "2":
				getSpecificIssue();
				break;
			case "3":
				addSolution();
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
	private void addSolution() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Enter the id of the issue you'd like to add a solution to: ");
		String stringid = myScanner.nextLine();
		System.out.println("Enter the answer to your proposed solution");
		String answer = myScanner.nextLine();
		Solution newSolution = new Solution(answer);
		newSolution.setIssueId(Integer.parseInt(stringid));
		issueBL.addSolution(newSolution);
		try {
			newSolution.setIssueId(Integer.parseInt(stringId));
			issueBL.addSolution(newSolution);
		}catch (NumberFormatException ex) {
			System.out.println("Please only enter numerics");
		}catch(Exception)
		}
	}

	private void getSpecificIssue() {
		// TODO Auto-generated method stub
		System.out.println("Enter the id of the issue you'd like to view the solutions for: ");
		String stringId = myScanner.nextLine();
		//integer.parseInt() is used to parse strings to integers
		Issue foundIssue = issueBL.getIssueById(Integer.parseInt(stringId));
		System.out.println(foundIssue);
		for(Solution solution:foundIssue.getSolution()) {
			System.out.println(solution);
			
		}
		catch (NullPointerException e) {
			//we don't want null pointer exception to stop our program, so we implement a try catch block to handle this exception
			System.out.println("No such issue found, try another id");
		}
		
	}

	private void getIssues() {
		for(Issue issue:issueBL.getIssues()) {
			System.out.println(issue);
		}
	}

	private void createIssue() {
		
		System.out.println("Enter a title for your issue: ");
		String title = myScanner.nextLine();
		System.out.println("Enter a description for your issue: ");
		String description = myScanner.nextLine();
		Issue newIssue = new Issue(title,description);
		//saving to storage
		issueBL.addIssue(newIssue);
		System.out.println(newIssue);
		
	}
}
