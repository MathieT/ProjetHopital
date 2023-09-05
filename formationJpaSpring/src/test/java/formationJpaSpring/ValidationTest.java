package formationJpaSpring;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import formation.config.JpaConfig;
import formation.entities.Formateur;
import formation.exceptions.FormateurException;
import formation.repositories.FormateurRepository;
import formation.services.FormateurService;

@SpringJUnitConfig(JpaConfig.class)
class ValidationTest {

	@Autowired
	private FormateurService formateurSrv;

	@Test
	void test() {
		assertThrows(FormateurException.class, ()->{
			formateurSrv.createOrUpdate(new Formateur());
		});
	}

}
