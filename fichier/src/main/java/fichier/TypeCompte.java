package fichier;

public enum TypeCompte {
	
	Medecin("medecin"), Secretaire("secretaire");
	
	private String typeCompte;

	private TypeCompte(String typeCompte) {
		this.typeCompte = typeCompte;
	}
	
	
}
