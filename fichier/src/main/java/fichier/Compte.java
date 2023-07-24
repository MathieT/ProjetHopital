package fichier;

import java.util.Objects;

public class Compte {
	
	private Long id;
	private String login;
	private String password;
	private TypeCompte typeCompte;
	
	public Compte(Long id, String login, String password, TypeCompte typeCompte) {
		this.id = id;
		this.login = login;
		this.password = password;
		this.typeCompte = typeCompte;
	}

	public Compte(String login, String password, TypeCompte typeCompte) {
		this.login = login;
		this.password = password;
		this.typeCompte = typeCompte;
	}

	public Long getId() {
		return id;
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

	public TypeCompte getTypeCompte() {
		return typeCompte;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setTypeCompte(TypeCompte typeCompte) {
		this.typeCompte = typeCompte;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Compte other = (Compte) obj;
		return Objects.equals(id, other.id);
	}
	
	
}
