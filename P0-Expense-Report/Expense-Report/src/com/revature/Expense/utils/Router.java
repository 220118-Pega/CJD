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
		app.get("/Transactions",OpenApiBuilder.documented(DocumentationFacotry.getDoc("getTransaction"),transactionController.getAll()));
		app.get("/Transaction/{transactionid}", OpenApiBuilder.documented(DocumentationFacotry.getDoc("getTransactionById"), transactionController.getByTId()));
		app.post("/Transactions", OpenApiBuilder.documented(DocumentationFacotry.getDoc("addTransaction"), transactionController.add()));
		app.post("/UserInfo/{userInfoid}", OpenApiBuilder.documented(DocumentationFacotry.getDoc("addUser"), userInfoController.add()));
		app.get("/Users", OpenApiBuilder.documented(DocumentationFacotry.getDoc("getUsers"), userInfoController.getAll()));
		app.get("/UserInfo/{userInfoid}", OpenApiBuilder.documented(DocumentationFacotry.getDoc("getUserById"), userInfoController.getByUId()));
	}
}
