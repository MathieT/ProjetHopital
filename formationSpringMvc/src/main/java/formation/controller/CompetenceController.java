package formation.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import formation.entities.Competence;
import formation.entities.Formateur;
import formation.services.CompetenceService;
import formation.services.FormateurService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/competence")
@SessionAttributes("formateur")
public class CompetenceController {

	@Autowired
	private CompetenceService competenceSrv;
	@Autowired
	private FormateurService formateurSrv;

	@GetMapping("")
	public String list(Model model) {
		model.addAttribute("competences", competenceSrv.findAll());
		return "competence/list";
	}

	@GetMapping("/delete")
	public String delete(@RequestParam Long id) {
		competenceSrv.deleteById(id);
		return "redirect:/competence";
	}

	@GetMapping("/edit")
	public String edit(@RequestParam Long id, Model model) {
		return form(model, competenceSrv.findById(id));
	}

	@GetMapping("/add")
	public String add(Model model, @RequestParam Long id) {
		model.addAttribute("formateur", formateurSrv.findByIdWithCompetence(id));
		return form(model, new Competence());
	}

	private String form(Model model, Competence competence) {
		model.addAttribute("competence", competence);
		return "competence/edit";
	}

	@PostMapping("")
	public String save(Model model, @Valid @ModelAttribute Competence competence, BindingResult br) {
		if (br.hasErrors()) {
			return form(model, competence);
		}
		Set<Formateur> formateurs = new HashSet<Formateur>();
		formateurs.add((Formateur)model.getAttribute("formateur"));
		competence.setFormateurs(formateurs);
		competenceSrv.createOrUpdate(competence);
		Set<Competence> competences = ((Formateur)model.getAttribute("formateur")).getCompetences();
		competences.add(competence);
		((Formateur)model.getAttribute("formateur")).setCompetences(competences);
		formateurSrv.createOrUpdate((Formateur)model.getAttribute("formateur"));
		//((Formateur)model.getAttribute("formateur")).getCompetences().forEach(c->System.out.println(c.getLibelle()));
		return "redirect:/formateur/edit?id="+((Formateur)model.getAttribute("formateur")).getId();
	}
}
