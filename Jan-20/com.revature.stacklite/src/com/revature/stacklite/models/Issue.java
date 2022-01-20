package com.revature.stacklite.models;

/**
 * this is a model for coding issues
 * @author 16del
 *
 */
public class Issue {
// class = blueprint
	// member = attributes, methods, constructors
	
//attributes
	// used to define characteristics of a class
	// used to define dependcies of the class
	private String title;
	private String description;
	private int id;
	
// constuctos
	//methods used to initalize properites of a class
	// default constructor exist
	//super calls the constructor of the base class is implicitly used in this case
	public Issue() {super();}
	public Issue(String title, String description) {
		//this keyword pertains to the particular instance title and descrition properties
		//title attribute of the object being constructed will have the value of the title parameter i am passing
		this.title = title;
		this.description = description;
	}
	public Issue(String title, String description, int id) {
		//calling a existing constructor of the same class is constructor chaining
		this(title,description);
		this.id = id;
	}
	//multiple constrotrs are polymorphism called method overloading
	
//methods
	//getters and setters
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		//you can intrtoduce valadation logic here
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getId() {
		return id;
	}
}
