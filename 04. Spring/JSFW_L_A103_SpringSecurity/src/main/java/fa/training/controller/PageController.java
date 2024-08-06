package fa.training.controller;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import fa.training.util.SessionUtil;

@Controller
public class PageController {
	@GetMapping("")
	public String index(HttpSession httpSession) {
//	if (SessionUtil.getLoggedInUser(httpSession) == null) {
//	    return "login";
//	}
		return "index";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/register")
	public String register() {
		return "register";
	}

	@GetMapping("/member/logout")
	public String logout(HttpSession httpSession) {
		SessionUtil.removeLoggedInUser(httpSession);
		SecurityContextHolder.getContext().setAuthentication(null);
		return "login";
	}

	@GetMapping("/pages/{name}")
	public String loadPages(HttpSession httpSession, @PathVariable String name) {
		return "pages/" + name;
	}

	@GetMapping("/pages/form-content/{id}")
	public String loadPages(HttpSession httpSession, Model model, @PathVariable Integer id) {
		model.addAttribute("contentId", id);
		return "pages/form-content";
	}
}