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
	
	public DBRepository( DAO<transaction,Integer> tranDAO, DAO<userInfo, Integer> userDAO) {
		this.tranDAO = tranDAO;
		this.userDAO = userDAO;
	}
	
	@Override
	public void addtransaction(transaction newtran) {
		tranDAO.add(newtran);
		
	}
/* was a testing method with database no longer needed
	@Override//this was testing until data base came can delte once database is connected
	public void IntializeEmployees() {
		// TODO Auto-generated method stub
		
	}
*/
	@Override
	public List<transaction> gettransaction() {
		return tranDAO.findAll();
	}

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

	@Override
	public void addemployee(userInfo newuser) {
		userDAO.add(newuser);
		
	}

	@Override
	public userInfo getUserById(int id) {
		userInfo wanteduser = userDAO.findByUid(id);
		return wanteduser;
	}

}
