/**
 * 
 */
package com.revature.Expense.controllers;

import io.javalin.http.Handler;

/**
 * how controllers handle http requests
 * @author 16del
 *
 */
public interface IController {
	Handler getAll();
	Handler getById();
	Handler add();
	Handler update();
	
}
