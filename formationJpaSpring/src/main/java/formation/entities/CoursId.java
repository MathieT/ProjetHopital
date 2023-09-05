package formation.entities;

import java.util.Objects;

import jakarta.persistence.Embeddable;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class CoursId {
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "course_training_id", foreignKey = @ForeignKey(name = "course_training_id_fk"))
	private Formation formation;
	@ManyToOne
	@JoinColumn(name = "course_skill_id", foreignKey = @ForeignKey(name = "course_skill_id_fk"))
	private Competence matiere;

	public CoursId() {

	}

	public CoursId(Formation formation, Competence matiere) {
		super();
		this.formation = formation;
		this.matiere = matiere;
	}

	public Formation getFormation() {
		return formation;
	}

	public void setFormation(Formation formation) {
		this.formation = formation;
	}

	public Competence getMatiere() {
		return matiere;
	}

	public void setMatiere(Competence matiere) {
		this.matiere = matiere;
	}

	@Override
	public int hashCode() {
		return Objects.hash(formation, matiere);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CoursId other = (CoursId) obj;
		return Objects.equals(formation, other.formation) && Objects.equals(matiere, other.matiere);
	}

}
