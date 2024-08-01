package fa.training.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import fa.training.daos.MayDAO;
import fa.training.entities.May;

@Controller
@RequestMapping(value = "/may")
public class MayController {

	@Autowired
	private MayDAO mayDAOImpl;

	@GetMapping("")
	public String getAll() {
		List<May> mays = mayDAOImpl.findAllMay(null);
		System.out.println(mays);
		return "index";
	}
}
