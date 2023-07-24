package model;

import java.time.LocalDate;
import java.util.Objects;

public class Visite {

	private Long numVisite;
	private final int coutVisite=20;
	private Salle salle;
	private LocalDate dtVisite;
	private Patient patient;
	private Compte compte;
	private Medecin medecin;
	
	
	
	public Visite(Long numVisite, Salle salle, LocalDate dtVisite) {
		super();
		this.numVisite = numVisite;
		this.salle = salle;
		this.dtVisite = dtVisite;
	}


	public Visite(Long numVisite, Salle salle, LocalDate dtVisite, Patient patient, Compte compte, Medecin medecin) {
		super();
		this.numVisite = numVisite;
		this.salle = salle;
		this.dtVisite = dtVisite;
		this.patient = patient;
		this.compte = compte;
		this.medecin = medecin;
	}
	
	
	public Visite(Long numVisite, Salle salle, LocalDate dtVisite, Compte compte) {
		super();
		this.numVisite = numVisite;
		this.salle = salle;
		this.dtVisite = dtVisite;
		this.compte = compte;
	}


	public Visite(Long numVisite, LocalDate dtVisite) {
		super();
		this.numVisite = numVisite;
		this.dtVisite = dtVisite;
	}


	public Long getNumVisite() {
		return numVisite;
	}
	public void setNumVisite(Long numVisite) {
		this.numVisite = numVisite;
	}
	public Salle getSalle() {
		return salle;
	}
	public void setSalle(Salle salle) {
		this.salle = salle;
	}
	public LocalDate getDtVisite() {
		return dtVisite;
	}
	public void setDtVisite(LocalDate dtVisite) {
		this.dtVisite = dtVisite;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public Compte getCompte() {
		return compte;
	}
	public void setCompte(Compte compte) {
		this.compte = compte;
	}
	public Medecin getMedecin() {
		return medecin;
	}
	public void setMedecin(Medecin medecin) {
		this.medecin = medecin;
	}
	public int getCoutVisite() {
		return coutVisite;
	}
	@Override
	public int hashCode() {
		return Objects.hash(numVisite);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Visite other = (Visite) obj;
		return Objects.equals(numVisite, other.numVisite);
	}
	
	
}