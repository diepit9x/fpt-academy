<%@page import="util.SessionUtil"%>
<%@page import="entities.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%
 	Member member = SessionUtil.getMember(session);
 if (member == null) {
%>
	 <h2>Please log in to do this</h2>
<%
      return;
 }
 %>

<h2>Add Content</h2>
<div class="profile-box">
  <div class="login-header">Profile from elements</div>
  <form class="profile-body">
    <div class="form-submit">
      <div class="form-group">
        <label for="title">Title</label>
        <input type="text" class="form-control" id="title" placeholder="Enter the title" />
        <span class="text-danger" id="titleError"></span>
      </div>
      <div class="form-group">
        <label for="brief">Brief</label>
        <textarea class="form-control" id="brief" rows="3" placeholder=""></textarea>
        <span class="text-danger" id="briefError"></span>
      </div>
      <div class="form-group">
        <label for="content">Content</label>
        <textarea class="form-control" id="content" rows="5" placeholder=""></textarea>
        <span class="text-danger" id="contentError"></span>
      </div>
      <button type="submit" onclick="validateFormContent()" class="btn btn-outline-dark">Submit Button</button>
      <button type="reset" class="btn btn-outline-dark">Reset Button</button>
    </div>
  </form>
</div>