<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>Login</title>
</head>
<body>
	<div align="center">
		<form:form action="dashboard" method="POST" modelAttribute="fresher">
			<table>
				<tr>
					<td colspan="2">
						<c:if test="${not empty param['error']}">
							<div style="color: red;">${param['error']}</div>
						</c:if>
					</td>
				</tr>
				<tr>
					<td><form:label path="username">Username</form:label></td>
					<td><form:input path="username" name="username" id="username" />
					</td>
				</tr>
				<tr>
					<td><form:label path="password">Password</form:label></td>
					<td><form:input path="password" type="password"
							name="password" id="password" /></td>
				</tr>
				<tr>
					<td></td>
					<td><form:button name="login" id="login">Login</form:button></td>
				</tr>
			</table>
		</form:form>
	</div>
</body>
</html>
