/**
 * 
 */
package com.revature.Expense.utils;

import com.revature.Expense.controllers.IController;

import io.javalin.Javalin;
import io.javalin.plugin.openapi.dsl.OpenApiBuilder;

/**
 * class in charge of mapping endpoint to the controller methods that would handle the http requests
 * @author 16del
 *
 */
public class Router {
	private Javalin app;
	private IController transactionController;
	private IController userInfoController;
	
	public Router(Javalin app, IController transactionController, IController userInfoController) {
		//your router is the front controller in the front controller design pattern
		this.app = app;
		this.transactionController = transactionController;
		this.userInfoController = userInfoController;
	}
	public void setUpEndPoints() {
		//get all transactions
		app.get("/Transactions",OpenApiBuilder.documented(DocumentationFacotry.getDoc("getTransaction"),transactionController.getAll()));
		//get 1 transaction
		app.get("/Transactions/{transactionid}", OpenApiBuilder.documented(DocumentationFacotry.getDoc("getTransactionById"), transactionController.getByTId()));
		//add a transaction to database
		app.post("/Transactions", OpenApiBuilder.documented(DocumentationFacotry.getDoc("addTransaction"), transactionController.add()));
		//add a user to database
		app.post("/UserInfo/{Name}", OpenApiBuilder.documented(DocumentationFacotry.getDoc("addUser"), userInfoController.add()));
		//get all users
		app.get("/Users", OpenApiBuilder.documented(DocumentationFacotry.getDoc("getUsers"), userInfoController.getAll()));
		//get 1 user
		app.get("/UserInfo/{employid}", OpenApiBuilder.documented(DocumentationFacotry.getDoc("getUserById"), userInfoController.getByUId()));
	}
}
