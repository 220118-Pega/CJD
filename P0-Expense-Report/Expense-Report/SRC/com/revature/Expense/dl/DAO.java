package com.revature.Expense.dl;

/**
 * this is a generic interface for our dao's
 * @author 16del
 *
 *@param <t> type of object we're creating a dao for
 *@param <K> data type of the id of the object
 */
public interface DAO<T,K> {
	T findById(K id);
	Iterable<T> findAll();
	void add(T newObject);
	void update(T newOject);
}
