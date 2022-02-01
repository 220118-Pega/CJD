/**
 * 
 */
package com.revature.Expense.utils;

import com.revature.Expense.controllers.IController;

import io.javalin.Javalin;
import io.javalin.plugin.openapi.dsl.OpenApiBuilder;

/**
 * @author 16del
 *
 */
public class Router {
	private Javalin app;
	private IController transactionController;
	
	public Router(Javalin app, IController transactionController) {
		this.app = app;
		this.transactionController = transactionController;
	}
	public void setUpEndPoints() {
		app.get("/Transactions",OpenApiBuilder.documented(DocumentationFacotry.getDoc("getTransaction"),transactionController.getAll()));
		app.get("/Transaction/{transactionid}", OpenApiBuilder.documented(DocumentationFacotry.getDoc("getTransactionId"), transactionController.getById()));
		app.post("/Transactions", OpenApiBuilder.documented(DocumentationFacotry.getDoc("addTransaction"), transactionController.add()));
	}
}
