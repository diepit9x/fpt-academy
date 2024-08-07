<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	// Lấy giá trị của thuộc tính "pageTitle", nếu không có thì gán giá trị mặc định
	String pageTitle = request.getAttribute("pageTitle") == null ? "Default Title": (String) request.getAttribute("pageTitle");
    %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title><%=pageTitle %></title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/bootstrap/css/bootstrap.min.css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/style.css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/font-awesome/css/font-awesome.min.css" />
    <script src="<%=request.getContextPath()%>/assets/jquery/jquery-3.7.1.min.js"></script>
  </head>
  <body>
    <header>
      <div class="container-fluid">
        <div class="flex-row">
          <div class="header bg-dark">
            <nav class="navbar navbar-expand-md navbar-dark bg-dark">
              <a class="navbar-brand" href="#">ATJB OTP1</a>
              <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
              </button>
              <div class="collapse navbar-collapse" id="navbarNavDropdown">
                <ul class="navbar-nav">
                  <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" role="button" data-toggle="dropdown" aria-expanded="false"> máy</a>
                    <div class="dropdown-menu bg-dark">
                      <a class="dropdown-item" href="<%=request.getContextPath()%>/may/tao-moi">Tạo mới máy</a>
                      <a class="dropdown-item" href="<%=request.getContextPath()%>/may/danh-sach">Danh sách máy</a>
                      <a class="dropdown-item" href="<%=request.getContextPath()%>/may/dang-ky-su-dung">Đăng kí sử dụng máy</a>
                    </div>
                  </li>
                  <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" role="button" data-toggle="dropdown" aria-expanded="false"> khách hàng</a>
                    <div class="dropdown-menu bg-dark">
                      <a class="dropdown-item" href="<%=request.getContextPath()%>/khach-hang/tao-moi">Tạo mới khách hàng</a>
                      <a class="dropdown-item" href="<%=request.getContextPath()%>/khach-hang/danh-sach">Danh sách khách hàng</a>
                      <a class="dropdown-item" href="<%=request.getContextPath()%>/khach-hang/danh-sach-su-dung-dich-vu">Danh sách sử dụng dịch vụ</a>
                    </div>
                  </li>
                  <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" role="button" data-toggle="dropdown" aria-expanded="false"> dịch vụ</a>
                    <div class="dropdown-menu bg-dark">
                      <a class="dropdown-item" href="<%=request.getContextPath()%>/dich-vu/tao-moi">Tạo mới dịch vụ</a>
                      <a class="dropdown-item" href="<%=request.getContextPath()%>/dich-vu/danh-sach">Danh sách dịch vụ</a>
                      <a class="dropdown-item" href="<%=request.getContextPath()%>/dich-vu/dang-ky-su-dung">Đăng kí sử dụng dịch vụ</a>
                    </div>
                  </li>
                </ul>
              </div>
            </nav>
          </div>
        </div>
      </div>
    </header>
    <section class="section-content">
  <div class="container pt-4">
    <div class="flex-row">
      <div class="content">