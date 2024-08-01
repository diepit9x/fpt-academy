package fa.training.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import fa.training.model.Fresher;
import fa.training.service.RegisterService;

@Controller
public class RegisterController {
	@Autowired
	private RegisterService registerService;

	@RequestMapping(value = "register")
	public String showRegister(Model model) {
		model.addAttribute("fresher", new Fresher());
		return "register";
	}

	@RequestMapping(value = "userRegister")
	public String register(@ModelAttribute("fresher") Fresher fresher, Model model) {
		boolean status = registerService.register(fresher);
		if (!status) {
			model.addAttribute("error", "Register failure");
			return "register";
		}
		return "redirect:login";
	}
}