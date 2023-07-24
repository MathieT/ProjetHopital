package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Compte;
import model.TypeCompte;

public class DaoCompteJdbcImpl implements DaoCompte{

	@Override
	public void create(Compte obj) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = JdbcContext.getContext().getConnection().prepareStatement(
					"insert into compte(compte_login,compte_password,compte_typeCompte) values(?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, obj.getLogin());
			ps.setString(2, obj.getPassword());
			ps.setString(3, obj.getTypeCompte().getTypeCompte());
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
	public void update(Compte obj) {
		PreparedStatement ps = null;
		try {
			ps = JdbcContext.getContext().getConnection().prepareStatement(
					"update compte set compte_login=?, compte_password=?,compte_typeCompte=? where compte_id=?");
			ps.setString(1, obj.getLogin());
			ps.setString(2, obj.getPassword());
			ps.setString(3, obj.getTypeCompte().getTypeCompte());
			ps.setLong(4, obj.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JdbcContext.close();
	}

	@Override
	public void delete(Compte obj) {
		deleteByKey(obj.getId());
	}

	@Override
	public void deleteByKey(Long key) {
		PreparedStatement ps = null;
		try {
			ps = JdbcContext.getContext().getConnection()
					.prepareStatement("delete from compte where compte_id=?");
			ps.setLong(1, key);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JdbcContext.close();
	}

	@Override
	public Compte findByKey(Long key) {
		PreparedStatement ps = null;
		Compte compte = null;
		ResultSet rs = null;
		try {
			ps = JdbcContext.getContext().getConnection().prepareStatement(
					"select * from compte where compte_id=?");
			ps.setLong(1, key);
			rs = ps.executeQuery();
			if (rs.next()) {
				compte = getCompte(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JdbcContext.close();
		return compte;
	}

	static Compte getCompte(ResultSet rs) throws SQLException {
		String typeCompte = rs.getString("compte_typeCompte");
		Compte compte = new Compte(rs.getLong("compte_id"), rs.getString("compte_login"),
				rs.getString("compte_password"),TypeCompte.valueOf(typeCompte));
		return compte;
	}
	@Override
	public List<Compte> findAll() {
		Statement st = null;
		List<Compte> list = new ArrayList<>();
		ResultSet rs = null;
		try {
			st = JdbcContext.getContext().getConnection().createStatement();
			rs = st.executeQuery("select * from compte");
			while (rs.next()) {
				list.add(getCompte(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JdbcContext.close();
		return list;
	}

}
