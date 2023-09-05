package formation.entities;

import java.time.LocalDate;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "student")
@AttributeOverride(name = "nom", column = @Column(name = "student_last_name", nullable = false))
@AttributeOverride(name = "adresse.numero", column = @Column(name = "student_adress_number"))
@AttributeOverride(name = "adresse.rue", column = @Column(name = "student_adress_street"))
@AttributeOverride(name = "adresse.codePostal", column = @Column(name = "student_adress_zip_code"))
@AttributeOverride(name = "adresse.ville", column = @Column(name = "student_adress_city"))
public class Stagiaire extends Personne {
	@Column(name = "student_first_name", nullable = false)
	private String prenom;
	@Column(name = "student_birthday")
	private LocalDate dtNaiss;
	@ManyToOne
	@JoinColumn(name = "student_training_id", foreignKey = @ForeignKey(name = "student_training_id_fk"))
	private Formation formation;
	@OneToOne
	@JoinColumn(name = "student_computer_code", foreignKey = @ForeignKey(name = "student_computer_code_fk"))
	private Ordinateur ordinateur;

	public Stagiaire() {

	}

	public Stagiaire(String nom, String prenom, LocalDate dtNaiss) {
		super(nom);
		this.prenom = prenom;
		this.dtNaiss = dtNaiss;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public LocalDate getDtNaiss() {
		return dtNaiss;
	}

	public void setDtNaiss(LocalDate dtNaiss) {
		this.dtNaiss = dtNaiss;
	}

	public Formation getFormation() {
		return formation;
	}

	public void setFormation(Formation formation) {
		this.formation = formation;
	}

	public Ordinateur getOrdinateur() {
		return ordinateur;
	}

	public void setOrdinateur(Ordinateur ordinateur) {
		this.ordinateur = ordinateur;
	}

}
