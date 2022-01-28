package com.revature.Expense.dl;

import java.util.List;

/**
 * this is a generic interface for our dao's
 * @author 16del
 *
 *@param <t> type of object we're creating a dao for
 *@param <K> data type of the id of the object
 */
public interface DAO<T,K> {
	T findByTId(K id);
	T findByUid(K id);
	List<T> findAll();
	void add(T newObject);
	void update(T newOject);
}
