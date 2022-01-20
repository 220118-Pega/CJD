package com.revature.stacklite.dl;

import java.util.ArrayList;
import java.util.List;

import com.revature.stacklite.models.Issue;
import com.revature.stacklite.models.Solution;

/**
 * repo that connects to a in memory storage, ie a static list of issues and solutions
 * @author 16del
 *
 */
public class InMemoryRepo implements IRepo {
	private static List<Issue> listofIssues;
	private static int LatestId;
	
	public InMemoryRepo() {
		List<Solution> solutionListA = new ArrayList<Solution>() {{
			add(new Solution("maybe read through the errors?",2,1));
			add(new Solution("maybe read through the tears?",0,2));
		}};
		listofIssues = new ArrayList<Issue>() {{
			//seeding list of issues with dummy data
			new Issue("Code doesn't work, why??", "My code doesn't work i dont know why", 1, solutionListA);
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

	@Override
	public Issue getIssuebyId(int id) throws Exception {
		// TODO Auto-generated method stub
		Issue foundIssue = null;
		for(Issue issue:listofIssues) {
			if(issue.getId() == id) {
				foundIssue = issue;
			}
		}
		if(foundIssue == null) throw new Exception("Issue not found");
		return foundIssue;
	}

	@Override
	public void addSolution(Solution newSolution) {
		// TODO Auto-generated method stub
		Issue issue2Update = getIssuebyId(newSolution.getIssueId());
		List<Solution> existingSolutions = issue2Update.getSolution();
		if(existingSolutions == null) existingSolutions = new ArrayList<Solution>();
		existingSolutions.add(newSolution);
		issue2Update.setSolution(existingSolutions);
		
	}

}
