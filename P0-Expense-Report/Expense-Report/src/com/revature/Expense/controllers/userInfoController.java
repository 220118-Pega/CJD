/**
 * 
 */
package com.revature.Expense.controllers;

import com.revature.Expense.bl.RReportBL;
import com.revature.Expense.models.userInfo;

import io.javalin.http.Handler;

/**
 * @author 16del
 *
 */
public class userInfoController implements IController {
	private RReportBL reportBL;
	
	public userInfoController(RReportBL reportBL) {
		this.reportBL = reportBL;
	}
	
	@Override
	public Handler getAll() {
		// TODO Auto-generated method stub
		return ctx -> {
			ctx.jsonStream(reportBL.getAllUsers());
		};
	}

	@Override
	public Handler add() {
		return ctx -> {
			reportBL.addemployee(ctx.bodyStreamAsClass(userInfo.class));
		};
	}

	@Override
	public Handler update() {
		return null;
	}

	@Override
	public Handler getByTId() {
		// TODO Auto-generated method stub
		return null;
	}

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
