package fa.training.controller;

import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fa.training.dto.UserDTO;
import fa.training.entity.User;
import fa.training.service.UserService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class HomeController {
	private final UserService userService;
	
	@GetMapping("/home")
	public ResponseEntity<?> home() {
		return ResponseEntity.ok("home page");
	}

	@GetMapping("/auth")
	public ResponseEntity<?> auth() {
		return ResponseEntity.ok("authenticated");
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(HttpSession session, @RequestBody UserDTO userDTO) {
		try {
			Authentication authentication = userService.login(userDTO);
			
			SecurityContextHolder.getContext().setAuthentication(authentication);
			
			User user = (User) authentication.getPrincipal();
			
			return ResponseEntity.ok().body(user);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody UserDTO userDTO) {
		try {
			User user = userService.register(userDTO);
			return ResponseEntity.ok().body(user);
		} catch (Exception e) { 
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping("/logout")
	public ResponseEntity<?> logout() {
		try {
			SecurityContextHolder.getContext().setAuthentication(null);
			return ResponseEntity.ok().body("Logout successfully");
		} catch (Exception e) { 
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	
}