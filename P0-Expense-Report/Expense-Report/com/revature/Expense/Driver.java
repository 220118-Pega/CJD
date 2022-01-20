package com.revature.Expense;

import java.util.Scanner;

import com.revature.Expense.bl.ReportBL;
import com.revature.Expense.dl.RepoMemory;
import com.revature.Expense.ui.MainMenu;

public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//employee id
		//status
		//type
		//amount
		//date
		//reinburstment id
		MainMenu menu = new MainMenu(new Scanner(System.in),new ReportBL(new RepoMemory()));
		menu.start();

	}

}
