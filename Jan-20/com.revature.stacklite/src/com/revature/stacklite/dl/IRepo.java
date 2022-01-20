/**
 * 
 */
package com.revature.stacklite.dl;

import java.util.List;

import com.revature.stacklite.models.Issue;

/**
 * This is a interface for my repo implementation
 * contains the necessary methods my BL needs
 * @author 16del
 *
 */
public interface IRepo {
	public void addIssue(Issue newIssue);
	List<Issue> getIssues();
}
