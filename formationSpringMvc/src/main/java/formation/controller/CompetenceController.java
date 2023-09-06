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
		Formateur formateur = formateurSrv.findByIdWithCompetence(id);
		Competence competence = new Competence();
		model.addAttribute("formateur", formateur);
		System.out.println(formateur);
		return form(model, competence);
	}

	private String form(Model model, Competence competence) {
		model.addAttribute("competence", competence);
		return "competence/edit";
	}

	@PostMapping("")
	public String save(Model model, @RequestParam String formateurId, @Valid @ModelAttribute Competence competence, BindingResult br) {
		if (br.hasErrors()) {
			return form(model, competence);
		}
		System.out.println(formateurId);
		Formateur formateur = formateurSrv.findByIdWithCompetence(Long.parseLong(formateurId));
		competenceSrv.createOrUpdate(competence);
		Set<Competence> competences = formateur.getCompetences();
		competences.add(competence);
		formateur.setCompetences(competences);
		formateurSrv.createOrUpdate(formateur);
		return "redirect:/formateur/edit?id="+formateur.getId();
	}
}
