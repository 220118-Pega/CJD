package com.revature.Expense;

import java.util.Scanner;

import com.revature.Expense.bl.ReportBL;
import com.revature.Expense.dl.RepoMemory;
import com.revature.Expense.ui.MainMenu;

public class Driver {
	
	//main menu is start of program and starts running the main menu
	public static void main(String[] args) {
		MainMenu menu = new MainMenu(new Scanner(System.in),new ReportBL(new RepoMemory()));
		menu.start();

	}

}
