package formation.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("r")
public class Raspberry extends Ordinateur {
	public Raspberry() {

	}
}
