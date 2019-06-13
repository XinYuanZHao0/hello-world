package cn.ljy.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import cn.ljy.util.*;

import cn.ljy.bean.Order;

public class OrderDao {

	public boolean add(Order order) {
		// TODO Auto-generated method stub

		String sql = "insert into orderlist(order_id, room_id, start_time, end_time, order_fee, order_deposit, order_status) values(?,?,?,?,?,?,?)";

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = C3P0Util.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, order.getOrder_id());
			stmt.setString(2, order.getRoom_id());
			stmt.setDate(3, (Date) order.getStart_time());
			stmt.setDate(4,(Date) order.getEnd_time());
			stmt.setInt(5, order.getOrder_fee());
			stmt.setInt(6, order.getOrder_deposit());
			stmt.setInt(7, order.getOrder_status());
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

	public boolean delete(String order_id) {
		// TODO Auto-generated method stub

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		String sql = "delete from orderlist where order_id=? ";

		// System.out.println(sql);

		try {

			conn = C3P0Util.getConnection();
			return false;
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

	public boolean deleteRoom(String room_id) {
		// TODO Auto-generated method stub

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		String sql = "delete from orderlist where room_id=? ";

		// System.out.println(sql);

		try {

			conn = C3P0Util.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, room_id);

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
	public boolean update(Order order) {

		String sql = "update orderlist set start_time=?, end_time=?, order_fee=?, order_deposit=? where order_id=?";

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		// System.out.println(sql);
		try {

			conn = C3P0Util.getConnection();
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(5, order.getOrder_id());
			stmt.setDate(1, (Date) order.getStart_time());
			stmt.setDate(2,(Date) order.getEnd_time());
			stmt.setInt(3, order.getOrder_fee());
			stmt.setInt(4, order.getOrder_deposit());

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
	
	public boolean updateStatus(String order_id) {

		String sql = "update orderlist set order_status=0 where order_id=?";

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		// System.out.println(sql);
		try {

			conn = C3P0Util.getConnection();
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, order_id);

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

	public Order getOne(String order_id) {

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		String sql = "select room_id, start_time, end_time, order_fee, order_deposit, order_status from orderlist where order_id=? ";
		
		try {

			conn = C3P0Util.getConnection();
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, order_id);
			
			rs = stmt.executeQuery();

			if (rs.next()) {
				Order order = new Order();
				order.setOrder_id(order_id);;
				order.setRoom_id(rs.getString("room_id"));
				order.setStart_time(rs.getDate("start_time"));
				order.setEnd_time(rs.getDate("end_time"));
				order.setOrder_fee(rs.getInt("order_fee"));
				order.setOrder_deposit(rs.getInt("order_deposit"));
				order.setOrder_status(rs.getInt("order_status"));
				return order;
			}
			return null;

		} catch (Exception e) {
			throw new RuntimeException(e);

		} finally {
			C3P0Util.release(rs, stmt, conn);

		}

	}

	public List<Order> getAlling() {

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		String sql = "select order_id,room_id,start_time,end_time,order_fee,order_deposit,order_status from orderlist where order_status = 1";

		try {
			List<Order> list = new ArrayList<Order>();
			conn = C3P0Util.getConnection();
			stmt = conn.prepareStatement(sql);
			
			rs = stmt.executeQuery();

			while (rs.next()) {

				Order order = new Order();
				order.setOrder_id(rs.getString("order_id"));;
				order.setRoom_id(rs.getString("room_id"));
				order.setStart_time(rs.getDate("start_time"));
				order.setEnd_time(rs.getDate("end_time"));
				order.setOrder_fee(rs.getInt("order_fee"));
				order.setOrder_deposit(rs.getInt("order_deposit"));
				order.setOrder_status(rs.getInt("order_status"));

				list.add(order);
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
	public List<Order> getRoom(String room_id) {

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		String sql = "select order_id,room_id,start_time,end_time,order_fee,order_deposit,order_status from orderlist where room_id = ?";

		try {
			List<Order> list = new ArrayList<Order>();
			conn = C3P0Util.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, room_id);
			rs = stmt.executeQuery();

			while (rs.next()) {

				Order order = new Order();
				order.setOrder_id(rs.getString("order_id"));;
				order.setRoom_id(rs.getString("room_id"));
				order.setStart_time(rs.getDate("start_time"));
				order.setEnd_time(rs.getDate("end_time"));
				order.setOrder_fee(rs.getInt("order_fee"));
				order.setOrder_deposit(rs.getInt("order_deposit"));
				order.setOrder_status(rs.getInt("order_status"));

				list.add(order);
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
	public List<Order> getAll() {

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		String sql = "select order_id,room_id,start_time,end_time,order_fee,order_deposit,order_status from orderlist";

		try {
			List<Order> list = new ArrayList<Order>();
			conn = C3P0Util.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

			while (rs.next()) {

				Order order = new Order();
				order.setOrder_id(rs.getString("order_id"));;
				order.setRoom_id(rs.getString("room_id"));
				order.setStart_time(rs.getDate("start_time"));
				order.setEnd_time(rs.getDate("end_time"));
				order.setOrder_fee(rs.getInt("order_fee"));
				order.setOrder_deposit(rs.getInt("order_deposit"));
				order.setOrder_status(rs.getInt("order_status"));

				list.add(order);
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
