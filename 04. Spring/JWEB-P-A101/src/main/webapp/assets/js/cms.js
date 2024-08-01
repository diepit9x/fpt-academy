$(document).ready(function () {
  $(".main-body").load("pages/view-content.jsp");

  $("#logout").click(function () {
    window.location.href = "MemberController?action=logout";
  });
});

jQuery(document).ready(function () {
  jQuery(".load-page").click(function (e) {
    e.preventDefault(); // Ngăn chặn hành vi mặc định của thẻ <a>
    jQuery(".main-body").html("<h2>Loading...<h2>");
    var url = jQuery(this).attr("ref"); // Lấy giá trị của thuộc tính ref
    jQuery.ajax({
      type: "GET",
      url: url,
      success: function (html) {
        setTimeout(function () {
          jQuery(".main-body").html(html);
        }, 300);
      },
      error: function () {
        jQuery(".main-body").html("404 NOT FOUND");
      },
    });
  });
});
