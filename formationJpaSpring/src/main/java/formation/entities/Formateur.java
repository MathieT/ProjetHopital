package formation.entities;

import java.util.List;
import java.util.Set;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "teacher")
@AttributeOverride(name = "nom", column = @Column(name = "teacher_last_name", nullable = false))
@AttributeOverride(name = "adresse.numero", column = @Column(name = "teacher_adress_number"))
@AttributeOverride(name = "adresse.rue", column = @Column(name = "teacher_adress_street"))
@AttributeOverride(name = "adresse.codePostal", column = @Column(name = "teacher_adress_zip_code"))
@AttributeOverride(name = "adresse.ville", column = @Column(name = "teacher_adress_city"))
public class Formateur extends Personne {
	@Column(name = "teacher_exp")
	@NotNull
	@Min(value = 5)
	private Integer experience;
	@OneToMany(mappedBy = "referent", fetch = FetchType.LAZY) // comparait this avec l'attribut referent d'une formation
	private Set<Formation> formations;
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "teachers_skills", joinColumns = {
			@JoinColumn(name = "teachers_skills_teacher_id", foreignKey = @ForeignKey(name = "teachers_skills_teacher_id_fk")) }, inverseJoinColumns = {
					@JoinColumn(name = "teachers_skills_skill_id", foreignKey = @ForeignKey(name = "teachers_skills_skill_id_fk")) })
	private Set<Competence> competences;
	@OneToMany(mappedBy = "animateur")
	private Set<Cours> coursAnime;

	public Formateur() {

	}

	public Formateur(String nom, Integer experience) {
		super(nom);
		this.experience = experience;
	}

	public Integer getExperience() {
		return experience;
	}

	public void setExperience(Integer experience) {
		this.experience = experience;
	}

	public Set<Formation> getFormations() {
		return formations;
	}

	public void setFormations(Set<Formation> formations) {
		this.formations = formations;
	}

	public Set<Competence> getCompetences() {
		return competences;
	}

	public void setCompetences(Set<Competence> competences) {
		this.competences = competences;
	}

	public Set<Cours> getCoursAnime() {
		return coursAnime;
	}

	public void setCoursAnime(Set<Cours> coursAnime) {
		this.coursAnime = coursAnime;
	}

}
