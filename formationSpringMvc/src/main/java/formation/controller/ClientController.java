package formation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/client")
public class ClientController {

	@RequestMapping("")
	public String homeClient() {
		return "client/home";
	}

	//@RequestMapping(path = "/info", method = RequestMethod.GET)
	@GetMapping("/info")
	public String info() {
		return "client/info";
	}
}
