package formation.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import formation.entities.Competence;
import formation.entities.Formateur;
import formation.entities.Formation;

public interface CompetenceRepository extends JpaRepository<Competence, Long> {
	
}
