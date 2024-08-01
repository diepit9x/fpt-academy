<%@page import="util.SessionUtil"%>
<%@page import="entities.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%
 	Member member = SessionUtil.getMember(session);
 if (member == null) {
	 //chuyển hướng login
	 response.sendRedirect("login.jsp");
      return;
 }
 %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css" />
    <link rel="stylesheet" href="assets/font-awesome/css/font-awesome.css" />
    <link rel="stylesheet" href="assets/css/style.css" />
    <title>Edit Profile</title>
  </head>
  <body>
    <header>
      <nav class="navbar navbar-expand navbar-light card-header container">
        <a class="navbar-brand">CMS</a>
        <div class="ml-auto btn-group">
          <div type="button" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><i class="fa fa-user" aria-hidden="true"></i></div>
          <ul class="dropdown-menu dropdown-menu-right">
            <li class="dropdown-item border-bottom load-page" ref="pages/edit-profile.jsp" id="loadEditProfile"><i class="fa fa-user" aria-hidden="true"></i> User profile</li>
            <li class="dropdown-item" id="logout"><i class="fa fa-sign-out" aria-hidden="true"></i> Logout</li>
          </ul>
        </div>
      </nav>
    </header>
    <section>
      <div class="container">
        <div class="row">
          <nav class="col-md-2 sidebar card-header">
            <div class="sidebar-sticky">
              <ul class="nav flex-column">
                <li class="nav-item">
                  <form class="form-inline navbar-toggler">
                    <div class="input-group">
                      <input type="text" class="form-control" placeholder="Search..." aria-label="Search" />
                      <div class="input-group-append">
                        <button class="btn btn-outline-secondary" type="button">
                          <span class="fa fa-search"></span>
                        </button>
                      </div>
                    </div>
                  </form>
                </li>
                <li class="nav-item">
                  <a ref="pages/view-content.jsp" class="nav-link load-page"><i class="fa fa-table" aria-hidden="true"></i> View content</a>
                </li>
                <li class="nav-item">
                  <a ref="pages/form-content.jsp" class="nav-link load-page"><i class="fa fa-pencil-square-o" aria-hidden="true"></i> From content</a>
                </li>
              </ul>
            </div>
          </nav>
          <main class="col-md-10 main-body"></main>
        </div>
      </div>
    </section>
    <script src="assets/jquery/jquery-3.7.1.min.js"></script>
    <script src="assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="assets/js/cms.js"></script>
    <script src="assets/js/validate-form.js"></script>
  </body>
</html>
    