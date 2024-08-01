package fpt.fa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import fpt.fa.repository.StudentRepository;

@Controller
@RequestMapping("/student")
public class StudentController {
	@Autowired
	private StudentRepository studentRepository;
	
	@GetMapping("/list")
	public String list(Model model) {
		model.addAttribute("students", studentRepository.findAll());
		return "/student/list";
	}
}