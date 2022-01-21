package com.revature.Expense.models;

public class transaction {
	//attributes 
	private int transactionid;
	private int userid;
	private float transactionamount;
	private String date;
	private enum transactiontype{ Lodging, Travel, Food, Other,Error}
	private transactiontype type;
	private enum status{ Pending, Approved, Denied}
	private status state;
	
	//setters and getters
	public int getTransactionid() {
		return transactionid;
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
	public float getTransactionamount() {
		return transactionamount;
	}
	public void setTransactionamount(float transactionamount) {
		this.transactionamount = transactionamount;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
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
	public transaction(int userid, float transactionamount, String date) {
		super();
		this.userid = userid;
		this.transactionamount = transactionamount;
		this.date = date;
		this.type = com.revature.Expense.models.transaction.transactiontype.Error;
		this.transactionid = 0;
		this.state =  com.revature.Expense.models.transaction.status.Pending;
	}
//userid,amount,date,type,id,status
	//methods
	public void Approve() {
		this.state = com.revature.Expense.models.transaction.status.Approved;
	}
	public void Deny() {
		this.state = com.revature.Expense.models.transaction.status.Denied;
	}
	
	@Override
	public String toString() {
		return "Report[employee id="+ userid + ", amount=" + transactionamount + ", date=" + date + ", type=" + type.toString() +", transaction id=" + transactionid + ", status=" + state.toString() + "  ]";
	}
}
