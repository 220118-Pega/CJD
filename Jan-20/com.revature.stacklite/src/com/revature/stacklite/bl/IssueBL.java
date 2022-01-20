package com.revature.stacklite.bl;

import java.util.List;

import com.revature.stacklite.dl.IRepo;
import com.revature.stacklite.models.Issue;

public class IssueBL implements IIssueBL {
	private IRepo repo;
	public IssueBL(IRepo repo) {
		this.repo =repo;
	}
	@Override
	public void addIssue(Issue issue) {
		// call my dl
		repo.addIssue(issue);
		
	}
	@Override
	public List<Issue> getIssues(){
		return repo.getIssues();
	}
}
