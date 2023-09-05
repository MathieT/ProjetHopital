package formation.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import formation.entities.Competence;
import formation.entities.Cours;
import formation.entities.CoursId;
import formation.entities.Formateur;
import formation.entities.Formation;
import jakarta.transaction.Transactional;

public interface CoursRepository extends JpaRepository<Cours, CoursId> {

	@Query("update Cours c set c.animateur=null where c.animateur=:animateur")
	@Transactional
	@Modifying
	void setAnimateurToNull(@Param("animateur") Formateur animateur);

	List<Cours> findByAnimateur(Formateur animateur);

	@Transactional
	@Modifying
	void deleteByIdMatiere(Competence matiere);

	@Transactional
	@Modifying
	void deleteByIdFormation(Formation formation);
}
