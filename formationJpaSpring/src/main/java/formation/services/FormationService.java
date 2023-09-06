package formation.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import formation.entities.Formateur;
import formation.entities.Formation;
import formation.exceptions.FormateurException;
import formation.exceptions.FormationException;
import formation.exceptions.IdException;
import formation.repositories.FormationRepository;

@Service
public class FormationService {

	@Autowired
	private FormationRepository formationRepo;
	@Autowired
	private FormateurService formateurService;

	public Formation create(Formation formation) {
		checkFormationIsNotNull(formation);
		checkFormationLibelleIsNotNullOrEmpty(formation);
		//checkFormationReferentIsNotNull(formation);
		formation.setReferent(formateurService.findById(formation.getReferent().getId()));
		return formationRepo.save(formation);
	}

	private void checkFormationReferentIsNotNull(Formation formation) {
		if (formation.getReferent() == null) {
			throw new FormationException();
		}
	}

	private void checkFormationLibelleIsNotNullOrEmpty(Formation formation) {
		if (formation.getNom() == null || formation.getNom().isEmpty()) {
			throw new FormationException();
		}
	}

	private void checkFormationIsNotNull(Formation formation) {
		if (formation == null) {
			throw new FormationException();
		}
	}

	public Formation getById(Long id) {
		if (id == null) {
			throw new IdException();
		}
		return formationRepo.findById(id).orElseThrow(IdException::new);
	}
	
	public Formation findByIdWithCoursAndParticipants(Long id) {
		if (id == null) {
			throw new IdException();
		}
		return formationRepo.findByIdFetchCoursAndParticipants(id).orElseThrow(() -> {
			throw new IdException();
		});
	}

	public void delete(Formation formation) {
		checkFormationIsNotNull(formation);
		deleteById(formation.getId());
	}

	public void delete(Long id) {
		deleteById(id);
	}

	private void deleteById(Long id) {
		Formation formation = getById(id);
		formationRepo.delete(formation);
	}

	public List<Formation> getAll() {
		return formationRepo.findAll();
	}

	public Page<Formation> getAll(Pageable pageable) {
		if (pageable == null) {
			throw new FormationException();
		}
		return formationRepo.findAll(pageable);
	}

	public Page<Formation> getNextPage(Page<Formation> page) {
		if (page == null) {
			throw new FormationException();
		}
		return formationRepo.findAll(page.nextOrLastPageable());
	}

	public Page<Formation> getPrevious(Page<Formation> page) {
		if (page == null) {
			throw new FormationException();
		}
		return formationRepo.findAll(page.previousOrFirstPageable());
	}

	public Formation update(Formation formation) {
		// @formatter:off
		Formation formationEnBase = getById(formation.getId());
		checkFormationLibelleIsNotNullOrEmpty(formationEnBase);
		formationEnBase.setNom(formation.getNom());
		formationEnBase.setDescription(formation.getDescription());
		formationEnBase.setDate(formation.getDate());
		formationEnBase.setType(formation.getType());
		checkFormationReferentIsNotNull(formation);
		formationEnBase.setReferent(formateurService.findById(formation.getReferent().getId()));
		return formationRepo.save(formationEnBase);
		// @formatter:on
	}
}
