<%@page import="util.SessionUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="assets/font-awesome/css/font-awesome.css" />
<link rel="stylesheet" href="assets/css/style.css" />
<title>Register</title>
</head>
<body>
	<div class="container login-container">
		<div class="login-box">
			<div class="login-header">Register</div>
			<div class="login-body">
				<form class="form-submit" method="POST" action="MemberController">
					<input type="hidden" id="action" value="register" >
					<div class="form-group">
						<input type="text" class="form-control" id="username"
							placeholder="Username" /> <span class="text-danger"
							id="usernameError"></span>
					</div>
					<div class="form-group">
						<input type="email" class="form-control" id="email"
							placeholder="E-mail" /> <span class="text-danger"
							id="emailError"></span>
					</div>
					<div class="form-group">
						<input type="password" class="form-control" id="password"
							placeholder="Password" />
						<span class="text-danger" id="passwordError"></span>
					</div>
					<div class="form-group">
						<input type="password" class="form-control" id="rePassword"
							placeholder="Re Password" /> <span class="text-danger"
							id="rePasswordError"></span>
					</div>
					<button type="submit" onclick="validateRegisterForm()"
						class="btn btn-primary btn-block">Register</button>
					<p class="text-left mt-3">
						<a href="login.jsp">Click here to Login</a>
					</p>
				</form>
			</div>
		</div>
	</div>
</body>
<script src="assets/jquery/jquery-3.7.1.min.js"></script>
<script src="assets/bootstrap/js/bootstrap.min.js"></script>
<script src="assets/js/validate-form.js"></script>
</html>
