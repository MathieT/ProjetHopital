package dao;

import model.Compte;

public interface DaoCompte extends DaoGeneric<Compte, Long>{
	public Compte findByIdentifiant(String login, String password);
}
