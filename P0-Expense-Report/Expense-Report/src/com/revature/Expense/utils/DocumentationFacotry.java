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
		
		case "getTransaction":
			return OpenApiBuilder.document()
					.operation(op -> 
			{op.addTagsItem("Transaction");
			}).jsonArray("200", transaction.class);
			
		case "getTransactionById":
			return OpenApiBuilder.document().operation(op -> 
			{op.addTagsItem("Transaction");})
					.jsonArray("200", transaction.class);
			
		case "addTransaction":
			return OpenApiBuilder.document().operation(op -> 
			{op.addTagsItem("Transaction");})
					.body(transaction.class).result("201");
		
		case "addUser":
			return OpenApiBuilder.document().operation(op ->{
				op.addTagsItem("User");
			}).body(userInfo.class).result("201");
			
		case "getUsers":
			return OpenApiBuilder.document().operation(op ->{
				op.addTagsItem("User");
			}).json("200", userInfo.class);
		
		case "getUserById":
			return OpenApiBuilder.document().operation(op -> {
				op.addTagsItem("User");
			}).jsonArray("200", userInfo.class);
			
		default:
			return null;
		}
	}
}
