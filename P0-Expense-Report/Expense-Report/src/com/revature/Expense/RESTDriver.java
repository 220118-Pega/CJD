/**
 * 
 */
package com.revature.Expense;

import com.revature.Expense.bl.ReportBL;
import com.revature.Expense.controllers.IController;
import com.revature.Expense.controllers.transactionController;
import com.revature.Expense.controllers.userInfoController;
import com.revature.Expense.dl.DBRepository;
import com.revature.Expense.dl.transactionDAO;
import com.revature.Expense.dl.userInfoDAO;
import com.revature.Expense.utils.Router;

import io.javalin.Javalin;
import io.javalin.plugin.openapi.OpenApiOptions;
import io.javalin.plugin.openapi.OpenApiPlugin;
import io.javalin.plugin.openapi.ui.SwaggerOptions;
import io.swagger.v3.oas.models.info.Info;
/**
 * @author 16del
 *
 */
public class RESTDriver {
	
	public static void main(String[] args) {
		IController transactionController = new transactionController(new ReportBL(new DBRepository(new transactionDAO(), new userInfoDAO())));
		IController userInfoController = new userInfoController(new ReportBL(new DBRepository(new transactionDAO(), new userInfoDAO())));
		
		Javalin app = Javalin.create(config -> {
			config.registerPlugin(new OpenApiPlugin(getOpenApiOptions()));}
		).start(7000);
		
		Router router = new Router(app, transactionController, userInfoController);
		router.setUpEndPoints();
	}
		private static OpenApiOptions getOpenApiOptions() {
			//configuring swagger
			Info applicationInfo = new Info().version("1.0").description("Expense REST");
			return new OpenApiOptions(applicationInfo).path("/swagger-docs")
					.swagger(new SwaggerOptions("/swagger")
							.title("Expense API Docs"));
		}
}