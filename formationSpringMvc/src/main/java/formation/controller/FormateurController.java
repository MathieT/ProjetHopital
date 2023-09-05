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

import formation.entities.Formateur;
import formation.services.CompetenceService;
import formation.services.FormateurService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/formateur")
public class FormateurController {

	@Autowired
	private FormateurService formateurSrv;
	@Autowired
	private CompetenceService CompetenceSrv;


	@GetMapping("/edit")
	public String edit(Model model, @RequestParam Long id) {
		System.out.println(formateurSrv.findByIdWithCompetence(id));
		System.out.println(formateurSrv.findByIdWithCompetence(id).getId());
		model.addAttribute("formateur", formateurSrv.findByIdWithCompetence(id));
		return goForm(model);
	}

	@GetMapping("/add")
	public String add(Model model) {
		model.addAttribute("formateur", new Formateur());
		return goForm(model);
	}

	private String goForm(Model model) {
		model.addAttribute("competences", ((Formateur)model.getAttribute("formateur")).getCompetences());
		return "formateur/edit";
	}

	@GetMapping("")
	public String list(Model model) {
		model.addAttribute("formateurs", formateurSrv.findAll());
		return "formateur/list";
	}

	@GetMapping("/delete")
	public String delete(@RequestParam Long id, Model model) {
		formateurSrv.deleteById(id);
		return "redirect:/formateur";
	}

//	public String recap(Model model,
//			@RequestParam(name = "nom", required = false) String nom,
//			@RequestParam(name = "experience", required = false, defaultValue = "0") int experience,
//			@RequestParam(name = "adresse.numero", required = false) String numero,
//			@RequestParam(name = "adresse.rue", required = false) String rue,
//			@RequestParam(name = "adresse.codePostal", required = false) String codePostal,
//			@RequestParam(name = "adresse.ville", required = false) String ville) {
//
//		Formateur formateur = new Formateur(nom, experience);
//		formateur.setAdresse(new Adresse(numero, rue, codePostal, ville));
//		model.addAttribute("formateur", formateur);
//		if (nom == null || nom.isBlank()) {
//			model.addAttribute("erreurNom", true);
//			return "formateur/edit";
//		}
//		return "formateur/list";
//	}
	@PostMapping("")
	public String save(Model model, @Valid @ModelAttribute("formateur") Formateur formateur, BindingResult br) {
		model.addAttribute("formateur", formateur);
		System.out.println(br);
		if (br.hasErrors()) {
			return goForm(model);
		}
		formateurSrv.createOrUpdate(formateur);
		return "redirect:/formateur";
	}
}
