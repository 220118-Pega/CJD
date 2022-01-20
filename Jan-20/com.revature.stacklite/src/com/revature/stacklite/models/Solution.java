package com.revature.stacklite.models;

public class Solution {
	private String answer;
	private int id;
	private int upvote;
	
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUpvote() {
		return upvote;
	}
	public void setUpvote(int upvote) {
		this.upvote = upvote;
	}
	public Solution() {}
	public Solution(String answer, int upvote) {
		this.answer = answer;
		this.upvote = 0;
	}
	public Solution(String answer) {
		this.answer = answer;
		this.upvote = 0;
	}
	@Override
	public String toString() {
		return "Solution [answer=" + answer + ", id=" + id + ", upvote=" + upvote + "]";
	}
}
