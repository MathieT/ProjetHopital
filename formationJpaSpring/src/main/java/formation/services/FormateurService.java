package formation.services;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import formation.entities.Competence;
import formation.entities.Formateur;
import formation.exceptions.FormateurException;
import formation.repositories.CoursRepository;
import formation.repositories.FormateurRepository;
import formation.repositories.FormationRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;

@Service
public class FormateurService {
	@Autowired
	private FormateurRepository formateurRepo;
	@Autowired
	private FormationRepository formationRepo;
	@Autowired
	private CoursRepository coursRepo;
	@Autowired
	private Validator validator;

	public Formateur findById(Long id) {
		if (id == null) {
			throw new FormateurException("id null");
		}
		return formateurRepo.findById(id).orElseThrow(() -> {
			throw new FormateurException("id inconnu");
		});
	}

	public Formateur findByIdWithCompetence(Long id) {
		if (id == null) {
			throw new FormateurException("id null");
		}
		return formateurRepo.findByIdFetchCompetences(id).orElseThrow(() -> {
			throw new FormateurException("id inconnu");
		});
	}

	public List<Formateur> findAll() {
		return formateurRepo.findAll();
	}

	public Page<Formateur> findAll(Pageable pageable) {
		return formateurRepo.findAll(pageable);
	}

	public Formateur createOrUpdate(Formateur formateur) {
		if (formateur == null) {
			throw new FormateurException("formateur null");
		}

		Set<ConstraintViolation<Formateur>> violations = validator.validate(formateur);
		if (!violations.isEmpty()) {
			throw new FormateurException("données invalides");
		}
		if(formateur.getId()!=null) {
			Formateur formateur2 = findByIdWithCompetence(formateur.getId());
			Set<Competence> competences = formateur.getCompetences();
			if(competences==null) {
				if(formateur2.getCompetences()!=null) {
					formateur.setCompetences(formateur2.getCompetences());
				}	
			}else {
				if(formateur2.getCompetences()!=null) {
					competences.addAll(formateur2.getCompetences());
				}
			}
		}
		return formateurRepo.save(formateur);
	}

	public void delete(Formateur formateur) {
		if (formateur == null) {
			throw new FormateurException("formateur null");
		}
		formateur = this.findById(formateur.getId());
		formationRepo.setReferentToNull(formateur);
		coursRepo.setAnimateurToNull(formateur);
		formateurRepo.delete(formateur);
	}

	public void deleteById(Long id) {
		delete(this.findById(id));
	}
}
