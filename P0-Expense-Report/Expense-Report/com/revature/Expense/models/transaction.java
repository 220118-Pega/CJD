package com.revature.Expense.models;

public class transaction {
	//attributes 
	private int transactionid;
	private int userid;
	private float transactionamount;
	private String date;
	private String transactiontype;
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
	public String getTransactiontype() {
		return transactiontype;
	}
	public void setTransactiontype(String transactiontype) {
		this.transactiontype = transactiontype;
	} 
	//constructors
	public transaction(int userid, float transactionamount, String date, String transactiontype) {
		super();
		this.userid = userid;
		this.transactionamount = transactionamount;
		this.date = date;
		this.transactiontype = transactiontype;
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
		return "Report[employee id="+ userid + ", amount=" + transactionamount + ", date=" + date + ", type= " + transactiontype +", transaction id=" + transactionid + ", status=" + state.toString() + "  ]";
	}
}
