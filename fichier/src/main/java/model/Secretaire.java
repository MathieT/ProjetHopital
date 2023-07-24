package model;

public class Secretaire extends Compte {

	public Secretaire(Long id) {
		super(id);
		
	}

	public Secretaire(Long id, String login, String password, TypeCompte typeCompte) {
		super(id, login, password, typeCompte);
		
	}

	public Secretaire(String login, String password, TypeCompte typeCompte) {
		super(login, password, typeCompte);
		
	}

	public void ajoutFileAttente(Patient patient) {
	}
	
	public void afficherFileAttente() {
	}
	
	public void creationPatient() {
	}
	
	
}
