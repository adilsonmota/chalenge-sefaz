package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.Phone;
import util.JdbcUtil;

public class PhoneDAOImpl implements PhoneDAO {

	public void insert(Phone phone) {
		String sql = "INSERT INTO TB_PHONE (TYP, DDD, NUMBR, CONTACT_ID) VALUES (?,?,?,?)";

		Connection conn;
		try {
			conn = JdbcUtil.getConnection();

			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, phone.getTyp());
			ps.setInt(2, phone.getDdd());
			ps.setString(3, phone.getNumbr());
			ps.setLong(4, phone.getContact().getId());
			ps.execute();
			ps.close();
		
			} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void update(Phone phone) {
		String sql = "UPDATE TB_PHONE SET TYP=?, DDD=?, NUMBR=? WHERE ID=?";

		Connection conn;
		try {
			conn = JdbcUtil.getConnection();

			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, phone.getTyp());
			ps.setInt(2, phone.getDdd());
			ps.setString(3, phone.getNumbr());
			ps.setLong(4, phone.getId());
			ps.execute();
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void remove(Phone phone) {
		String sql = "DELETE FROM TB_PHONE WHERE ID=?";

		Connection conn;
		try {
			conn = JdbcUtil.getConnection();

			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setLong(1, phone.getId());
			ps.execute();
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Phone> findAll() {
		String sql = "SELECT ID, TYP, DDD, NUMBR FROM TB_PHONE";

		List<Phone> listPhones = new ArrayList<Phone>();

		Connection conn;
		try {
			conn = JdbcUtil.getConnection();

			PreparedStatement ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Phone phone = new Phone();

				phone.setId(rs.getLong("ID"));
				phone.setTyp(rs.getString("TYP"));
				phone.setDdd(rs.getInt("DDD"));
				phone.setNumbr(rs.getString("NUMBR"));

				listPhones.add(phone);
			}
			ps.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listPhones;
	}

}
