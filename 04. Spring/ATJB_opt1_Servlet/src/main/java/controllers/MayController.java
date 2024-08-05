package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daos.KhachHangDAO;
import daos.MayDAO;
import entities.KhachHang;
import entities.May;
import entities.SuDungMay;
import models.PagedResult;
import models.ResponseData;
import models.SelectData;
import utils.HibernateValidator;

/**
 * Servlet implementation class MayController
 */
@WebServlet("/may/*")
public class MayController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MayDAO mayDAO;

	public MayController() {
		super();
		mayDAO = new MayDAO();
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
		case "xoa-may": {
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
		String url = "/WEB-INF/pages/may/dang-ky-su-dung.jsp";
		ResponseData responseData = new ResponseData(400, null);

		String maKhachHang = request.getParameter("maKhachHang");
		String maMay = request.getParameter("maMay");
		if (maKhachHang != null && maMay != null) {

			try {

				May may = mayDAO.findById(Integer.parseInt(maMay));

				if (may == null || may.getTrangThai().equals("1")) {
					KhachHangDAO khachHangDAO = new KhachHangDAO();
					KhachHang khachHang = khachHangDAO.findById(maKhachHang);

					SuDungMay suDungMay = mayDAO.findSDMByKH(khachHang);
					if (suDungMay == null) {
						LocalDate ngayBDSD = LocalDate.now();
						LocalTime gioBDSD = LocalTime.now();
						Integer thoigianSD = null;

						may.setTrangThai("2");
						suDungMay = new SuDungMay();
						suDungMay.setKhachHang(khachHang);
						suDungMay.setMay(may);
						suDungMay.setNgaybatDauSuDung(ngayBDSD);
						suDungMay.setGioBatDauSuDung(gioBDSD);
						suDungMay.setThoiGianSuDung(thoigianSD);

						boolean status = mayDAO.registerToUse(suDungMay);
						if (status) {
							responseData.setStatus(200);
						} else {
							responseData.setObject("Đăng ký sử dụng thất bại");
						}
					} else {
						responseData.setObject("Khách hàng đang sử dụng máy khác rồi");
					}

				} else {
					String msg = may == null ? "Mã máy không hợp lệ"
							: "Mã máy " + may.getMaMay() + " " + SelectData.trangThai((may.getTrangThai()));
					responseData.setObject(msg);
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
		String maMay = request.getParameter("maMay");
		try {
			Integer maMayInt = Integer.parseInt(maMay);
			boolean status = mayDAO.delete(maMayInt);
			if (status) {
				out.println("Xoa thanh cong");
				response.sendRedirect(request.getContextPath() + "/may/danh-sach");
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
		String maMay = request.getParameter("maMay");
		String viTri = request.getParameter("viTri");
		String trangThai = request.getParameter("trangThai");

		if (maMay != null) {
			try {
				Integer maMayInt = Integer.parseInt(maMay);
				May may = mayDAO.findById(maMayInt);
				if (may != null) {
					if (viTri != null && trangThai != null) {
						May updateMay = new May(maMayInt, viTri, trangThai);
						try {
							boolean status = mayDAO.update(updateMay);

							if (may.getTrangThai().equals("2") && !updateMay.getTrangThai().equals("2")) {
								mayDAO.updateThoiGianSuDung(updateMay);
							}
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
						responseData.setObject(may);
					}
				} else {
					responseData.setObject("Mã máy không tồn tại");
				}

			} catch (Exception e) {
				responseData.setObject(e.getMessage());
			}

		}

		request.setAttribute("responseData", responseData);
		String url = "/WEB-INF/pages/may/cap-nhat.jsp";

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
		// Status
		ResponseData responseData = new ResponseData(200, null);
		String keyword = request.getParameter("keyword");
		int page = Integer.parseInt(request.getParameter("page") != null ? request.getParameter("page") : "1");
		int pageSize = 2;

		PagedResult<May> mays = mayDAO.findAllMay(keyword, page, pageSize);
		responseData.setObject(mays);

		if (keyword != null) {
			request.setAttribute("keyword", keyword);
		}

		request.setAttribute("responseData", responseData);
		String url = "/WEB-INF/pages/may/danh-sach.jsp";

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
		// url
		String url = "/WEB-INF/pages/may/tao-moi.jsp";
		// Status
		ResponseData responseData = new ResponseData(400, null);
		// Collect data
		String viTri = request.getParameter("viTri");
		String trangThai = request.getParameter("trangThai");
		if (viTri != null && trangThai != null) {
			May may = new May();
			may.setViTri(viTri);
			may.setTrangThai(trangThai);
			List<String> errors = HibernateValidator.getViolations(may);

			if (errors.size() > 0) {
				responseData.setObject(errors);
			} else {
				try {
					boolean status = mayDAO.insert(may);
					if (status) {
						responseData.setStatus(200);
					} else {
						responseData.setObject("Không tạo được dữ liệu mới");
					}
				} catch (Exception e) {
					responseData.setObject(e.getMessage());
				}
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
