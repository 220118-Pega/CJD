/**
 * 
 */
package com.revature.Expense.dl;

import java.util.ArrayList;
import java.util.List;

import com.revature.Expense.models.transaction;
import com.revature.Expense.models.userInfo;

/**
 * @author 16del
 *
 */
public class DBRepository implements IRepo {
	private DAO<transaction,Integer> tranDAO;
	private DAO<userInfo, Integer> userDAO;
	//creates DBRepository with DAO for the differnt tables
	public DBRepository( DAO<transaction,Integer> tranDAO, DAO<userInfo, Integer> userDAO) {
		this.tranDAO = tranDAO;
		this.userDAO = userDAO;
	}
	//forwards add a transaction to database request
	@Override
	public void addtransaction(transaction newtran) {
		tranDAO.add(newtran);
		
	}
	//returns all transactions in database
	@Override
	public List<transaction> gettransaction() {
		return tranDAO.findAll();
	}
	//returns transaction based on id returns null if not in database
	@Override
	public transaction gettransactionByTranId(int id) {
		// the bl requires isn't the version of the transaction
		// that the transaction DAO.findbyid returns
		// what you need is the transaction with the userInfo connected to it
		transaction tranwanted = tranDAO.findByTId(id);
		return tranwanted;
/*this seems to apply more to my get by employee id
 * 		List<userInfo> all users = userInfoDAO.findAll();
 * 		tranwanted.setuser(allusers.stream().filer(
 * 	//single line above is equivalent to
 *  List<users> user4transaction
 * */
		
	}
	//gets all transactions then adds the ones that match userid to the return list returns empty list if no match\ note will have error if database is empty
	@Override
	public List<transaction> gettransactionByUserId(int id) {
		List<transaction> alltran = tranDAO.findAll();
		List<transaction> usertran = new ArrayList<transaction>();
		for(transaction tran:alltran) {
			if(tran.getUserid() == id) {
				usertran.add(tran);
			}
		}
		return usertran;
	}
	//forward request to add employee to database to userdao
	@Override
	public void addemployee(userInfo newuser) {
		userDAO.add(newuser);
		
	}
	//gets user information by id returns null if non there
	@Override
	public userInfo getUserById(int id) {
		userInfo wanteduser = userDAO.findByUid(id);
		return wanteduser;
	}
	//returns all users from database
	@Override
	public List<userInfo> getAllUsers() {
		return userDAO.findAll();
	}

	//updates the state of a transaction in the databse
	@Override
	public void updateState(transaction updatetran) {
		tranDAO.updateState(updatetran);
		
	}

	//returns transaction with largest transaction id that should be the last one to be added
	@Override
	public transaction getLatesttransaction() {
		return tranDAO.getLatest();
	}

}
