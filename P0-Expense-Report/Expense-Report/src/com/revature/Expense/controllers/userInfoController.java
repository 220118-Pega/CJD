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
		return null;
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
		// TODO Auto-generated method stub
		return null;
	}
	
}
