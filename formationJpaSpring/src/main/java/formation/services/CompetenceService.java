package formation.services;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import formation.entities.Competence;
import formation.exceptions.CompetenceException;
import formation.repositories.CompetenceRepository;
import formation.repositories.FormateurRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;

@Service
public class CompetenceService {
	@Autowired
	private FormateurRepository formateurRepo;
	@Autowired
	private CompetenceRepository competenceRepo;
	@Autowired
	private Validator validator;

	public Competence findById(Long id) {
		if (id == null) {
			throw new CompetenceException("id null");
		}
		return competenceRepo.findById(id).orElseThrow(() -> {
			throw new CompetenceException("id inconnu");
		});
	}

	public void deleteById(Long id) {
		competenceRepo.delete(competenceRepo.findById(id).orElseThrow());
	}

	public List<Competence> findAll() {
		return competenceRepo.findAll();
	}

	public Competence createOrUpdate(Competence competence) {
		if (competence == null) {
			throw new CompetenceException("competence null");
		}

		Set<ConstraintViolation<Competence>> violations = validator.validate(competence);
		if (!violations.isEmpty()) {
			throw new CompetenceException("données invalides");
		}

		return competenceRepo.save(competence);
	}
}
