package formation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

	@RequestMapping("/home")
	public String home(Model model, HttpSession session,
			@RequestParam(name = "user", required = false, defaultValue = "world") String user,
			@RequestParam(name = "age", required = false, defaultValue = "0") int age) {
		model.addAttribute("user", user);
		System.out.println(age);
		session.setAttribute("userSession", user);
		return "home";
	}

	@RequestMapping("/page1")
	public String autrePage() {
		return "page1";
	}
}
