package model;

import java.util.List;
import java.util.Objects;

public class Medecin {

	private Long id;
	private List<Visite> visites;
	private Salle salle;

	public Medecin() {
		super();
	}

	public Medecin(Long id) {
		super();
		this.id = id;
	}
	
	public Medecin(Long id, Salle salle) {
		super();
		this.id = id;
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
		
	}
	
	public void visualiserPatients() {
		
	}
	
	public void sauvegarderVisites() {
		
	}
}
