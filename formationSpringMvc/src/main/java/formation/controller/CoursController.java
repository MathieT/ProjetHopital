package formation.controller;

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

import formation.entities.Cours;
import formation.entities.CoursId;
import formation.entities.Formation;
import formation.services.CompetenceService;
import formation.services.CoursService;
import formation.services.FormateurService;
import formation.services.FormationService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/cours")
public class CoursController {

		@Autowired
		private CoursService coursSrv;
		@Autowired
		private FormationService formationSrv;
		@Autowired
		private CompetenceService competenceSrv;
		@Autowired
		private FormateurService formateurSrv;

		@GetMapping("")
		public String list(Model model) {
			model.addAttribute("cours", coursSrv.findAll());
			return "cours/list";
		}

		@GetMapping("/delete")
		public String delete(@RequestParam CoursId id) {
			coursSrv.deleteById(id);
			return "redirect:/cours";
		}

		@GetMapping("/edit")
		public String edit(@RequestParam Long id, Model model) {
			return form(model, formationSrv.findByIdWithCoursAndParticipants(id));
		}

		@GetMapping("/add")
		public String add(Model model, @RequestParam Long id) {
			return form(model, formationSrv.findByIdWithCoursAndParticipants(id));
		}

		private String form(Model model, Formation formation) {
			Cours cours = new Cours();
			CoursId coursId = new CoursId();
			coursId.setFormation(formation);
			cours.setId(coursId);
			model.addAttribute("cours", cours);
			model.addAttribute("competences", competenceSrv.findAll());
			model.addAttribute("formateurs", formateurSrv.findAll());
			model.addAttribute("formation", formation);
			return "cours/edit";
		}

		@PostMapping("")
		public String save(Model model, @Valid @ModelAttribute Cours cours, BindingResult br) {
			if (br.hasErrors()) {
				return form(model, cours.getId().getFormation());
			}
			
			Formation formation = formationSrv.findByIdWithCoursAndParticipants(cours.getId().getFormation().getId());
			coursSrv.createOrUpdate(cours);
			Set<Cours> courses = formation.getCours();
			courses.add(cours);
			formation.setCours(courses);
			formationSrv.update(formation);
			
			return "redirect:/formation/edit?id="+formation.getId();
		}
}
