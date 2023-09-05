package formation.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import formation.entities.Formateur;

public interface FormateurRepository extends JpaRepository<Formateur, Long>{

	List<Formateur> findByNom(String nom);
	List<Formateur> findByCompetencesLibelle(String libelle);
	List<Formateur> findByNomContaining(String nom);
	
	@Query("from Formateur f left join fetch f.competences where f.id=:id")
	Optional<Formateur> findByIdFetchCompetences(@Param("id") Long id);
}
