package com.revature.Expense.models;

public class transaction {
	//attributes 
	private int transactionid;
	private int userid;
	private float transactionamount;
	private String date;
	private String transactiontype;
	private String status;
	//setters and getters
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
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
		this.status = "pending";
	}
//userid,amount,date,type,id,status
	//methods
	public void Approve() {
		this.setStatus("Approved");
	}
	public void Deny() {
		this.setStatus("Deny");
	}
	
	@Override
	public String toString() {
		return "Report[employee id="+ userid + ", amount=" + transactionamount + ", date=" + date + ", type= " + transactiontype +", transaction id=" + transactionid + ", status=" + status + "  ]";
	}
}
