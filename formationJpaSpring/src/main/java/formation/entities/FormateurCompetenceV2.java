package formation.entities;

import java.util.Objects;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Table;

//@Entity
@Table(name = "formateur_competence")
public class FormateurCompetenceV2 {
	@EmbeddedId
	private FormateurCompetenceKey id;

	public FormateurCompetenceV2() {

	}

	public FormateurCompetenceV2(FormateurCompetenceKey id) {
		super();
		this.id = id;
	}

	public FormateurCompetenceKey getId() {
		return id;
	}

	public void setId(FormateurCompetenceKey id) {
		this.id = id;
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
		FormateurCompetenceV2 other = (FormateurCompetenceV2) obj;
		return Objects.equals(id, other.id);
	}

}
