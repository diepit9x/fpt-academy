<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <div class="container login-container">
      <div class="login-box">
        <div class="login-header">Register</div>
        <div class="login-body">
          <form class="register-form">
            <div class="form-group text-center">
              <span class="msg-response text-danger header-error"></span>
              <span class="msg-response text-success header-success"></span>
            </div>
            <div class="form-group">
              <input type="text" class="form-control" id="username" name="username" placeholder="Username" />
              <span class="msg-response text-danger username-error"></span>
            </div>
            <div class="form-group">
              <input type="email" class="form-control" id="email" name="email" placeholder="E-mail" />
              <span class="msg-response text-danger email-error"></span>
            </div>
            <div class="form-group">
              <input type="password" class="form-control" id="password" name="password" placeholder="Password" />
              <span class="msg-response text-danger password-error"></span>
            </div>
            <div class="form-group">
              <input type="password" class="form-control" id="rePassword" name="rePassword" placeholder="Repassword" />
              <span class="msg-response text-danger rePassword-error"></span>
            </div>
            <button type="submit" class="btn btn-primary btn-block registerBtn">Register</button>
            <p class="text-left mt-3"><a href="${pageContext.request.contextPath}/login">Click here to Login</a></p>
          </form>
        </div>
      </div>
    </div>