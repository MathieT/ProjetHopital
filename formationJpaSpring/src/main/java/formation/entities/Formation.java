package formation.entities;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.Version;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "training")
public class Formation {
	@Id
	@Column(name = "training_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "training_name", nullable = false, length = 100)
	@NotBlank
	private String nom;
	@Column(name = "training_date")
	@Future
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate date;
	@Column(name = "training_desc", columnDefinition = "TEXT")
	// @Lob
	private String description;
	@Enumerated(EnumType.STRING)
	@Column(name = "training_type", length = 15)
	private Type type;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "training_referent_id", foreignKey = @ForeignKey(name = "training_referent_id_fk"))
	@NotNull
	private Formateur referent;
	@OneToMany(mappedBy = "formation")
	private Set<Stagiaire> participants;
	@OneToMany(mappedBy = "id.formation")
	private Set<Cours> cours;
	@Version
	private int version;

	public Formation() {

	}

	public Formation(String nom) {
		super();
		this.nom = nom;
	}

	public Formation(String nom, LocalDate date) {
		super();
		this.nom = nom;
		this.date = date;
	}

	public Formation(String nom, LocalDate date, String description) {
		super();
		this.nom = nom;
		this.date = date;
		this.description = description;
	}

	public Formation(String nom, LocalDate date, String description, Type type) {
		super();
		this.nom = nom;
		this.date = date;
		this.description = description;
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Formateur getReferent() {
		return referent;
	}

	public void setReferent(Formateur referent) {
		this.referent = referent;
	}

	public Set<Stagiaire> getParticipants() {
		return participants;
	}

	public void setParticipants(Set<Stagiaire> participants) {
		this.participants = participants;
	}

	public Set<Cours> getCours() {
		return cours;
	}

	public void setCours(Set<Cours> cours) {
		this.cours = cours;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
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
		Formation other = (Formation) obj;
		return Objects.equals(id, other.id);
	}

}
