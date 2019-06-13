package cn.ljy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import cn.ljy.util.*;

import cn.ljy.bean.RegisterList;

public class RegisterListDao {

	public boolean add(RegisterList registerlist) {
		// TODO Auto-generated method stub

		String sql = "insert into registerlist(user_number, order_id) values(?,?)";

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = C3P0Util.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, registerlist.getUser_number());
			stmt.setString(2, registerlist.getOrder_id());
			// stmt.setDate();
			int rows = stmt.executeUpdate();
			if (rows > 0)
				return true;

		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			C3P0Util.release(rs, stmt, conn);
		}
		return false;
	}

	public boolean deleteUser(String user_number) {
		// TODO Auto-generated method stub

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		String sql = "delete from registerlist where user_number=? ";

		// System.out.println(sql);

		try {

			conn = C3P0Util.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, user_number);

			int rows = stmt.executeUpdate();

			if (rows > 0)
				return true;
			else
				return false;

		} catch (Exception e) {
			throw new RuntimeException(e);

		} finally {
			C3P0Util.release(rs, stmt, conn);

		}

	}
	
	public boolean deleteOrder(String order_id) {
		// TODO Auto-generated method stub

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		String sql = "delete from registerlist where order_id=? ";

		// System.out.println(sql);

		try {

			conn = C3P0Util.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, order_id);

			int rows = stmt.executeUpdate();

			if (rows > 0)

				return true;
			else
				return false;

		} catch (Exception e) {
			throw new RuntimeException(e);

		} finally {
			C3P0Util.release(rs, stmt, conn);

		}

	}
	

	public List<RegisterList> getUser(String user_number) {

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		String sql = "select order_id from registerlist where user_number=?";

		try {
			List<RegisterList> list = new ArrayList<RegisterList>();
			conn = C3P0Util.getConnection();
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, user_number);
			
			rs = stmt.executeQuery();

			while (rs.next()) {

				RegisterList registerlist = new RegisterList();
				registerlist.setUser_number(user_number);
				registerlist.setOrder_id(rs.getString("order_id"));
				
				list.add(registerlist);
			}
			return list;

		} catch (Exception e) {
			throw new RuntimeException(e);

		} finally {
			C3P0Util.release(rs, stmt, conn);

		}
		// TODO Auto-generated method stub
		// return null;
	}
	
	public List<RegisterList> getOrder(String order_id) {

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		String sql = "select user_number from registerlist where order_id=?";

		try {
			List<RegisterList> list = new ArrayList<RegisterList>();
			conn = C3P0Util.getConnection();
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, order_id);
			
			rs = stmt.executeQuery();

			while (rs.next()) {

				RegisterList registerlist = new RegisterList();
				registerlist.setUser_number(rs.getString("user_number"));
				registerlist.setOrder_id(order_id);
				
				list.add(registerlist);
			}
			return list;

		} catch (Exception e) {
			throw new RuntimeException(e);

		} finally {
			C3P0Util.release(rs, stmt, conn);

		}
		// TODO Auto-generated method stub
		// return null;
	}
	
	public List<RegisterList> getAll() {

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		String sql = "select user_number, order_id from registerlist";

		try {
			List<RegisterList> list = new ArrayList<RegisterList>();
			conn = C3P0Util.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

			while (rs.next()) {

				RegisterList registerlist = new RegisterList();
				registerlist.setUser_number(rs.getString("user_number"));
				registerlist.setOrder_id(rs.getString("order_id"));

				list.add(registerlist);
			}
			return list;

		} catch (Exception e) {
			throw new RuntimeException(e);

		} finally {
			C3P0Util.release(rs, stmt, conn);

		}
		// TODO Auto-generated method stub
		// return null;
	}
}
