package fa.training.controller;

import java.time.LocalDate;
import java.time.LocalTime;
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

import fa.training.daos.DichVuDAO;
import fa.training.daos.KhachHangDAO;
import fa.training.daos.MayDAO;
import fa.training.entities.DichVu;
import fa.training.entities.KhachHang;
import fa.training.entities.SuDungDichVu;
import fa.training.entities.SuDungMay;
import fa.training.models.PagedResult;
import fa.training.models.ResponseData;
import jakarta.validation.Valid;

@Controller
@RequestMapping(value = "/dich-vu")
public class DichVuController {
    @Autowired
    private DichVuDAO dichVuDAO;

    @Autowired
    private KhachHangDAO khachHangDAO;

    @Autowired
    private MayDAO mayDAO;

    @GetMapping("/tao-moi")
    public String create(Model model) {
	model.addAttribute("dichVu", new DichVu());
	return "pages/dich-vu/tao-moi";
    }

    @PostMapping("/tao-moi")
    public String create(Model model, @Valid @ModelAttribute DichVu dichVu, BindingResult bindingResult) {
	boolean status = false;
	List<String> errors = new ArrayList<>();
	if (bindingResult.hasErrors()) {
	    errors = bindingResult.getFieldErrors().stream().map(FieldError::getDefaultMessage).toList();
	} else {
	    try {
		status = dichVuDAO.insert(dichVu);
	    } catch (Exception e) {
		errors.add(e.getMessage());
	    }
	}
	model.addAttribute("errors", errors);
	model.addAttribute("success", status);
	model.addAttribute("dichVu", status ? new DichVu() : dichVu);
	return "pages/dich-vu/tao-moi";
    }

    @GetMapping("/danh-sach")
    public String getAll(@RequestParam(required = false, defaultValue = "1") int page,
	    @RequestParam(required = false) String keyword, Model model) {
	page = page < 1 ? 1 : page;
	int pageSize = 10;
	PagedResult<DichVu> pagedResult = dichVuDAO.findAll(keyword, page, pageSize);
	ResponseData responseData = new ResponseData(200, pagedResult);
	model.addAttribute("responseData", responseData);
	model.addAttribute("keyword", keyword);
	return "pages/dich-vu/danh-sach";
    }

    @GetMapping("/cap-nhat")
    public String update(Model model, @RequestParam String maDichVu) {
	DichVu dichVu = dichVuDAO.findById(maDichVu);
	if (dichVu == null) {
	    return "redirect:/dich-vu/danh-sach";
	}
	model.addAttribute("dichVu", dichVu);
	return "pages/dich-vu/cap-nhat";
    }

    @PostMapping("/cap-nhat")
    public String update(Model model, @Valid @ModelAttribute DichVu dichVu, BindingResult bindingResult) {
	boolean status = false;
	List<String> errors = new ArrayList<>();
	if (bindingResult.hasErrors()) {
	    errors = bindingResult.getFieldErrors().stream().map(FieldError::getDefaultMessage).toList();
	} else {
	    try {
		status = dichVuDAO.update(dichVu);
	    } catch (Exception e) {
		errors.add(e.getMessage());
	    }
	}
	model.addAttribute("errors", errors);
	model.addAttribute("success", status);
	model.addAttribute("dichVu", dichVu);
	return "pages/dich-vu/cap-nhat";
    }

    @GetMapping("/xoa-dich-vu")
    public String update(@RequestParam String maDichVu) {
	dichVuDAO.delete(maDichVu);
	return "redirect:/dich-vu/danh-sach";
    }

    @GetMapping("/dang-ky-su-dung")
    public String registerToUse() {
	return "pages/dich-vu/dang-ky-su-dung";
    }

    @PostMapping("/dang-ky-su-dung")
    public String registerToUse(@RequestParam(name = "maKhachHang", defaultValue = "") String maKhachHang,
	    @RequestParam(name = "maDichVu", defaultValue = "") String maDichVu,
	    @RequestParam(name = "soLuong", defaultValue = "0") Integer soLuong, Model model) {
	List<String> errors = new ArrayList<>();
	DichVu dichVu = dichVuDAO.findById(maDichVu);
	KhachHang khachHang = khachHangDAO.findById(maKhachHang);
	if (dichVu == null) {
	    errors.add("Mã dịch vụ không hợp lệ");
	}
	if (khachHang == null) {
	    errors.add("Mã khách hàng không hợp lệ");
	}
	if (soLuong <= 0) {
	    errors.add("Số lượng phải lớn hơn 0");
	}
	if (dichVu != null && khachHang != null && soLuong > 0) {
	    SuDungMay suDungMay = mayDAO.findSDMByKH(khachHang);
	    if (suDungMay == null) {
		errors.add("Khách hàng hiện tại chưa đăng kí sử dụng máy");
	    } else {
		SuDungDichVu suDungDichVu = new SuDungDichVu();
		suDungDichVu.setKhachHang(khachHang);
		suDungDichVu.setDichVu(dichVu);
		suDungDichVu.setNgaySuDung(LocalDate.now());
		suDungDichVu.setGioSuDung(LocalTime.now());
		suDungDichVu.setSoLuong(soLuong);
		suDungDichVu.setDonGia(dichVu.getDonGia());

		boolean status = dichVuDAO.registerToUse(suDungDichVu);
		if (!status) {
		    errors.add("Không thể đăng ký sử dụng máy");
		} else {
		    model.addAttribute("success", "Thành công");
		}
	    }
	}
	model.addAttribute("errors", errors);
	return "pages/dich-vu/dang-ky-su-dung";
    }
}
