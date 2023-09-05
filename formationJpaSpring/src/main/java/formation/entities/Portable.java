package formation.entities;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("p")
public class Portable extends Ordinateur {
	@Column(name = "computer_size")
	private int ecran;

	public Portable() {

	}

	public Portable(String code, int ram, int ecran) {
		super(code, ram);
		this.ecran = ecran;
	}

	public int getEcran() {
		return ecran;
	}

	public void setEcran(int ecran) {
		this.ecran = ecran;
	}

}
