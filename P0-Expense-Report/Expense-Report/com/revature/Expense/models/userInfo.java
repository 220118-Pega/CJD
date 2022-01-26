package com.revature.Expense.models;

public class userInfo {
 //attributes 
	private int employid;
	private String Name;
	private boolean isManager;
	private int Managerid;
	//getters and setters
	public int getEmployid() {
		return employid;
	}
	public void setEmployid(int employid) {
		this.employid = employid;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	//feel like should make it private or modify it to check status of user
	public boolean isManager() {
		return isManager;
	}
	public void setManager(boolean isManager) {
		this.isManager = isManager;
	}
	public int getManagerid() {
		return Managerid;
	}
	public void setManagerid(int managerid) {
		Managerid = managerid;
	}
	//constructor
	
	public userInfo(String Name, int Mid) {
		this.employid = 0;
		this.Name = Name;
		this.isManager = false;
		this.Managerid = Mid;
	}
	public userInfo(int empid, String Name,  boolean isM, int Mid) {
		this.employid = empid;
		this.Name = Name;
		this.isManager = isM;
		this.Managerid = Mid;
	}
}
/*
 * how to use userinfo
 * 
 * first initialize with a preset thing of users
 * some managers and god
 * at start have person enter employid check against list to see if they are real employee
 * next check if manager to determine how much of menu they have access too
 * finally if manager check if god
 * 
 * if regular employee they can create a report, can show list of all their transactions/ can show list of all their transactions depending on status
 * 
 * if manager they can do everything a regular employee can do
 * they gain access to approve, deny by transaction id
 * they can add additional regular employee to system
 *
 * if manager id is god level they can change users into managers
 * only god can make another god
 * 
 * */

