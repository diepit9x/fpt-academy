<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<h2>View Content</h2>
<div class="profile-box">
  <div class="login-header">View content list</div>
  <div class="profile-body table-responsive">
    <table class="table table-bordered">
      <thead>
        <tr>
          <th>#</th>
          <th>Title</th>
          <th>Brief</th>
          <th>Create Date</th>
          <th>Actions</th>
        </tr>
      </thead>
      <tbody id="content-table-body">
      </tbody>
    </table>
    <div id="pagination-controls"></div>
  </div>
</div>
<script>
loadAllContents()
</script>