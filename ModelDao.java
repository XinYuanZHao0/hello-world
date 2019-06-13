package cn.ljy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import cn.ljy.util.*;

import cn.ljy.bean.Model;

public class ModelDao {

	public boolean add(Model model) {
		// TODO Auto-generated method stub

		String sql = "insert into model(model_name, model_count, model_description, model_fee) values(?,?,?,?)";

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = C3P0Util.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, model.getModel_name());
			stmt.setInt(2, model.getModel_count());
			stmt.setString(3, model.getModel_description());
			stmt.setInt(4, model.getModel_fee());
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

	public boolean delete(int model_id) {
		// TODO Auto-generated method stub

		Connection conn = null;
		Connection conn2 = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		String sql = "delete from model where model_id=? ";

		// System.out.println(sql);

		try {

			conn = C3P0Util.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, model_id);

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

	public boolean update(Model model) {

		String sql = "update  model set model_name=?,model_count=?,model_description=?,model_fee=? where model_id=?";

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		// System.out.println(sql);
		try {

			conn = C3P0Util.getConnection();
			stmt = conn.prepareStatement(sql);

			stmt.setInt(5, model.getModel_id());
			stmt.setString(1, model.getModel_name());
			stmt.setInt(2, model.getModel_count());
			stmt.setString(3, model.getModel_description());
			stmt.setInt(4, model.getModel_fee());

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

	public Model getOne(int model_id) {

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		String sql = "select model_name, model_count, model_description, model_fee from model where model_id=? ";

		// System.out.println(sql);

		try {

			conn = C3P0Util.getConnection();
			stmt = conn.prepareStatement(sql);

			stmt.setInt(1, model_id);
			// stmt.setString(2, password);

			rs = stmt.executeQuery();

			if (rs.next()) {
				Model model = new Model();
				model.setModel_id(model_id);
				model.setModel_name(rs.getString("model_name"));
				model.setModel_count(rs.getInt("model_count"));
				model.setModel_description(rs.getString("model_description"));
				model.setModel_fee(rs.getInt("model_fee"));

				return model;
			}
			return null;

		} catch (Exception e) {
			throw new RuntimeException(e);

		} finally {
			C3P0Util.release(rs, stmt, conn);

		}

	}

	public List<Model> getAll() {

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		String sql = "select model_id, model_name, model_count, model_description, model_fee from model";

		// System.out.println(sql);

		try {
			List<Model> list = new ArrayList<Model>();
			conn = C3P0Util.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

			while (rs.next()) {

				Model model = new Model();
				model.setModel_id(rs.getInt("model_id"));
				model.setModel_name(rs.getString("model_name"));
				model.setModel_count(rs.getInt("model_count"));
				model.setModel_description(rs.getString("model_description"));
				model.setModel_fee(rs.getInt("model_fee"));

				list.add(model);
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
