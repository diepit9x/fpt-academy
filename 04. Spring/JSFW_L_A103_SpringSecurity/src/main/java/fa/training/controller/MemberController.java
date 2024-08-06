package fa.training.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fa.training.dto.LoginDTO;
import fa.training.dto.RegisterDTO;
import fa.training.dto.UpdateProFileDTO;
import fa.training.entity.Member;
import fa.training.response.ResponseObject;
import fa.training.service.MemberService;
import fa.training.util.SessionUtil;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/member")
@RequiredArgsConstructor
public class MemberController {
	private final MemberService memberService;

	@PostMapping("/register")
	public ResponseEntity<ResponseObject> register(
			@Valid @ModelAttribute RegisterDTO registerDTO,
			BindingResult bindingResult ) {
		Map<String, String> errors = new HashMap<>();
		if (bindingResult.hasErrors()) {
			bindingResult.getFieldErrors().forEach(
					error -> errors.put(error.getField(), error.getDefaultMessage())
			);
			return ResponseEntity.badRequest().body(
					ResponseObject.builder()
					.status(400)
					.object(errors)
					.build()
			);
		}
		try {
			memberService.register(registerDTO);
		} catch (Exception e) {
			errors.put(e.getMessage().split(":")[0], e.getMessage().split(":")[1]);
			return ResponseEntity.badRequest().body(
					ResponseObject.builder()
					.status(400)
					.object(errors)
					.build()
				);
		}
		return ResponseEntity.ok(ResponseObject.builder().status(200).object("Register successfully").build());
	}

	@PostMapping("/login")
	public ResponseEntity<ResponseObject> login(
			HttpSession session, 
			@Valid @ModelAttribute LoginDTO loginDTO,
			BindingResult bindingResult) {
		Member memberSession = SessionUtil.getLoggedInUser(session);
		if (memberSession != null) {
			return ResponseEntity.ok(
					ResponseObject.builder()
					.status(400)
					.object("You are logged In")
					.build()
			);
		}
		Map<String, String> errors = new HashMap<>();
		if (bindingResult.hasErrors()) {
			bindingResult.getFieldErrors().forEach(
					error -> errors.put(error.getField(), error.getDefaultMessage())
			);
			return ResponseEntity.badRequest().body(
					ResponseObject.builder()
					.status(400)
					.object(errors)
					.build()
				);
		}
		try {
			Member member = memberService.login(loginDTO);
			SessionUtil.setLoggedInUser(session, member);
		} catch (Exception e) {
			errors.put(e.getMessage().split(":")[0], e.getMessage().split(":")[1]);
			return ResponseEntity.badRequest().body(
					ResponseObject.builder()
					.status(400)
					.object(errors)
					.build()
			);
		}
		return ResponseEntity.ok(
				ResponseObject.builder()
				.status(200)
				.object("Login successfully")
				.build()
			);
	}

	@GetMapping("/profile")
	public ResponseEntity<ResponseObject> profile(HttpSession session) {
		Member member = SessionUtil.getLoggedInUser(session);
		if (member != null) {
			return ResponseEntity.ok(
					ResponseObject.builder()
					.status(200)
					.object(member)
					.build()
				);
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
				.body(ResponseObject.builder().status(401).object("Please sign in").build());
	}

	@PostMapping("/edit-profile")
	public ResponseEntity<ResponseObject> updateProfile(
			HttpSession session,
			@Valid @ModelAttribute UpdateProFileDTO updateProFileDTO,
			BindingResult bindingResult) {
		Member memberSession = SessionUtil.getLoggedInUser(session);
		Map<String, String> errors = new HashMap<>();
		if (bindingResult.hasErrors()) {
			bindingResult.getFieldErrors().forEach(
					error -> errors.put(error.getField(), error.getDefaultMessage())
			);
			return ResponseEntity.badRequest().body(
					ResponseObject.builder()
					.status(400)
					.object(errors)
					.build()
				);
		}
		try {
			Member member = memberService.update(memberSession.getId(), updateProFileDTO);
			SessionUtil.setLoggedInUser(session, member);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(
					ResponseObject.builder()
					.status(400)
					.object(e.getMessage())
					.build()
				);
		}
		return ResponseEntity.ok(
				ResponseObject.builder()
				.status(200)
				.object("Update successfully")
				.build()
			);
	}

//    @GetMapping("/logout")
//    public ResponseEntity<ResponseObject> logout(HttpSession session) {
//	SessionUtil.removeLoggedInUser(session);
//	return ResponseEntity.ok(ResponseObject.builder().status(200).object("Logout successfully").build());
//    }
}