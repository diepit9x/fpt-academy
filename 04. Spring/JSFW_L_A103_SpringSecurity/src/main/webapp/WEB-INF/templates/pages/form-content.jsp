<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<h2>
<c:if test="${not empty contentId}">Edit content id: ${contentId}</c:if>
<c:if test="${empty contentId}">Add content</c:if>
</h2>
<div class="profile-box">
  <div class="login-header">Content from elements</div>
  <form class="profile-body creater-content-form">
    <div class="form-submit">
    <c:if test="${not empty contentId}">
    <input type="hidden" name="contentId" value="${contentId}" />
    </c:if>
    <div class="form-group text-center">
              <span class="msg-response text-danger header-error"></span>
              <span class="msg-response text-success header-success"></span>
            </div>
      <div class="form-group">
        <label for="title">Title</label>
        <input type="text" class="form-control" name="title" id="title" placeholder="Enter the title" />
        <span class="msg-response text-danger title-error" id=""></span>
      </div>
      <div class="form-group">
        <label for="brief">Brief</label>
        <textarea class="form-control" name="brief"  id="brief" rows="3" placeholder=""></textarea>
        <span class="msg-response text-danger brief-error" id=""></span>
      </div>
      <div class="form-group">
        <label for="content">Content</label>
        <textarea class="form-control" name="content" id="content" rows="5" placeholder=""></textarea>
        <span class="msg-response text-danger content-error" id=""></span>
      </div>
      <button type="submit" class="btn btn-outline-dark formBtn">Submit Button</button>
      <button type="reset" class="btn btn-outline-dark formBtn">Reset Button</button>
    </div>
  </form>
</div>
<c:if test="${not empty contentId}">
<script>
var contentId = "${contentId}";
var apiUrl = "${pageContext.request.contextPath}/content/update";
$(document).ready(function() {
    $.ajax({
        url: "${pageContext.request.contextPath}/content/get-content/"+contentId,
        type: "GET",
        dataType: "json",
        success: function(response) {
            var profileData = response.object;

            // Populate form fields with profile data using jQuery
            $("#title").val(profileData.title);
            $("#brief").val(profileData.brief);
            $("#content").val(profileData.content);
        },
        error: function(xhr) {
            const response = xhr.responseJSON;
            let msg = response && response.object ? response.object : "Có lỗi xảy ra";
            $(".creater-content-form").html('<div class="alert alert-danger">' + msg + "</div>");
        }
    });
});
</script>
</c:if>
<c:if test="${empty contentId}">
<script>
var apiUrl = "${pageContext.request.contextPath}/content/create";
</script>
</c:if>
<script type="text/javascript">




$(document).on("submit", ".creater-content-form", function(event) {
	event.preventDefault();
	var formBtn = $(".formBtn");

	formBtn.prop("disabled", true);
	$(".msg-response").empty();

	$.ajax({
		url: apiUrl,
		type: "POST",
		data: $(this).serialize(),
		contentType: "application/x-www-form-urlencoded",
		dataType: "json",
		success: function(response) {
			$(".header-success").text("Successfully");
			setTimeout(function() {
				window.location.href = "";
			}, 1000);
		},
		error: function(xhr) {
			formBtn.prop("disabled", false);
			let response = xhr.responseJSON;
			if (response.object) {
				// Kiểm tra xem response.object có phải là đối tượng không
				if (typeof response.object === "object" && !Array.isArray(response.object)) {
					// Xử lý khi response.object là đối tượng chứa lỗi của trường
					let fieldErrors = response.object;
					for (let field in fieldErrors) {
						let errorMessage = fieldErrors[field];
						$("." + field + "-error").text(errorMessage);
					}
				} else {
					$(".header-error").text(response.object);
				}
			} else {
				alert("Đã xảy ra lỗi. Vui lòng thử lại sau.");
			}
		},
	});
});
</script>