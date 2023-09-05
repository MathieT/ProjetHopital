package formation.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import formation.entities.Formateur;
import formation.entities.Formation;
import jakarta.transaction.Transactional;

public interface FormationRepository extends JpaRepository<Formation, Long> {
	List<Formation> findByReferent(Formateur referent);

	@Query("update Formation f set f.referent=null where f.referent=:referent")
	@Transactional
	@Modifying
	void setReferentToNull(@Param("referent") Formateur referent);

	@Transactional
	@Modifying
	void deleteByReferent(Formateur referent);

	@Query("from Formation f left join fetch f.cours left join fetch f.participants where f.id=:id")
	Optional<Formation> findByIdFetchCoursAndParticipants(@Param("id") Long id);
}
