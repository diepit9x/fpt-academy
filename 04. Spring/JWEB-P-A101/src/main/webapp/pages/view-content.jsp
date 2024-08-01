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
<h2>View Content</h2>
<div class="profile-box">
  <div class="login-header">View content list</div>
  <div class="profile-body">
    <table class="table table-bordered">
      <thead>
        <tr>
          <th>#</th>
          <th>Title</th>
          <th>Brief</th>
          <th>Create Date</th>
        </tr>
      </thead>
      <tbody>
        <tr class="tab-content">
          <td>1</td>
          <td>Lorem ipsum dolor sit amet.</td>
          <td>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Nihil repellendus iure dolores laudantium harum minus.</td>
          <td>14/10/2003 10:30</td>
        </tr>
        <tr>
          <td>2</td>
          <td>Lorem ipsum dolor sit amet.</td>
          <td>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Nihil repellendus iure dolores laudantium harum minus.</td>
          <td>14/10/2003 10:30</td>
        </tr>
        <tr>
          <td>3</td>
          <td>Lorem ipsum dolor sit amet.</td>
          <td>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Nihil repellendus iure dolores laudantium harum minus.</td>
          <td>14/10/2003 10:30</td>
        </tr>
      </tbody>
    </table>
  </div>
</div>