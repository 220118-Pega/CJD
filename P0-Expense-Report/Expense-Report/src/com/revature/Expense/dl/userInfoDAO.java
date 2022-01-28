/**
 * 
 */
package com.revature.Expense.dl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.Expense.models.userInfo;
/**
 * @author 16del
 *
 */
public class userInfoDAO implements DAO<userInfo, Integer> {
	private final Logger logger = LogManager.getLogger(this.getClass());
	
	@Override
	public userInfo findByTId(Integer id) {
		// TODO Auto-generated method stub
		// this is just for transactionDAO doesnt apply to user
		return null;
	}

	@Override
	public userInfo findByUid(Integer employeeid) {
		try (Connection conn = ConnectionFactory.getInstance().getConnection()){
			String query = "select * from userInfo where employeeid = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, employeeid);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				return new userInfo(rs.getInt("employeeid"),rs.getString("Name"),rs.getBoolean("isManager"),rs.getInt("Managerid"));
			}
			}catch (SQLException e) {
				e.printStackTrace();
				logger.error("can't connect to server");
			}
			return null;
	}

	@Override
	public List<userInfo> findAll() {
		List<userInfo> users = new ArrayList<userInfo>();
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String query = "select * from userInfo";
			PreparedStatement pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				users.add(new userInfo(rs.getInt("employeeid"),rs.getString("Name"),rs.getBoolean("isManager"),rs.getInt("Managerid")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("error connecting to the database",e);
		}
		return users;
	}

	@Override
	public void add(userInfo newObject) {
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String query = "insert into userInfo (employeeid, Name, isManager, Managerid) values (?,?,?,?);";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, newObject.getEmployid());
			pstmt.setString(2, newObject.getName());
			pstmt.setBoolean(3, newObject.isManager());
			pstmt.setInt(4, newObject.getManagerid());
			pstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("error connecting to the database",e);
		}
		
	}

	@Override
	public void update(userInfo newOject) {
		// TODO Auto-generated method stub
		
	}
	

}
