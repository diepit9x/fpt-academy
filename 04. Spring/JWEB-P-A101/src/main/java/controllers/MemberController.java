package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dtos.Register;
import dtos.ResponseData;
import dtos.UpdateMember;
import entities.Member;
import services.MemberService;
import services.MemberServiceImpl;
import util.HibernateValidator;
import util.JsonUtil;
import util.SessionUtil;

@WebServlet("/MemberController")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService;

	public MemberController() {
		super();
		this.memberService = new MemberServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String action = request.getParameter("action");
		if (action == null) {
			out.println(JsonUtil.toJson(new ResponseData(404, "action is invalid")));
			return;
		}
		String message = null;
		if (action.equals("register")) {
			message = register(request, response);
		} else if (action.equals("login")) {
			message = login(request, response);
		} else if (action.equals("logout")) {
			logout(request, response);
			response.sendRedirect("login.jsp");
		} else if (action.equals("update")) {
			message = update(request, response);
		} else {
			message = JsonUtil.toJson(new ResponseData(404, "action is invalid"));
		}
		out.println(message);
	}

	private String register(HttpServletRequest request, HttpServletResponse response) {
		ResponseData responseData = new ResponseData(400, null);
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String rePassword = request.getParameter("rePassword");

		Register register = Register.builder().username(username).email(email).password(password).rePassword(rePassword)
				.build();
		List<String> errors = HibernateValidator.getViolations(register);

		if (errors.size() > 0) {
			responseData.setData(errors.toString());
			return JsonUtil.toJson(responseData);
		}
		try {
			memberService.register(register);
			responseData.setStatus(200);
			return JsonUtil.toJson(responseData);
		} catch (Exception e) {
			responseData.setData(e.getMessage());
			return JsonUtil.toJson(responseData);
		}
	}

	private String login(HttpServletRequest request, HttpServletResponse response) {
	    ResponseData responseData = new ResponseData(400, null);
	    HttpSession session = request.getSession();
        
        if (SessionUtil.getMember(session) != null) {
            responseData.setData("Already logged in");
            return JsonUtil.toJson(responseData);
        }
	    
	    String email = request.getParameter("email");
	    String password = request.getParameter("password");

	    try {
	        Member member = memberService.login(email, password);
	        if (member != null) {
	        	SessionUtil.setMember(session, member);
	            responseData.setStatus(200);
	        } else {
	            responseData.setData("Email/password doesn't match");
	        }
	        return JsonUtil.toJson(responseData);
	    } catch (Exception e) {
	        responseData.setData(e.getMessage());
	        return JsonUtil.toJson(responseData);
	    }
	}
	
	private void logout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		SessionUtil.destroy(session);
	}
	
	private String update(HttpServletRequest request, HttpServletResponse response) {
		ResponseData responseData = new ResponseData(400, null);
		Integer id = Integer.parseInt(request.getParameter("id"));
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String phone = request.getParameter("phone");
		String description = request.getParameter("description");

		UpdateMember  updateMember = UpdateMember.builder()
				.id(id)
				.firstName(firstName)
				.lastName(lastName)
				.phone(phone)
				.description(description)
				.build();
		List<String> errors = HibernateValidator.getViolations(updateMember);

		if (errors.size() > 0) {
			responseData.setData(errors.toString());
			return JsonUtil.toJson(responseData);
		}
		try {
			memberService.update(updateMember);
			responseData.setStatus(200);
			logout(request, response);
			return JsonUtil.toJson(responseData);
		} catch (Exception e) {
			responseData.setData(e.getMessage());
			return JsonUtil.toJson(responseData);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
