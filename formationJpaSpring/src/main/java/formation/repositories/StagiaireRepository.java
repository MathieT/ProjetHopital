package formation.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import formation.entities.Formation;
import formation.entities.Ordinateur;
import formation.entities.Stagiaire;
import jakarta.transaction.Transactional;

public interface StagiaireRepository extends JpaRepository<Stagiaire, Long> {
	List<Stagiaire> findByOrdinateurNotNull();

	@Query("update Stagiaire s set s.formation=null where s.formation=:formation")
	@Transactional
	@Modifying
	void setFormationToNull(@Param("formation") Formation formation);

	@Query("update Stagiaire s set s.ordinateur=null where s.ordinateur=:ordinateur")
	@Transactional
	@Modifying
	void setOrdinateurToNull(Ordinateur ordinateur);
}
