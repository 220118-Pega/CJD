/**
 * 
 */
package com.revature.Expense.controllers;

import com.revature.Expense.bl.RReportBL;
import com.revature.Expense.models.userInfo;

import io.javalin.http.Handler;

/**
 * handles router for userInfo
 * @author 16del
 *
 */
public class userInfoController implements IController {
	private RReportBL reportBL;
	
	public userInfoController(RReportBL reportBL) {
		this.reportBL = reportBL;
	}
	//returns all users from database
	@Override
	public Handler getAll() {
		return ctx -> {
			ctx.jsonStream(reportBL.getAllUsers());
		};
	}
	//addes a employee to the database
	@Override
	public Handler add() {
		return ctx -> {
			reportBL.addemployee(ctx.bodyStreamAsClass(userInfo.class));
		};
	}
	//currently nothing to update about employee's so not implented
	@Override
	public Handler update() {
		return null;
	}
	//only applies to transactions so not implented
	@Override
	public Handler getByTId() {
		return null;
	}
	//returns specific employee based on id
	@Override
	public Handler getByUId() {
		return ctx -> {
			String id = ctx.pathParam("employid");
			int actualid = Integer.parseInt(id);
			try {
				ctx.jsonStream(reportBL.getUserById(actualid));
			}catch(NullPointerException ex) {
				ctx.status(204);
			}
		};
	}
	
}
