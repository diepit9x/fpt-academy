package fa.training.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import fa.training.model.Fresher;
import fa.training.service.LoginService;

import org.springframework.web.bind.annotation.ModelAttribute;


@Controller
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	@RequestMapping(value = "/login")
	public String showLogin(Model model) {
		model.addAttribute("fresher", new Fresher("admin","123"));
		return "login";
	}
	
	@RequestMapping(value = "/dashboard")
	public String postMethodName(Model model, @ModelAttribute Fresher fresher) {
		boolean status = loginService.login(fresher);
		if (!status) {
			model.addAttribute("error", "Login error");
			return "redirect:login";
		}
		return "dashboard";
	}

}
