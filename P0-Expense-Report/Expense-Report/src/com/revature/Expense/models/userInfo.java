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
	public userInfo() {}
	//standard constructor when making new employee
	public userInfo(String Name, int Mid) {
		this.employid = 0;
		this.Name = Name;
		this.isManager = false;
		this.Managerid = Mid;
	}
	//only used by IntializeEmployees in order to make all the users already in the system
	public userInfo(int empid, String Name,  boolean isM, int Mid) {
		this.employid = empid;
		this.Name = Name;
		this.isManager = isM;
		this.Managerid = Mid;
	}
}

