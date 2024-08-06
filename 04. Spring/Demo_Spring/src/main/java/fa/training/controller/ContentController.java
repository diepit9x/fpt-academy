package fa.training.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fa.training.entity.Content;
import fa.training.entity.ContentDTO;
import fa.training.repository.ContentRepository;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/content")
@RequiredArgsConstructor
public class ContentController {
	private final ContentRepository contentRepository;
	
	@PostMapping("/create")
	public ResponseEntity<?> create(@Valid @RequestBody ContentDTO contentDTO, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return ResponseEntity.badRequest().body(bindingResult.toString());
		}
		try {
			Content content = new Content();
			content.setDate(contentDTO.getDate(), "dd-MM-yyyy");
			contentRepository.save(content);
			return ResponseEntity.ok().body("success");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}
