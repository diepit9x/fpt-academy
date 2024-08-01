package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daos.KhachHangDAO;
import entities.KhachHang;
import entities.SuDungDichVuMay;
import models.PagedResult;
import models.ResponseData;

@WebServlet("/khach-hang/*")
public class KhachHangController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private KhachHangDAO khachHangDAO;

	public KhachHangController() {
		super();
		khachHangDAO = new KhachHangDAO();
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
		case "xoa-khach-hang": {
			delete(request, response);
			break;
		}
		case "danh-sach-su-dung-dich-vu": {
			getAllUsingService(request, response);
			break;
		}
		default:
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/pages/404.jsp");
			rd.forward(request, response);
		}
	}
	private void getAllUsingService(HttpServletRequest request, HttpServletResponse response) {
		String url = "/WEB-INF/pages/khach-hang/danh-sach-su-dung-dich-vu.jsp";
		ResponseData responseData = new ResponseData(200, null);
		int page = Integer.parseInt(request.getParameter("page") != null ? request.getParameter("page") : "1");
		int pageSize = 20;

		PagedResult<SuDungDichVuMay> pagedResult = khachHangDAO.getAllUsingService(page, pageSize);
		responseData.setObject(pagedResult);

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
	
	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// print
		PrintWriter out = response.getWriter();
		// Status
		String maKhachHang = request.getParameter("maKhachHang");
		try {
			boolean status = khachHangDAO.delete(maKhachHang);
			if (status) {
				out.println("Xoa thanh cong");
				response.sendRedirect(request.getContextPath() + "/khach-hang/danh-sach");
			} else {
				out.println("Xoa that bai");
			}
		} catch (Exception e) {
			out.println(e.getMessage());
		}

	}

	private void update(HttpServletRequest request, HttpServletResponse response) {
		//Status
		ResponseData responseData = new ResponseData(400, null);
		String maKhachHang = request.getParameter("maKhachHang");
		String tenKhachHang = request.getParameter("tenKhachHang");
		String diaChi = request.getParameter("diaChi");
		String soDienThoai = request.getParameter("soDienThoai");
		String email = request.getParameter("email");
		
	    if(maKhachHang != null) {
	    	try {
				KhachHang khachHang = khachHangDAO.findById(maKhachHang);
		    	if (khachHang != null) {
		    		if(tenKhachHang !=  null && diaChi != null && soDienThoai != null && email != null) {
				    	KhachHang updateKhachHang = new KhachHang(maKhachHang, tenKhachHang, diaChi, soDienThoai, email);
				    	try {
				    		boolean status = khachHangDAO.update(updateKhachHang);
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
						responseData.setObject(khachHang);
				    }
				} else {
					responseData.setObject("Mã khách hàng không tồn tại");
				}
				
			} catch (Exception e) {
				responseData.setObject(e.getMessage());
			}
	    	
	    }
	    
		request.setAttribute("responseData", responseData);
	    String url = "/WEB-INF/pages/khach-hang/cap-nhat.jsp";
	    
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
		String url = "/WEB-INF/pages/khach-hang/danh-sach.jsp";
		ResponseData responseData = new ResponseData(200, null);
		String keyword = request.getParameter("keyword");
		int page = Integer.parseInt(request.getParameter("page") != null ? request.getParameter("page") : "1");
		int pageSize = 2;

		PagedResult<KhachHang> khachhang = khachHangDAO.findAll(keyword, page, pageSize);
		responseData.setObject(khachhang);

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
		String url = "/WEB-INF/pages/khach-hang/tao-moi.jsp";
		ResponseData responseData = new ResponseData(400, null);

		String maKhachHang = request.getParameter("maKhachHang");
		String tenKhachHang = request.getParameter("tenKhachHang");
		String diaChi = request.getParameter("diaChi");
		String soDienThoai = request.getParameter("soDienThoai");
		String email = request.getParameter("email");
		if (maKhachHang != null && tenKhachHang != null && diaChi != null && soDienThoai != null && email != null) {

			KhachHang khachHang = new KhachHang();
			khachHang.setMaKH(maKhachHang);
			khachHang.setTenKH(tenKhachHang);
			khachHang.setDiaChi(diaChi);
			khachHang.setDiaChi(diaChi);
			khachHang.setSoDienThoai(soDienThoai);
			khachHang.setEmail(email);

			try {
				boolean status = khachHangDAO.insert(khachHang);
				if (status) {
					responseData.setStatus(200);
					responseData.setObject("Tạo khách hàng thành công");
				} else {
					responseData.setObject("Tạo khách hàng thất bại");
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
