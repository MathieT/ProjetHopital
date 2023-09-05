package formation.services;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import formation.entities.Cours;
import formation.entities.CoursId;
import formation.exceptions.CoursException;
import formation.repositories.CoursRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;

@Service
public class CoursService {

	@Autowired
	private CoursRepository coursRepo;
	@Autowired
	private Validator validator;

	public Cours findById(CoursId id) {
		if (id == null) {
			throw new CoursException("id null");
		}
		return coursRepo.findById(id).orElseThrow(() -> {
			throw new CoursException("id inconnu");
		});
	}

	public void delete(Cours cours) {
		if (cours == null) {
			throw new CoursException("cours null");
		}
		cours = this.findById(cours.getId());
		coursRepo.delete(cours);
	}

	public List<Cours> findAll() {
		return coursRepo.findAll();
	}

	public Cours createOrUpdate(Cours cours) {
		if (cours == null) {
			throw new CoursException("cours null");
		}

		Set<ConstraintViolation<Cours>> violations = validator.validate(cours);
		if (!violations.isEmpty()) {
			throw new CoursException("données invalides");
		}
		return coursRepo.save(cours);
	}

}
