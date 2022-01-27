package com.revature.Expense.dl;

import java.io.IOException;
import java.util.Properties;

public class ConnectionFactory {
	// eager loading - you create the instance of connection factory as soon as
	private static ConnectionFactory connectionFactory = new ConnectionFactory();
	// hold the db config stuff
	private Properties prop = new Properties();
	
	// force load postgresql driver
	//static members of the class get loaded into memoery at the start of program run time
	// static blocks get run at the start
	static {
		try {
			Class.forName("org.postgresql.Driver");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	//singletons are charterized by the private constuctor
	private ConnectionFactory() {
		//loading properties files that contain db config
		try {
			ClassLoader loader = Thread.currentThread().getContextClassLoader();
			prop.load(loader.getResourceAsStream("db.properties"));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	//as well as the getInstance method
	public static ConnectionFactory getInstance() {
		//lazy loading - create instance of connection facotry when you call
		//if connection factory == null connecctionfactory = new connection factory
		return connectionFactory;
	}
}
