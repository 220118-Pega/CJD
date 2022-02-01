/**
 * 
 */
package com.revature.Expense.controllers;

import com.revature.Expense.bl.RReportBL;
import com.revature.Expense.models.transaction;

import io.javalin.http.Handler;

/**
 * @author 16del
 *
 */
public class transactionController implements IController {

	private RReportBL reportBL;
	
	public transactionController (RReportBL reportBL) {
		this.reportBL = reportBL;
	}
	@Override
	public Handler getAll() {
		// a means to pass functions as parameters
		// we'll be returning a lambda function
		//as an implementation for this fcnal interface
		// the lambda we'll be returning is how we want 
		//our httprequest to be handled
		//the response gets set to json
		return ctx -> {
			ctx.jsonStream(reportBL.gettransaction());
		};
	}

	@Override
	public Handler getByTId() {
		return ctx ->{
			//get id of transaction we want from the path param,
			// swe extract if from the ctx
			String id = ctx.pathParam("transactionid");
			int actualId = Integer.parseInt(id);
			try {
			ctx.jsonStream(reportBL.gettransactionByTranId(actualId));
		}catch(NullPointerException e)
		{
		 ctx.res.setStatus(204);
		}
		};
		}

	@Override
	public Handler add() {
		return ctx -> {
			//unmarshall request body into an transaction class
			//bodyAsClass method unmarshalls the request body into the structure of the class you input into it
			transaction newtran = ctx.bodyStreamAsClass(transaction.class);
			try {
				reportBL.addtransaction(newtran);
				ctx.status(201);
			}catch (Exception e) {
				ctx.status(400);
			}
		};
	}

	@Override
	public Handler update() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Handler getByUId() {
		return ctx ->{
			//get id of transaction we want from the path param,
			// swe extract if from the ctx
			String id = ctx.pathParam("employid");
			int actualId = Integer.parseInt(id);
			try {
			ctx.jsonStream(reportBL.gettransactionByUserId(actualId));
		}catch(NullPointerException e)
		{
		 ctx.res.setStatus(204);
		}
		};
	}

}
