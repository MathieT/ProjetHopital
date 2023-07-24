package model;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import dao.DaoVisite;
import dao.JdbcContext;

public class Medecin extends Compte{

	private List<Visite> visites;
	private Salle salle;

	
	
	public Medecin(Long id, String login, String password, TypeCompte typeCompte) {
		super(id, login, password, typeCompte);
		
	}

	public Medecin(String login, String password, TypeCompte typeCompte) {
		super(login, password, typeCompte);
		
	}

	public Medecin(Long id) {
		super(id);
	}
	
	public Medecin(Long id, Salle salle) {
		super(id);
		this.salle = salle;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Salle getSalle() {
		return salle;
	}

	public void setSalle(Salle salle) {
		this.salle = salle;
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
		Medecin other = (Medecin) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Medecin [id=" + id + "]";
	}
	
	public void salleDisponible() {
		//sauvegarder visite
		Visite visite = new Visite (salle, LocalDate.now(), fileAttente.peek(), this);
		visites.add(visite);
		fileAttente.remove();
	}
	
	public void visualiserProchainPatient() {
		System.out.println("prochain patient:" + fileAttente.peek().toString());
	}
	
	public void sauvegarderVisites() {
		for(Visite visite: visites) {
			DaoVisite daoVisite = JdbcContext.getDaoVisite();
			daoVisite.create(visite);
		}
		visites.clear();
	}
	

	
}
