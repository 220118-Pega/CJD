/**
 * 
 */
package com.revature.Expense.dl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.Expense.models.transaction;
/**
 * @author 16del
 *
 */
public class transactionDAO implements DAO<transaction, Integer> {
	private final Logger logger = LogManager.getLogger(this.getClass());
	@Override
	public transaction findById(Integer id) {
		//try with resources block after the try block finishes excuting
		//disposes of the resources for you
		try(Connection conn = ConnectionFactory.getInstance().getConnection());{
			// ? is a placeholder for a parameter we'll be sending our DB
			String query = "select * from issues where id = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);
			//excutequery used for executing select commands
			//result set holds the results from the db
			ResultSet rs = pstmt.executeQuery();
			//we need to unpack the results to return something to end user
			if(rs.next()) {
				return new public transaction(rs.getInt("userid"), rs.getDouble("transactionamount"), rs.getString("date"), rs.getString("description"));//probably double check names
				
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
			logger.error("Error with connecting to the DB");
		}
		return null;
	}

	@Override
	public Iterable<transaction> findAll() {
		List<transaction> trans = new ArrayList<transaction>();
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String query = "select * from transactions";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				trans.add(new transaction(rs.getInt("userid"), 
						rs.getDouble("transactionamount"), 
						rs.getString("date"), 
						rs.getString("description")))
			}
		}catch(SQLException e) {
			e.printStackTrace();
			logger.error("Someting went wrong in findAll",e);
		}
		return null;
	}

	@Override
	public void add(transaction newObject) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(transaction newOject) {
		// TODO Auto-generated method stub
		
	}

}
