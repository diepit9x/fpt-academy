package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daos.DichVuDAO;
import daos.KhachHangDAO;
import daos.MayDAO;
import entities.DichVu;
import entities.KhachHang;
import entities.SuDungDichVu;
import entities.SuDungMay;
import models.PagedResult;
import models.ResponseData;

@WebServlet("/dich-vu/*")
public class DichVuController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DichVuDAO dichVuDAO;

	public DichVuController() {
		super();
		dichVuDAO = new DichVuDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getPathInfo();
		if (action != null) {
			action = action.substring(1);
		}
		switch (action) {
		case "tao-moi": {
			create(request, response);
			break;
		}
		case "danh-sach": {
			getAll(request, response);
			break;
		}
		case "cap-nhat": {
			update(request, response);
			break;
		}
		case "xoa-dich-vu": {
			delete(request, response);
			break;
		}
		case "dang-ky-su-dung": {
			registerToUse(request, response);
			break;
		}
		default:
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/pages/404.jsp");
			rd.forward(request, response);
		}
	}

	private void registerToUse(HttpServletRequest request, HttpServletResponse response) {
		String url = "/WEB-INF/pages/dich-vu/dang-ky-su-dung.jsp";
		ResponseData responseData = new ResponseData(400, null);

		String maKhachHang = request.getParameter("maKhachHang");
		String maDichVu = request.getParameter("maDichVu");
		String soLuong = request.getParameter("soLuong");

		if (maKhachHang != null && maDichVu != null && soLuong != null) {
			try {
				KhachHangDAO khachHangDAO = new KhachHangDAO();
				MayDAO mayDAO = new MayDAO();

				KhachHang khachHang = khachHangDAO.findById(maKhachHang);
				DichVu dichVu = dichVuDAO.findById(maDichVu);
				
				SuDungMay suDungMay = mayDAO.findSDMByKH(khachHang);
				if (suDungMay != null) {
					
					LocalDate ngaySD = LocalDate.now();
					LocalTime gioSD = LocalTime.now();
					Integer soLuongSD = Integer.parseInt(soLuong);

					SuDungDichVu suDungDichVu = new SuDungDichVu();
					suDungDichVu.setKhachHang(khachHang);
					suDungDichVu.setDichVu(dichVu);
					suDungDichVu.setNgaySuDung(ngaySD);
					suDungDichVu.setGioSuDung(gioSD);
					suDungDichVu.setSoLuong(soLuongSD);
					suDungDichVu.setSuDungMay(suDungMay);
					suDungDichVu.setDonGia(dichVu.getDonGia());

					boolean status = dichVuDAO.registerToUse(suDungDichVu);
					if (status) {
						responseData.setStatus(200);
					} else {
						responseData.setObject("Đăng ký sử dụng thất bại");
					}
				} else {
					responseData.setObject("Khách hàng chưa đăng ký sử dụng máy tại thời điểm này");
				}

			} catch (Exception e) {
				responseData.setObject(e.getMessage());
			}
			request.setAttribute("responseData", responseData);
		}
		try {
			RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
			rd.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// print
		PrintWriter out = response.getWriter();
		// Status
		String maDichVu = request.getParameter("maDichVu");
		try {
			boolean status = dichVuDAO.delete(maDichVu);
			if (status) {
				out.println("Xoa thanh cong");
				response.sendRedirect(request.getContextPath() + "/dich-vu/danh-sach");
			} else {
				out.println("Xoa that bai");
			}
		} catch (Exception e) {
			out.println(e.getMessage());
		}

	}

	private void update(HttpServletRequest request, HttpServletResponse response) {
		// Status
		ResponseData responseData = new ResponseData(400, null);
		String maDichVu = request.getParameter("maDichVu");
		String tenDichVu = request.getParameter("tenDichVu");
		String donViTinh = request.getParameter("donViTinh");
		String donGia = request.getParameter("donGia");

		if (maDichVu != null) {
			try {
				DichVu dichVu = dichVuDAO.findById(maDichVu);
				if (dichVu != null) {
					if (tenDichVu != null && donViTinh != null && donGia != null) {
						DichVu updateDichVu = new DichVu(maDichVu, tenDichVu, donViTinh, Integer.parseInt(donGia));
						try {
							boolean status = dichVuDAO.update(updateDichVu);
							if (status) {
								responseData.setStatus(200);
								responseData.setObject("Cập nhật thành công");
							} else {
								responseData.setObject("Cập nhật thất bại");
							}
						} catch (Exception e) {
							responseData.setObject(e.getMessage());
						}

					} else {
						responseData.setStatus(201);
						responseData.setObject(dichVu);
					}
				} else {
					responseData.setObject("Mã dịch vụ không tồn tại");
				}

			} catch (Exception e) {
				responseData.setObject(e.getMessage());
			}

		}

		request.setAttribute("responseData", responseData);
		String url = "/WEB-INF/pages/dich-vu/cap-nhat.jsp";

		try {
			RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
			rd.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void getAll(HttpServletRequest request, HttpServletResponse response) {
		String url = "/WEB-INF/pages/dich-vu/danh-sach.jsp";
		ResponseData responseData = new ResponseData(200, null);
		String keyword = request.getParameter("keyword");
		int page = Integer.parseInt(request.getParameter("page") != null ? request.getParameter("page") : "1");
		int pageSize = 2;

		PagedResult<DichVu> dichvu = dichVuDAO.findAll(keyword, page, pageSize);
		responseData.setObject(dichvu);

		if (keyword != null) {
			request.setAttribute("keyword", keyword);
		}

		request.setAttribute("responseData", responseData);
		try {
			RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
			rd.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void create(HttpServletRequest request, HttpServletResponse response) {
		String url = "/WEB-INF/pages/dich-vu/tao-moi.jsp";
		ResponseData responseData = new ResponseData(400, null);

		String maDichVu = request.getParameter("maDichVu");
		String tenDichVu = request.getParameter("tenDichVu");
		String donViTinh = request.getParameter("donViTinh");
		String donGia = request.getParameter("donGia");
		if (maDichVu != null && tenDichVu != null && donViTinh != null && donGia != null) {
			DichVu dichVu = new DichVu();
			dichVu.setMaDV(maDichVu);
			dichVu.setTenDV(tenDichVu);
			dichVu.setDonViTinh(donViTinh);
			dichVu.setDonGia(Integer.parseInt(donGia));

			try {
				boolean status = dichVuDAO.insert(dichVu);
				if (status) {
					responseData.setStatus(200);
				} else {
					responseData.setObject("Tạo mới dịch vụ thất bại");
				}
			} catch (Exception e) {
				responseData.setObject(e.getMessage());
			}
			request.setAttribute("responseData", responseData);
		}
		try {
			RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
			rd.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}