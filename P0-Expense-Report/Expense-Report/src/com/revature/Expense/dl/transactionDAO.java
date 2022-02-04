/**
 * 
 */
package com.revature.Expense.dl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.Expense.models.SortbyTid;
import com.revature.Expense.models.transaction;
/**
 * @author 16del
 *
 */
public class transactionDAO implements DAO<transaction, Integer> {
	private final Logger logger = LogManager.getLogger(this.getClass());
	
	@Override
	public transaction findByTId(Integer Tid) {
		//try with resources block after the try block finishes excuting
		//disposes of the resources for you
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			// ? is a placeholder for a parameter we'll be sending our DB
			String query = "select * from transactions where transactionid = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, Tid);
			//excutequery used for executing select commands
			//result set holds the results from the db
			ResultSet rs = pstmt.executeQuery();
			//we need to unpack the results to return something to end user
			if(rs.next()) {
				transaction found = buildNewTran(rs);
				return found;
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
			logger.error("Error with connecting to the DB");
		}
		return null;
	}

	@Override
	public List<transaction> findAll() {
		List<transaction> trans = new ArrayList<transaction>();
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String query = "select * from transactions";
			PreparedStatement stmt = conn.prepareStatement(query);
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				//easy to get
				trans.add(buildNewTran(rs));
			}
		}catch(SQLException e) {
			e.printStackTrace();
			logger.error("Someting went wrong in findAll",e);
		}
		Collections.sort(trans, new SortbyTid());
		return trans;
	}

	@Override
	public void add(transaction newObject) {
		try(Connection conn = ConnectionFactory.getInstance().getConnection())
				{
					String query = "insert into transactions (employid, transactionamount, date, descrtiption, tiep, state) values (?, ?, ?, ?, ?::transactiontype,?::status);";
					PreparedStatement pstmt = conn.prepareStatement(query);
					pstmt.setInt(1, newObject.getUserid());
					pstmt.setDouble(2, newObject.getTransactionamount());
					pstmt.setString(3, newObject.getDate());
					pstmt.setString(4, newObject.getDescritpion());
					pstmt.setString(5, newObject.getTypename());
					pstmt.setString(6, newObject.getStatename());
					pstmt.execute();
				} catch (SQLException e) {
					e.printStackTrace();
					logger.error("trouble adding", e);
				}
		
	}

	@Override
	public void updateState(transaction newObject) {
		try(Connection conn = ConnectionFactory.getInstance().getConnection())
		{
			String query = "update transactions set state = ?::status where transactionid = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, newObject.getStatename());
			pstmt.setInt(2, newObject.getTransactionid());
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("trouble adding", e);
		}
		
	}

	@Override
	public transaction findByUid(Integer Uid) {
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String query = "select * from issues where userid = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, Uid);
			ResultSet rs = pstmt.executeQuery();
		if(rs.next()) {
			return new transaction(rs.getInt("userid"), 
					rs.getDouble("transactionamount"), 
					rs.getString("date"), 
					rs.getString("description"));
		}
		
	}catch (SQLException e) {
		e.printStackTrace();
		logger.error("Error with connecting to the DB");
	}
	return null;
	}

	private transaction buildNewTran(ResultSet rs) throws SQLException {
		int Eid = rs.getInt("employid");
		double TA = rs.getDouble("transactionamount");
		String Date = rs.getString("date");
		String Des = rs.getString("descrtiption");
		transaction Ntran = new transaction(Eid,TA,Date,Des);
		Ntran.setTransactionid(rs.getInt("transactionid"));
		//more annoying to get
		Ntran.StringTypeSet(rs.getString("tiep"));
		Ntran.StringStateSet(rs.getString("state"));
		return Ntran;
	}

	@Override
	public transaction getLatest() {
		List<transaction> AllTran = findAll();
		transaction LatestTran = new transaction(0,0,"zero","zero");
		if(AllTran.size() > 0) {
			LatestTran = AllTran.get(AllTran.size() - 1);
		}
		return LatestTran;
	}
}
