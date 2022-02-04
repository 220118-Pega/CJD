/**
 * 
 */
package com.revature.Expense.dl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.Expense.models.SortbyUid;
import com.revature.Expense.models.transaction;
import com.revature.Expense.models.userInfo;
/**
 * @author 16del
 *
 */
public class userInfoDAO implements DAO<userInfo, Integer> {
	private final Logger logger = LogManager.getLogger(this.getClass());
	//does not apply to user info here because of implementation
	@Override
	public userInfo findByTId(Integer id) {
		// TODO Auto-generated method stub
		// this is just for transactionDAO doesnt apply to user
		return null;
	}
	//returns a user based on employee id
	@Override
	public userInfo findByUid(Integer employeeid) {
		try (Connection conn = ConnectionFactory.getInstance().getConnection()){
			String query = "select * from userInfo where employid = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, employeeid);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				return new userInfo(rs.getInt("employid"),rs.getString("Name"),rs.getBoolean("isManager"),rs.getInt("Managerid"));
			}
			}catch (SQLException e) {
				e.printStackTrace();
				logger.error("can't connect to server");
			}
			return null;
	}
	//returns all users in database
	@Override
	public List<userInfo> findAll() {
		List<userInfo> users = new ArrayList<userInfo>();
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String query = "select * from userInfo";
			PreparedStatement pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				users.add(new userInfo(rs.getInt("employid"),
						rs.getString("Name"),
						rs.getBoolean("isManager"),
						rs.getInt("Managerid")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("error connecting to the database",e);
		}
		//sorts based on employee id because i like it that way
		Collections.sort(users, new SortbyUid());
		return users;
	}
	//add a new user to the database
	@Override
	public void add(userInfo newObject) {
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String query = "insert into userInfo (Name, isManager, Managerid) values (?,?,?);";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, newObject.getName());
			pstmt.setBoolean(2, newObject.isManager());
			pstmt.setInt(3, newObject.getManagerid());
			pstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("error connecting to the database",e);
		}
		
	}
	//currently not implented probably use to change users
	public void update(userInfo newOject) {
		
	}
	//does not apply to users added because of implementation
	@Override//applies to transaction not to employies
	public void updateState(userInfo newOject) {
		// TODO Auto-generated method stub
		
	}

	//since findall sorts all users by employee id can just grab last
	//employee in list they are the latest employee to be added
	@Override
	public userInfo getLatest() {
		List<userInfo> AllUsers = findAll();
		userInfo LatestUser = new userInfo(0,"zero",false,0);
		if(AllUsers.size() > 0) {
			LatestUser = AllUsers.get(AllUsers.size() - 1);
		}
		return LatestUser;
	}
	

}
