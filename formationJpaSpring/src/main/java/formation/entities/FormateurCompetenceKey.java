package formation.entities;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class FormateurCompetenceKey implements Serializable{
	@ManyToOne
	@JoinColumn(name="formateur_competence_formateur_id")
	private Formateur formateur;
	@ManyToOne
	@JoinColumn(name="formateur_competence_compentence_id")
	private Competence competence;

	public FormateurCompetenceKey() {

	}

	public FormateurCompetenceKey(Formateur formateur, Competence competence) {
		super();
		this.formateur = formateur;
		this.competence = competence;
	}

	public Formateur getFormateur() {
		return formateur;
	}

	public void setFormateur(Formateur formateur) {
		this.formateur = formateur;
	}

	public Competence getCompetence() {
		return competence;
	}

	public void setCompetence(Competence competence) {
		this.competence = competence;
	}

	@Override
	public int hashCode() {
		return Objects.hash(competence, formateur);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FormateurCompetenceKey other = (FormateurCompetenceKey) obj;
		return Objects.equals(competence, other.competence) && Objects.equals(formateur, other.formateur);
	}

}
