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
    <title>Sign In</title>
  </head>
  <body>
    <div class="container login-container">
      <div class="login-box">
        <div class="login-header">Please Sign In</div>
        <div class="login-body">
          <form class="form-submit">
            <div class="form-group">
              <input type="email" class="form-control" id="email" placeholder="E-mail" />
              <span class="text-danger" id="emailError"></span>
            </div>
            <div class="form-group">
              <input type="password" class="form-control" id="password" placeholder="Password" />
              <span class="text-danger" id="passwordError"></span>
            </div>
            <div class="form-group form-check">
              <input type="checkbox" class="form-check-input" id="remember" />
              <label class="form-check-label" for="remember">Remember Me</label>
            </div>
            <button type="submit" onclick="validateLoginForm()" class="btn btn-primary btn-block">Login</button>
            <p class="text-left mt-3"><a href="register.jsp">Click here to Register</a></p>
          </form>
        </div>
      </div>
    </div>
  </body>
  
     <script src="assets/jquery/jquery-3.7.1.min.js"></script>
    <script src="assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="assets/js/validate-form.js"></script>
</html>
