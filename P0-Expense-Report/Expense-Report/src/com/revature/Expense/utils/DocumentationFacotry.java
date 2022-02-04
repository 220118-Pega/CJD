/**
 * 
 */
package com.revature.Expense.utils;

import com.revature.Expense.models.transaction;
import com.revature.Expense.models.userInfo;

import io.javalin.plugin.openapi.dsl.OpenApiBuilder;
import io.javalin.plugin.openapi.dsl.OpenApiDocumentation;

/**
 * return documentation for my endpoints
 * @author 16del
 *
 */
public class DocumentationFacotry {
	
	public static OpenApiDocumentation getDoc(String endPoint) {
		switch(endPoint) {
		//get all transactions
		case "getTransaction":
			return OpenApiBuilder.document()
					.operation(op -> 
			{op.addTagsItem("Transaction");
			}).jsonArray("200", transaction.class);
		//get 1 transaction by id	
		case "getTransactionById":
			return OpenApiBuilder.document().operation(op -> 
			{op.addTagsItem("Transaction");})
					.jsonArray("200", transaction.class);
		//add transaction to database	
		case "addTransaction":
			return OpenApiBuilder.document().operation(op -> 
			{op.addTagsItem("Transaction");})
					.body(transaction.class).result("201");
		//add user to database
		case "addUser":
			return OpenApiBuilder.document().operation(op ->{
				op.addTagsItem("User");
			}).body(userInfo.class).result("201");
		//get all users from database	
		case "getUsers":
			return OpenApiBuilder.document().operation(op ->{
				op.addTagsItem("User");
			}).json("200", userInfo.class);
		//get 1 user by employee id
		case "getUserById":
			return OpenApiBuilder.document().operation(op -> {
				op.addTagsItem("User");
			}).jsonArray("200", userInfo.class);
			
		default:
			return null;
		}
	}
}
