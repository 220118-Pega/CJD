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
	Handler getByTId();
	Handler getByUId();
	Handler add();
	Handler update();
	
}
