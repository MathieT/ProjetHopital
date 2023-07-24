package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class JdbcContext {
	private static DaoVisite daoVisite = new DaoVisiteJdbc();
	private static DaoPatient daoPatient = new DaoPatientJdbcImpl();
	private static DaoCompte daoCompte = new DaoCompteJdbcImpl();
	
	
	
	
	
	private Connection connection;
	private static JdbcContext singleton = null;
	
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	

	// Factory
	private JdbcContext() {
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tphopital", "root", "root123@");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	
	
	public static DaoVisite getDaoVisite() {
		return daoVisite;
	}




	public static DaoPatient getDaoPatient() {
		return daoPatient;
	}




	public static DaoCompte getDaoCompte() {
		return daoCompte;
	}




	public static void setDaoVisite(DaoVisite daoVisite) {
		JdbcContext.daoVisite = daoVisite;
	}




	public static void setDaoPatient(DaoPatient daoPatient) {
		JdbcContext.daoPatient = daoPatient;
	}




	public static void setDaoCompte(DaoCompte daoCompte) {
		JdbcContext.daoCompte = daoCompte;
	}




	public static JdbcContext getContext() {
		if (singleton == null) {
			singleton = new JdbcContext();
		}
		return singleton;
	}

	public Connection getConnection() {
		return connection;
	}

	public static void close() {
		if (singleton != null) {
			try {
				singleton.connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				singleton = null;
			}
		}
	}


}
