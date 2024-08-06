<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.min.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css" />
    <title>Sign In</title>
  </head>
  <body>
 		<jsp:include page="pages/login.jsp" />
  </body>
  <script src="${pageContext.request.contextPath}/assets/jquery/jquery-3.7.1.min.js"></script>
  <script src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.min.js"></script>
  <script>
  		const projectName = "${pageContext.request.contextPath}";
  </script>
  <script src="${pageContext.request.contextPath}/assets/js/cms.js"></script>
</html>
