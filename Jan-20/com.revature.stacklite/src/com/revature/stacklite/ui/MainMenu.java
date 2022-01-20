package com.revature.stacklite.ui;

import java.util.Scanner;

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
		}while(keepGoing);
		
	}
}
