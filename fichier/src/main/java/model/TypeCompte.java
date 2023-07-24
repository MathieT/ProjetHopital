package model;

public enum TypeCompte {
	
	Medecin("Medecin"), Secretaire("Secretaire");
	
	private String typeCompte;

	private TypeCompte(String typeCompte) {
		this.typeCompte = typeCompte;
	}

	public String getTypeCompte() {
		return typeCompte;
	}
}
