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
<h2>Edit Profile</h2>
<div class="profile-box">
	<div class="login-header">Profile from elements</div>
	<div class="profile-body">
		<form class="form-submit">
		<input type="hidden" id="idMember" value="<%=member.getId() %>">
			<div class="form-group">
				<label for="firstName">First Name</label> <input type="text"
					class="form-control" id="firstName" value="<%=member.getFirstName() %>"
					placeholder="Enter the first name" /> <span class="text-danger"
					id="firstNameError"></span>
			</div>
			<div class="form-group">
				<label for="lastName">Last Name</label> <input type="text"
					class="form-control" id="lastName" value="<%=member.getLastName() %>"
					placeholder="Enter the last name" /> <span class="text-danger"
					id="lastNameError"></span>
			</div>
			<div class="form-group">
				<label for="email">Email</label>
				<div class="form-control email-form"> <%=member.getEmail() %></div>
			</div>
			<div class="form-group">
				<label for="phone">Phone</label> <input type="tel"
					class="form-control" id="phone"  value="<%=member.getPhone()%>"
					placeholder="Enter your phone number" /> <span class="text-danger"
					id="phoneError"></span>
			</div>
			<div class="form-group">
				<label for="description">Description</label>
				<textarea class="form-control" id="description" rows="3"
					placeholder=""><%=member.getDescription()%></textarea>
				<span class="text-danger" id="descriptionError"></span>
			</div>
			<button type="submit" onclick="validateEditProfile()"
				class="btn btn-outline-dark">Submit Button</button>
			<button type="reset" class="btn btn-outline-dark">Reset
				Button</button>
		</form>
	</div>
</div>