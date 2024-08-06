<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <div class="container login-container">
      <div class="login-box">
        <div class="login-header">Please Sign In</div>
        <div class="login-body">
          <form class="login-form">
            <div class="form-group text-center">
              <span class="msg-response text-danger header-error"></span>
              <span class="msg-response text-success header-success"></span>
            </div>
            <div class="form-group">
              <input type="email" class="form-control" name="email" id="email" placeholder="E-mail" />
              <span class="msg-response text-danger email-error"></span>
            </div>
            <div class="form-group">
              <input type="password" class="form-control" name="password" id="password" placeholder="Password" />
              <span class="msg-response text-danger password-error"></span>
            </div>
            <div class="form-group form-check">
              <input type="checkbox" class="form-check-input" name="remember" id="remember" />
              <label class="form-check-label" for="remember">Remember Me</label>
            </div>
            <button type="submit" class="btn btn-primary btn-block loginBtn">Login</button>
            <p class="text-left mt-3"><a href="${pageContext.request.contextPath}/register">Click here to Register</a></p>
          </form>
        </div>
      </div>
    </div>