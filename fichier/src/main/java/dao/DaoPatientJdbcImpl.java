package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;


import model.Patient;

public class DaoPatientJdbcImpl implements DaoPatient {

	@Override
	public void create(Patient obj) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = JdbcContext.getContext().getConnection().prepareStatement(
					"insert into patient(patient_nom,patient_prenom) values(?,?) ",
					Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, obj.getNom());
			ps.setString(2, obj.getPrenom());
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			if (rs.next()) {
				obj.setId(rs.getLong(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JdbcContext.close();
	}


	@Override
	public void update(Patient obj) {
		PreparedStatement ps = null;
		try {
			ps = JdbcContext.getContext().getConnection().prepareStatement("update patient set patient_nom=?, patient_prenom=? where patient_id=?");
			ps.setString(1, obj.getNom());
			ps.setString(2, obj.getPrenom());
			ps.setLong(3, obj.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JdbcContext.close();
	}
		

	@Override
	public void delete(Patient obj) {
		deleteByKey(obj.getId());
		
	}

	@Override
	public void deleteByKey(Long key) {
		PreparedStatement ps = null;
		try {
			ps = JdbcContext.getContext().getConnection()
					.prepareStatement("delete from patient where patient_id=?");
			ps.setLong(1, key);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JdbcContext.close();
		
	}

	@Override
	public Patient findByKey(Long key) {
			PreparedStatement ps = null;
			Patient patient = null;
			ResultSet rs = null;
			try {
				ps = JdbcContext.getContext().getConnection().prepareStatement(
						"select * from patient where patient_id=?");
				ps.setLong(1, key);
				rs = ps.executeQuery();
				if (rs.next()) {
					patient = getPatient(rs);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			JdbcContext.close();
			return patient;
		
	}
	
	static Patient getPatient(ResultSet rs) throws SQLException {
		Patient patient = new Patient(rs.getLong("patient_id"), rs.getString("patient_nom"), rs.getString("patient_prenom"));

		return patient;
	}



	@Override
	public List<Patient> findAll() {
		List<Patient> list = new ArrayList<>();
		Statement st = null;
		ResultSet rs = null;
		try {
			st = JdbcContext.getContext().getConnection().createStatement();
			rs = st.executeQuery(
					"select * from patient");
			while (rs.next()) {
				list.add(getPatient(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JdbcContext.close();
		return list;
	}
	

}
