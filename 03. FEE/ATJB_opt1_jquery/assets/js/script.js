$(document).ready(function () {
  $(".content").load("pages/dich-vu/tao-moi.html");

  $(".load-page").click(function (e) {
    e.preventDefault(); // Ngăn chặn hành vi mặc định của thẻ <a>
    var url = $(this).attr("ref"); // Lấy giá trị của thuộc tính ref
    $.ajax({
      type: "GET",
      url: url,
      success: function (html) {
        $(".content").html(html);
      },
      error: function () {
        $(".content").html("<h1>404 NOT FOUND<h1>");
      },
    });
  });
});
