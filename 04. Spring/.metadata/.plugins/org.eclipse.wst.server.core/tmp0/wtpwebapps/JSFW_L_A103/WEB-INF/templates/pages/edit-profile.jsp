<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<h2>Edit Profile</h2>
<div class="profile-box">
  <div class="login-header">Profile from elements</div>
  <div class="profile-body">
    <form class="profile-form">
            <div class="form-group text-center">
              <span class="msg-response text-danger header-error"></span>
              <span class="msg-response text-success header-success"></span>
            </div>
      <div class="form-group">
        <label for="firstName">First Name</label>
        <input type="text" class="form-control" id="firstName" name="firstName" placeholder="Enter the first name" />
        <span class="msg-response text-danger firstName-error"></span>
      </div>
      <div class="form-group">
        <label for="lastName">Last Name</label>
        <input type="text" class="form-control" id="lastName" name="lastName" placeholder="Enter the last name" />
        <span class="msg-response text-danger lastName-error"></span>
      </div>
      <div class="form-group">
        <label for="email">Email</label>
        <div class="form-control email-form">your_email@example.com</div>
      </div>
      <div class="form-group">
        <label for="phone">Phone</label>
        <input type="tel" class="form-control" id="phone" name="phone" placeholder="Enter your phone number" />
        <span class="msg-response text-danger phone-error"></span>
      </div>
      <div class="form-group">
        <label for="description">Description</label>
        <textarea class="form-control" id="description" name="description" rows="3" placeholder=""></textarea>
        <span class="msg-response text-danger description-error"></span>
      </div>
      <button type="submit" class="btn btn-outline-dark profileBtn">Submit Button</button>
      <button type="reset" class="btn btn-outline-dark profileBtn">Reset Button</button>
    </form>
  </div>
</div>
<script>
$(document).ready(function() {
    $.ajax({
        url: "${pageContext.request.contextPath}/member/profile",
        type: "GET",
        dataType: "json",
        success: function(response) {
            const profileData = response.object;

            // Populate form fields with profile data using jQuery
            $("#firstName").val(profileData.firstName);
            $("#lastName").val(profileData.lastName);
            $(".email-form").text(profileData.email);
            $("#phone").val(profileData.phone);
            $("#description").val(profileData.description);
        },
        error: function(xhr) {
            const response = xhr.responseJSON;
            let msg = response && response.object ? response.object : "Có lỗi xảy ra";
            $(".profile-form").html('<div class="alert alert-danger">' + msg + "</div>");
        }
    });
});
</script>
