package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Medecin;
import model.Patient;
import model.Salle;
import model.Visite;

public class DaoVisiteJdbc implements DaoVisite {

	@Override
	public void create(Visite obj) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = JdbcContext.getContext().getConnection().prepareStatement(
					"insert into visite(visite_idpatient,visite_idmedecin,visite_tarif,visite_salle,visite_date) values(?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			ps.setLong(1, obj.getPatient().getId());
			ps.setLong(2, obj.getMedecin().getId());
			ps.setInt(3, obj.getCoutVisite());
			ps.setString(4, obj.getSalle().getNomSalle());
			ps.setDate(5, Date.valueOf(obj.getDtVisite()));
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			if (rs.next()) {
				obj.setNumVisite(rs.getLong(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JdbcContext.close();

	}

	@Override
	public void update(Visite obj) {
		PreparedStatement ps = null;
		try {
			ps = JdbcContext.getContext().getConnection().prepareStatement(
					"update visite set visite_idpatient=?,visite_idmedecin=?,visite_tarif=?,visite_salle=?,visite_date=?) ",
					Statement.RETURN_GENERATED_KEYS);
			ps.setLong(1, obj.getPatient().getId());
			ps.setLong(2, obj.getMedecin().getId());
			ps.setInt(3, obj.getCoutVisite());
			ps.setString(4, obj.getSalle().getNomSalle());
			ps.setDate(5, Date.valueOf(obj.getDtVisite()));
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JdbcContext.close();
	}

	@Override
	public void delete(Visite obj) {
		deleteByKey(obj.getNumVisite());

	}

	@Override
	public void deleteByKey(Long key) {
		PreparedStatement ps = null;
		try {
			ps = JdbcContext.getContext().getConnection().prepareStatement("delete from visite where visite_numero=?");
			ps.setLong(1, key);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JdbcContext.close();

	}

	@Override
	public Visite findByKey(Long key) {
		PreparedStatement ps = null;
		Visite visite = null;
		ResultSet rs = null;
		try {
			ps = JdbcContext.getContext().getConnection().prepareStatement(
					"select * from visite v join patient p on p.patient_id=v.visite_idpatient join compte c on v.visite_idmedecin=c.compte_id  where visite_numero=?");
			ps.setLong(1, key);
			rs = ps.executeQuery();
			if (rs.next()) {
				visite = getVisite(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JdbcContext.close();
		return visite;
	}

	static Visite getVisite(ResultSet rs) throws SQLException {
		Patient patient = null;
		Medecin medecin = null;
		Visite visite = new Visite(rs.getLong("visite_numero"), rs.getDate("visite_date").toLocalDate());
		if (rs.getLong("visite_idpatient") != 0) {
			patient = new Patient(rs.getLong("patient_id"), rs.getString("patient_nom"),
					rs.getString("patient_prenom"));

		}
		medecin = new Medecin(rs.getLong("visite_idmedecin"));
		if (rs.getString("visite_salle") != null) {
			visite.setSalle(Salle.valueOf(rs.getString("visite_salle")));
			medecin.setSalle(Salle.valueOf(rs.getString("visite_salle")));
		}
		visite.setMedecin(medecin);
		visite.setPatient(patient);
		return visite;
	}

	@Override
	public List<Visite> findAll() {
		List<Visite> list = new ArrayList<>();
		Statement st = null;
		ResultSet rs = null;
		try {
			st = JdbcContext.getContext().getConnection().createStatement();
			rs = st.executeQuery(
					"select * from visite v join patient p on p.patient_id=v.visite_idpatient join compte c on v.visite_idmedecin=c.compte_id");
			while (rs.next()) {
				list.add(getVisite(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JdbcContext.close();
		return list;
	}

	public List<Visite> findByPatient(Patient obj) {
		PreparedStatement ps = null;
		List<Visite> list = new ArrayList<>();

		ResultSet rs = null;
		try {

			ps = JdbcContext.getContext().getConnection().prepareStatement(
					"select * from visite v join patient p on p.patient_id=v.visite_idpatient join compte c on v.visite_idmedecin=c.compte_id where v.visite_idpatient=?");
			ps.setLong(1, obj.getId());
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(getVisite(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JdbcContext.close();
		return list;
	}

}
