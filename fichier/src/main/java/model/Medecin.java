package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import dao.DaoVisite;
import dao.JdbcContext;

public class Medecin extends Compte{

	private List<Visite> visites;
	private Salle salle;

	
	
	public Medecin(String login, String password, TypeCompte typeCompte) {
		super(login, password, typeCompte);
		// TODO Auto-generated constructor stub
	}

	public Medecin(Long id, String login, String password) {
		super(id, login, password, TypeCompte.Medecin);
		visites = new ArrayList<Visite>();
		
	}
	
	public Medecin(Long id, String login, String password, Salle salle) {
		super(id, login, password, TypeCompte.Medecin);
		this.salle = salle;
		visites = new ArrayList<Visite>();
	}

	public Medecin(String login, String password) {
		super(login, password, TypeCompte.Medecin);
		visites = new ArrayList<Visite>();
		
	}

	public Medecin(Long id) {
		super(id);
		visites = new ArrayList<Visite>();
	}
	
	public Medecin(Long id, Salle salle) {
		super(id);
		this.salle = salle;
		visites = new ArrayList<Visite>();
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
	
	public void salleDisponible() {
		//sauvegarder visite
		if(fileAttente.size()>0) {
			Visite visite = new Visite (salle, LocalDate.now(), fileAttente.peek(), this);
			visites.add(visite);
			fileAttente.remove();
		}
		if(visites.size()==10) {
			this.sauvegarderVisites();
		}
	}
	
	public void enregistrerVisite() {
		JdbcContext.getDaoVisite().create(null);
	}
	
	
	public void visualiserProchainPatient() {
		if(fileAttente.size()>0) {
		System.out.println("prochain patient:" + fileAttente.peek().toString());
		}
		else {
			System.out.println("Pas de patient dans la file d'attente");
		}
	}
	
	public void sauvegarderVisites() {
		for(Visite visite: visites) {
			DaoVisite daoVisite = JdbcContext.getDaoVisite();
			daoVisite.create(visite);
		}
		visites.clear();
	}

	@Override
	public String toString() {
		return "Medecin [id=" + id + ", salle=" + salle + "]";
	}



	
}
