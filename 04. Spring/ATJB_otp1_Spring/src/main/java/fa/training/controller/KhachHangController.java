package fa.training.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fa.training.daos.KhachHangDAO;
import fa.training.entities.KhachHang;
import fa.training.entities.SuDungDichVuMay;
import fa.training.models.PagedResult;
import fa.training.models.ResponseData;
import jakarta.validation.Valid;

@Controller
@RequestMapping(value = "/khach-hang")
public class KhachHangController {
    @Autowired
    private KhachHangDAO khachHangDAO;

    @GetMapping("/tao-moi")
    public String create(Model model) {
	model.addAttribute("khachHang", new KhachHang());
	return "pages/khach-hang/tao-moi";
    }

    @PostMapping("/tao-moi")
    public String create(Model model, @Valid @ModelAttribute KhachHang khachHang, BindingResult bindingResult) {
	List<String> errors = new ArrayList<>();
	boolean status = false;
	if (bindingResult.hasErrors()) {
	    errors = bindingResult.getFieldErrors().stream().map(FieldError::getDefaultMessage).toList();
	} else {
	    try {
		status = khachHangDAO.insert(khachHang);
	    } catch (Exception e) {
		errors.add(e.getMessage());
	    }
	}
	model.addAttribute("errors", errors);
	model.addAttribute("success", status);
	model.addAttribute("khachHang", status ? new KhachHang() : khachHang);
	return "pages/khach-hang/tao-moi";
    }

    @GetMapping("/danh-sach")
    public String getAll(@RequestParam(required = false, defaultValue = "1") int page,
	    @RequestParam(required = false) String keyword, Model model) {
	page = page < 1 ? 1 : page;
	int pageSize = 10;
	PagedResult<KhachHang> pagedResult = khachHangDAO.findAll(keyword, page, pageSize);
	ResponseData responseData = new ResponseData(200, pagedResult);
	model.addAttribute("responseData", responseData);
	model.addAttribute("keyword", keyword);
	return "pages/khach-hang/danh-sach";
    }

    @GetMapping("/cap-nhat")
    public String update(Model model, @RequestParam String maKhachHang) {
	KhachHang khachHang = khachHangDAO.findById(maKhachHang);
	if (khachHang == null) {
	    return "redirect:/khach-hang/danh-sach";
	}
	model.addAttribute("khachHang", khachHang);
	return "pages/khach-hang/cap-nhat";
    }

    @PostMapping("/cap-nhat")
    public String update(Model model, @Valid @ModelAttribute KhachHang khachHang, BindingResult bindingResult) {
	List<String> errors = new ArrayList<>();
	boolean status = false;
	if (bindingResult.hasErrors()) {
	    errors = bindingResult.getFieldErrors().stream().map(FieldError::getDefaultMessage).toList();
	} else {
	    try {
		status = khachHangDAO.update(khachHang);
	    } catch (Exception e) {
		errors.add(e.getMessage());
	    }
	}
	model.addAttribute("errors", errors);
	model.addAttribute("success", status);
	model.addAttribute("khachHang", khachHang);
	return "pages/khach-hang/cap-nhat";
    }

    @GetMapping("/xoa-khach-hang")
    public String update(@RequestParam String maKhachHang) {
	khachHangDAO.delete(maKhachHang);
	return "redirect:/khach-hang/danh-sach";
    }

    @GetMapping("/danh-sach-su-dung-dich-vu")
    public String getAllUsingService(Model model, @RequestParam(required = false, defaultValue = "1") int page) {
	ResponseData responseData = new ResponseData(200, null);
	page = page < 1 ? 1 : page;
	int pageSize = 10;
	PagedResult<SuDungDichVuMay> pagedResult = khachHangDAO.getAllUsingService(page, pageSize);
	responseData.setObject(pagedResult);
	model.addAttribute("responseData", responseData);
	return "pages/khach-hang/danh-sach-su-dung-dich-vu";
    }
}