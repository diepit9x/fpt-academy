$(document).ready(function () {
  function validateInput() {
    let isValid = true;

    // Clear previous errors
    $(".text-danger").text("");

    let nameRegex = /^[a-zA-Z]+$/;
    // Validate First Name
    let firstName = $("#firstName").val();
    if (!nameRegex.test(firstName)) {
      $("#firstNameError").text("Invalid First Name");
      isValid = false;
    }

    // Validate Last Name
    let lastName = $("#lastName").val();
    if (!nameRegex.test(lastName)) {
      $("#lastNameError").text("Invalid Last Name");
      isValid = false;
    }
    // Validate Email
    let email = $("#email").val();
    let emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    if (!emailPattern.test(email) || !email.endsWith("@fsoft.com.vn")) {
      $("#emailError").text("Invalid Email");
      isValid = false;
    }

    // Validate Phone
    let phone = $("#phone").val();
    let phoneRegex = /^\d{10}$/;
    if (!phoneRegex.test(phone)) {
      $("#phoneError").text("Invalid Phone");
      isValid = false;
    }

    // Validate Address
    let address = $("#address").val();
    let addressRegex = /^[a-zA-Z0-9]+$/;
    if (!addressRegex.test(address)) {
      $("#addressError").text("Invalid Address");
      isValid = false;
    }

    // Validate City
    let city = $("#city").val();
    let cityRegex = /^[a-zA-Z]+$/;
    if (!cityRegex.test(city)) {
      $("#cityError").text("Invalid City");
      isValid = false;
    }

    // Validate Zip Code
    let zip = $("#zip").val();
    let zipRegex = /^\d{5}$/;
    if (!zipRegex.test(zip)) {
      $("#zipError").text("Invalid Zip Code");
      isValid = false;
    }

    // Validate Name on Card
    let cardName = $("#cardName").val();
    let cardNameRegex = /^[a-zA-Z]+$/;
    if (!cardNameRegex.test(cardName)) {
      $("#cardNameError").text("Invalid Name on Card");
      isValid = false;
    }

    // Validate Credit Card Number
    let cardNumber = $("#cardNumber").val();
    let cardNumberPattern = /^\d{4}-\d{4}-\d{4}-\d{4}$/;
    if (!cardNumberPattern.test(cardNumber)) {
      $("#cardNumberError").text("Invalid Credit Card Number");
      isValid = false;
    }

    // Validate Exp Month
    let expMonth = $("#expMonth").val();
    let expMonthRegex = /^(0[1-9]|1[0-2])$/;
    if (!expMonthRegex.test(expMonth)) {
      $("#expMonthError").text("Invalid Exp Month");
      isValid = false;
    }

    // Validate Exp Year
    let expYear = $("#expYear").val();
    let expYearRegex = /^\d{4}$/;
    if (!expYearRegex.test(expYear) || parseInt(expYear) <= 2000) {
      $("#expYearError").text("Invalid Exp Year");
      isValid = false;
    }

    // Validate CVV
    let cvv = $("#cvv").val();
    let cvvRegex = /^\d{3}$/;
    if (!cvvRegex.test(cvv)) {
      $("#cvvError").text("Invalid CVV");
      isValid = false;
    }

    // Validate State
    let state = $("#state").val();
    if (state === "Choose...") {
      $("#stateError").text("Please select a State");
      isValid = false;
    }

    // Validate Check me out
    let checkMeOut = $("#checkMeOut").is(":checked");
    if (!checkMeOut) {
      $("#checkMeOutError").text("Please check this box");
      isValid = false;
    }

    return isValid;
  }

  $("#signInBtn").click(function () {
    if (validateInput()) {
      let row = `<tr>
                <td>${$("#firstName").val()}</td>
                <td>${$("#lastName").val()}</td>
                <td>${$("#email").val()}</td>
                <td>${$("#phone").val()}</td>
                <td>${$("#address").val()}</td>
                <td>${$("#city").val()}</td>
                <td>${$("#state").val()}</td>
                <td>${$("#zip").val()}</td>
                <td>${$("#cardName").val()}</td>
                <td>${$("#cardNumber").val()}</td>
                <td>${$("#expMonth").val()}</td>
                <td>${$("#expYear").val()}</td>
                <td>${$("#cvv").val()}</td>
                <td><a href="#" class="delete">Delete</a></td>
            </tr>`;

      $("#dataTable").append(row);
      $("form")[0].reset();
    }
  });

  $(document).on("click", ".delete", function (e) {
    e.preventDefault();
    let row = $(this).closest("tr");
    $("#deleteModal").modal("show");

    $("#confirmDelete")
      .off("click")
      .on("click", function () {
        row.remove();
        $("#deleteModal").modal("hide");
      });
  });

  // Hàm định dạng số thẻ tín dụng
  function formatCardNumber(value) {
    return value
      .replace(/\W/gi, "")
      .replace(/(.{4})/g, "$1-")
      .replace(/-$/, "");
  }

  // Sự kiện input cho trường thẻ tín dụng
  $("#cardNumber").on("input", function () {
    let cardNumber = $(this).val();
    $(this).val(formatCardNumber(cardNumber));
  });
});
