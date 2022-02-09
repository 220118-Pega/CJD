package com.revature.Expense.models;


/**
 * transaction objects hold the information about the transaction
 * @author 16del
 *likely will need to outsource user id from it
 */
public class transaction {
	
	//attributes 
	private int transactionid;
	private int userid;
	private double transactionamount;
	private String date;
	private String descritpion;
	private enum transactiontype{ Lodging, Travel, Food, Other,Error}
	private transactiontype type;
	private enum status{ Pending, Approved, Denied}
	private status state;
	
	//setters and getters
	public int getTransactionid() {
		return transactionid;
	}
	public transactiontype getType() {
		return type;
	}
	public String getTypename() {
		return this.type.name();
	}
	public void setType(transactiontype type) {
		this.type = type;
	}
	public status getState() {
		return state;
	}
	public String getStatename() {
		return this.state.name();
	}
	public void setState(status state) {
		this.state = state;
	}
	public String getDescritpion() {
		return descritpion;
	}
	public void setDescritpion(String descritpion) {
		this.descritpion = descritpion;
	}
	public void setTransactionid(int transactionid) {
		this.transactionid = transactionid;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public double getTransactionamount() {
		return transactionamount;
	}
	public void setTransactionamount(double transactionamount) {
		this.transactionamount = transactionamount;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	//enum setters for differnt states
	public void StringStateSet(String state) {
		switch(state) {
		
		case "Pending":
			this.Pending();
			break;
		case "Approved":
			this.Approve();
			break;
		case "Denied":
			this.Deny();
			break;
		}
	}
	public void Pending() {
		this.state = com.revature.Expense.models.transaction.status.Pending;
	}
	public void Approve() {
		this.state = com.revature.Expense.models.transaction.status.Approved;
	}
	public void Deny() {
		this.state = com.revature.Expense.models.transaction.status.Denied;
	}
	//enum setters for different typs
	public void StringTypeSet(String type) {
		switch(type) {
		case "Lodging":
			this.Lodging();
			break;
		case "Travel":
			this.Travel();
			break;
		case "Food":
			this.Food();
			break;
		case "Other":
			this.Other();
			break;
		}
	}
	public void Lodging() {
		this.type = com.revature.Expense.models.transaction.transactiontype.Lodging;
	}
	public void Travel() {
		this.type = com.revature.Expense.models.transaction.transactiontype.Travel;
	}
	public void Food() {
		this.type = com.revature.Expense.models.transaction.transactiontype.Food;
	}
	public void Other() {
		this.type = com.revature.Expense.models.transaction.transactiontype.Other;
	}
	
	//constructors
	public transaction() {}
	public transaction(int userid, double transactionamount, String date, String descritpion) {
		super();
		this.userid = userid;
		this.transactionamount = transactionamount;
		this.date = date;
		this.descritpion = descritpion;
		this.type = com.revature.Expense.models.transaction.transactiontype.Error;
		this.transactionid = 0;
		this.state =  com.revature.Expense.models.transaction.status.Pending;
	}
	//for the DAO
	public transaction(int userid, double transactionamount, String date, String descritpion,transactiontype type, status state,int transactionid) {
		this.userid = userid;
		this.transactionamount = transactionamount;
		this.date = date;
		this.descritpion = descritpion;
		this.type = type;
		this.transactionid = 0;
		this.state = state;
		this.transactionid = transactionid;
	}
	
	//methods
	//print out transactions in certain setting
	@Override
	public String toString() {
		return "Report[employee id="+ userid + ", amount=" + transactionamount + ", date=" + date + ", type=" + type.toString() +", transaction id=" + transactionid + ", status=" + state.toString() + ", descritpion=" + descritpion + " ]";
	}
}
