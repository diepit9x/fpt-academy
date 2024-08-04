<%@page import="fa.training.util.SessionUtil"%>
<%@page import="fa.training.entity.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Member member = SessionUtil.getLoggedInUser(session);
%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/font-awesome/css/font-awesome.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.min.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css" />
    <script src="${pageContext.request.contextPath}/assets/jquery/jquery-3.7.1.min.js"></script>
    <title>CMS</title>
  </head>
  <body>
    <header>
      <nav class="navbar navbar-expand navbar-light card-header container">
        <a class="navbar-brand">CMS</a>
        <div class="ml-auto btn-group">
        <% if(member != null){%>
          <div type="button" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><i class="fa fa-user" aria-hidden="true"></i> <%=member.getUsername()%></div>
          <ul class="dropdown-menu dropdown-menu-right">
            <li class="dropdown-item border-bottom load-page" ref="${pageContext.request.contextPath}/pages/edit-profile" id="loadEditProfile"><i class="fa fa-user" aria-hidden="true"></i> User profile</li>
            <li class="dropdown-item"><a href="${pageContext.request.contextPath}/member/logout"><i class="fa fa-sign-out" aria-hidden="true"></i> Logout</a></li>
          </ul>
          <% } else { %>
          <a href="login" class="btn btn-info">Login</a>
          <% } %>
        </div>
      </nav>
    </header>
    <section>
      <div class="container">
        <div class="row">
          <nav class="col-md-2 sidebar card-header">
            <div class="sidebar-sticky">
              <ul class="nav flex-column">
                <div class="nav-item">
                  <form class="form-inline navbar-toggler">
                    <div class="input-group">
                      <input type="text" class="form-control" id="keyword" placeholder="Search..." aria-label="Search" />
                      <div class="input-group-append">
                        <button class="btn btn-outline-secondary searchBtn" type="button">
                          <span class="fa fa-search"></span>
                        </button>
                      </div>
                    </div>
                  </form>
                </div>
                <li class="nav-item">
                  <a ref="${pageContext.request.contextPath}/pages/view-content" class="nav-link load-page"><i class="fa fa-table" aria-hidden="true"></i> View content</a>
                </li>
                <li class="nav-item">
                  <a ref="${pageContext.request.contextPath}/pages/form-content" class="nav-link load-page"><i class="fa fa-pencil-square-o" aria-hidden="true"></i> From content</a>
                </li>
              </ul>
            </div>
          </nav>
          <main class="col-md-10 main-body"></main>
        </div>
      </div>
    </section>
    <script src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.min.js"></script>
    <script>
  		var projectName = "${pageContext.request.contextPath}";
  </script>
  <script src="${pageContext.request.contextPath}/assets/js/cms.js"></script>
  </body>
</html>