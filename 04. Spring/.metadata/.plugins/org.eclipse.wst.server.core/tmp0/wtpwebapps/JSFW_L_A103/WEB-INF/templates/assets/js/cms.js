$(document).ready(function() {
	$(".main-body").load("pages/view-content.html");
});
$(document).on("click", ".load-page", function(e) {
	$("#keyword").val('');
	e.preventDefault(); // Ngăn chặn hành vi mặc định của thẻ <a>
	$(".main-body").html("<h2>Loading...<h2>");
	var url = $(this).attr("ref"); // Lấy giá trị của thuộc tính ref
	loadPage(url);
});

function loadPage(url) {
	$.ajax({
		type: "GET",
		url: url,
		success: function(html) {
			setTimeout(function() {
				$(".main-body").html(html);
			}, 100);
		},
		error: function() {
			$(".main-body").html("404 NOT FOUND");
		},
	});
}

$(document).on("submit", ".login-form", function(event) {
	event.preventDefault();
	const loginBtn = $(".loginBtn");

	loginBtn.prop("disabled", true);
	$(".msg-response").empty();
	$.ajax({
		url: projectName + "/member/login",
		type: "POST",
		data: $(this).serialize(),
		contentType: "application/x-www-form-urlencoded",
		dataType: "json",
		success: function(response) {
			$(".header-success").text("Login successfully");
			setTimeout(function() {
				window.location.href = projectName;
			}, 1000);
		},
		error: function(xhr) {
			loginBtn.prop("disabled", false);
			let response = xhr.responseJSON;
			if (response.object) {
				debugger;
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

$(document).on("submit", ".register-form", function(event) {
	event.preventDefault();
	const registerBtn = $(".registerBtn");

	registerBtn.prop("disabled", true);
	$(".msg-response").empty();

	$.ajax({
		url: projectName + "/member/register",
		type: "POST",
		data: $(this).serialize(),
		contentType: "application/x-www-form-urlencoded",
		dataType: "json",
		success: function(response) {
			$(".header-success").text("Register successfully");
			setTimeout(function() {
				window.location.href = projectName + "/login";
			}, 1000);
		},
		error: function(xhr) {
			registerBtn.prop("disabled", false);
			let response = xhr.responseJSON;
			if (response.object) {
				debugger;
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


$(document).on("submit", ".profile-form", function(event) {
	event.preventDefault();
	const registerBtn = $(".profileBtn");

	registerBtn.prop("disabled", true);
	$(".msg-response").empty();

	$.ajax({
		url: projectName + "/member/edit-profile",
		type: "POST",
		data: $(this).serialize(),
		contentType: "application/x-www-form-urlencoded",
		dataType: "json",
		success: function(response) {
			$(".header-success").text("Update successfully");
			setTimeout(function() {
				window.location.href = "";
			}, 500);
		},
		error: function(xhr) {
			registerBtn.prop("disabled", false);
			let response = xhr.responseJSON;
			if (response.object) {
				debugger;
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

function loadAllContents(page = 1) {
	var keyword = $("#keyword").val();
	$.ajax({
		url: projectName + "/content/all-contents",
		type: "GET",
		data: { page: page, keyword: keyword },
		dataType: "json",
		success: function(response) {
			const contentObject = response.object;
			const contents = contentObject.contents;
			const totalPages = contentObject.totalPages;
			debugger
			let rows = '';
			contents.forEach(function(content) {
				const createDate = new Date(content.createDate[0], content.createDate[1] - 1, content.createDate[2], content.createDate[3], content.createDate[4], content.createDate[5]);
				const formattedDate = createDate.toLocaleString('en-GB', { day: '2-digit', month: '2-digit', year: 'numeric', hour: '2-digit', minute: '2-digit' });

				rows += '<tr><td>' + content.id + '</td><td style="max-width: 40%">' + content.title + '</td><td style="max-width: 40%">' + content.brief + '</td><td>' + formattedDate + '</td><td><button class="btn btn-sm btn-info load-page" ref="' + projectName + '/pages/form-content/' + content.id + '">Edit</button><button class="btn btn-sm btn-danger" onclick="deleteContent(' + content.id + ')">Delete</button></td></tr>';
			});

			$('#content-table-body').html(rows);

			// Update pagination controls
			let paginationControls = '';
			if (page > 1) {
				paginationControls += '<button class="btn btn-outline-secondary mr-1" onclick="loadAllContents(' + (page - 1) + ', \'' + keyword + '\')">Previous</button>';
			}
			if (page < totalPages) {
				paginationControls += '<button class="btn btn-outline-secondary mr-1" onclick="loadAllContents(' + (page + 1) + ', \'' + keyword + '\')">Next</button>';
			}
			$('#pagination-controls').html(paginationControls);
		},
		error: function(xhr) {
			const response = xhr.responseJSON;
			let msg = response && response.object ? response.object : "Có lỗi xảy ra";
			$(".profile-box").html('<div class="alert alert-danger">' + msg + "</div>");
		}
	});
}

function deleteContent(contentId) {
	// Hiển thị hộp thoại xác nhận
	if (confirm("Are you sure you want to delete this content?")) {
		$.ajax({
			url: projectName + "/content/delete/" + contentId,
			type: "DELETE",
			dataType: "json",
			success: function(response) {
				alert("Delete successfully");
				loadAllContents();
			},
			error: function(xhr) {
				const response = xhr.responseJSON;
				let msg = response && response.object ? response.object : "Có lỗi xảy ra";
				alert(msg);
			}
		});
	} else {
		// Thực hiện nếu người dùng hủy thao tác
		alert("Delete action was canceled.");
	}
}

$(document).on("click", ".searchBtn", function() {
	var url = "pages/view-content";
	loadPage(url, function() {
	        loadAllContents();
	    });
	
});


