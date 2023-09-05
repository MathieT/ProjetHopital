package formation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import formation.entities.Competence;
import formation.entities.Formateur;
import formation.services.CompetenceService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/competence")
public class CompetenceController {

	@Autowired
	private CompetenceService competenceSrv;

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
	public String add(Model model) {
		return form(model, new Competence());
	}

	private String form(Model model, Competence competence) {
		model.addAttribute("competence", competence);
		return "competence/edit";
	}

	@PostMapping("")
	public String save(Model model, @Valid @ModelAttribute Competence competence, BindingResult br, @ModelAttribute Formateur formateur) {
		if (br.hasErrors()) {
			return form(model, competence);
		}
		model.addAttribute("formateur",formateur);
		competenceSrv.createOrUpdate(competence);
		return "redirect:/formateur/edit";
	}
}
