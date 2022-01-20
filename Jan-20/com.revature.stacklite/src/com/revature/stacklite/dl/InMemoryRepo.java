package com.revature.stacklite.dl;

import java.util.ArrayList;
import java.util.List;

import com.revature.stacklite.models.Issue;

/**
 * repo that connects to a in memory storage, ie a static list of issues and solutions
 * @author 16del
 *
 */
public class InMemoryRepo implements IRepo {
	private static List<Issue> listofIssues;
	private static int LatestId;
	
	public InMemoryRepo() {
		listofIssues = new ArrayList<Issue>() {{
			//seeding list of issues with dummy data
			new Issue("Code doesn't work, why??", "My code doesn't work i dont know why", 1);
			new Issue("Code work, why??", "My code works i dont know why", 2);
		}};
		LatestId = 3;
	}

	@Override
	public void addIssue(Issue newIssue) {
		// TODO Auto-generated method stub
		newIssue.setId(LatestId);
		listofIssues.add(newIssue);
		LatestId++;
	}

	@Override
	public List<Issue> getIssues() {
		// TODO Auto-generated method stub
		return this.listofIssues;
	}

}
