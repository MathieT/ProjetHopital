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

import formation.entities.Formation;
import formation.entities.Type;
import formation.services.FormateurService;
import formation.services.FormationService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/formation")
public class FormationController {
	@Autowired
	private FormationService formationSrv;
	@Autowired
	private FormateurService formateurSrv;

	@GetMapping("")
	public String list(Model model) {
		model.addAttribute("formations", formationSrv.getAll());
		return "formation/list";
	}

	@GetMapping("/delete")
	public String delete(@RequestParam Long id) {
		formationSrv.delete(id);
		return "redirect:/formation";
	}

	@GetMapping("/edit")
	public String edit(@RequestParam Long id, Model model) {
		return form(model, formationSrv.getById(id));
	}

	@GetMapping("/add")
	public String add(Model model) {
		return form(model, new Formation());
	}

	private String form(Model model, Formation formation) {
		model.addAttribute("formation", formation);
		model.addAttribute("formateurs", formateurSrv.findAll());
		model.addAttribute("types", Type.values());
		return "formation/edit";
	}

	@PostMapping("")
	public String save(Model model, @Valid @ModelAttribute Formation formation, BindingResult br) {
		if (br.hasErrors()) {
			return form(model, formation);
		}
		if (formation.getId() != null) {
			formationSrv.update(formation);
		} else {
			formationSrv.create(formation);
		}
		return "redirect:/formation";
	}
}
