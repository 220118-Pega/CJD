/**
 * 
 */
package com.revature.Expense.utils;

import com.revature.Expense.models.transaction;

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
			return OpenApiBuilder.document()operation(op -> 
			{op.addTagItem("transaction");})
					.jsonArray("200", transaction.class);
		case "getTransactionById":
			return OpenApiBuilder.document()operation(op -> 
			{op.addTagItem("transaction");})
					.jsonArray("200", transaction.class);
		case "addTransaction":
			return OpenApiBuilder.document()operation(op -> 
			{op.addTagItem("transaction");})
					.body(transaction.class).result("201");
		default:
			return null;
		}
	}
}
