package cn.ljy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import cn.ljy.util.*;
import cn.ljy.bean.Admin;


public class AdminDao  {



	public boolean add(Admin admin) {
		// TODO Auto-generated method stub
		
		String sql = "insert into admin(admin_id,admin_password,admin_status) values(?,?,?)";
		
		Connection conn=null;
		PreparedStatement stmt=null;
		ResultSet rs =null;
		
		//System.out.println(sql);
		try
		{
				
			conn = C3P0Util.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, admin.getAdmin_id());
			stmt.setString(2, admin.getAdmin_password());
			stmt.setInt(3, admin.getAdmin_status());
		//	stmt.setDate();
			
			
			int rows  = stmt.executeUpdate();

		    if(rows>0)
		    	return true;

		}
		catch(Exception e)
		{
			throw new RuntimeException(e);	
		}
		finally
		{
			C3P0Util.release(rs, stmt, conn);
		}

		return false;
	}


	
	public boolean delete(String admin_id) {
		// TODO Auto-generated method stub
		

		Connection conn=null;
		PreparedStatement stmt=null;
		ResultSet rs =null;
		
		String sql = "delete from admin where admin_id=? ";
		
		//System.out.println(sql);
		
		try
		{
			
			conn = C3P0Util.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, admin_id);
			
			int rows  = stmt.executeUpdate();
			
			if(rows>0)
			
			return true;
			else
				return false;
		
		
			
		}
		catch(Exception e)
		{
			throw new RuntimeException(e);
			
		}
		finally
		{
			C3P0Util.release(rs, stmt, conn);
			
		}
	
	}

	
	public boolean updatePassword(String admin_id,  String admin_password) {

		String sql = "update  admin set admin_password=? where admin_id=?";
		
		Connection conn=null;
		PreparedStatement stmt=null;
		ResultSet rs =null;
		
		//System.out.println(sql);
		try
		{
				
			conn = C3P0Util.getConnection();
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(2, admin_id);
			stmt.setString(1, admin_password);
			
			int rows  = stmt.executeUpdate();

		    if(rows>0)
		    	return true;

		}
		catch(Exception e)
		{
			throw new RuntimeException(e);	
		}
		finally
		{
			C3P0Util.release(rs, stmt, conn);
		}

		return false;
		
	}

	public boolean updateStatus(String admin_id,  int admin_status) {

		String sql = "update  admin set admin_status=? where admin_id=?";
		
		Connection conn=null;
		PreparedStatement stmt=null;
		ResultSet rs =null;
		
		//System.out.println(sql);
		try
		{
				
			conn = C3P0Util.getConnection();
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(2, admin_id);
			stmt.setInt(1, admin_status);
			
			int rows  = stmt.executeUpdate();

		    if(rows>0)
		    	return true;

		}
		catch(Exception e)
		{
			throw new RuntimeException(e);	
		}
		finally
		{
			C3P0Util.release(rs, stmt, conn);
		}

		return false;
		
	}
	
	public Admin getOne(String admin_id) {


		Connection conn=null;
		PreparedStatement stmt=null;
		ResultSet rs =null;
		

		String sql = "select admin_password,admin_status from admin where admin_id=? ";
		
		//System.out.println(sql);
		
		try
		{
				
			conn = C3P0Util.getConnection();
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, admin_id);
			//stmt.setString(2, password);
			
			rs  = stmt.executeQuery();

			//5���������
			if(rs.next()){
				Admin admin = new Admin();
			
				admin.setAdmin_id(admin_id);
				admin.setAdmin_password(rs.getString("admin_password"));
				admin.setAdmin_status(rs.getInt("admin_status"));
				return admin;
			}
			return null;
//			6���ͷ�ռ�õ���Դ
		
			
		}
		catch(Exception e)
		{
			throw new RuntimeException(e);
			
		}
		finally
		{
			C3P0Util.release(rs, stmt, conn);
			
		}
		
	}
	
	public List<Admin> getAll() {
		Connection conn=null;
		PreparedStatement stmt=null;
		ResultSet rs =null;
		

		String sql = "select admin_id,admin_password,admin_status from admin where admin_id != 'admin'";
		
		//System.out.println(sql);
		
		try
		{
			List<Admin> list= new ArrayList<Admin>();
			conn = C3P0Util.getConnection();
			stmt = conn.prepareStatement(sql);
			
			rs  = stmt.executeQuery();

			//5���������
			while(rs.next()){
				Admin admin = new Admin();
				admin.setAdmin_id(rs.getString("admin_id"));
				admin.setAdmin_password(rs.getString("admin_password"));
				admin.setAdmin_status(rs.getInt("admin_status"));
				list.add(admin);

			}
			return list;
		
		
			
		}
		catch(Exception e)
		{
			throw new RuntimeException(e);
			
		}
		finally
		{
			C3P0Util.release(rs, stmt, conn);
			
		}
		// TODO Auto-generated method stub
		//return null;
	}
}
