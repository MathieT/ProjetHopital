package formation.entities;

import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

@Entity
@Table(name = "course")
public class Cours {
	@EmbeddedId
	private CoursId id;
	@Column(name = "course_date")
	private LocalDate date;
	@JoinColumn(name = "course_trainer_id", foreignKey = @ForeignKey(name = "course_trainer_id_fk"))
	@ManyToOne
	private Formateur animateur;
	@Version
	private int version;

	public Cours() {

	}

	public Cours(CoursId id, LocalDate date, Formateur animateur) {
		super();
		this.id = id;
		this.date = date;
		this.animateur = animateur;
	}

	public CoursId getId() {
		return id;
	}

	public void setId(CoursId id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Formateur getAnimateur() {
		return animateur;
	}

	public void setAnimateur(Formateur animateur) {
		this.animateur = animateur;
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
		Cours other = (Cours) obj;
		return Objects.equals(id, other.id);
	}

}
