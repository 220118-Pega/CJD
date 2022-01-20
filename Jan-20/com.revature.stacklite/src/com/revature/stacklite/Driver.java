package com.revature.stacklite;

import java.util.Scanner;

import com.revature.stacklite.models.*;
import com.revature.stacklite.ui.MainMenu;

public class Driver {

	public static void main(String[] args) {
		
		MainMenu menu = new MainMenu(new Scanner(System.in));
		menu.start();
	}

}
