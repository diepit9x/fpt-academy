// Validate Email
function isEmail(email) {
  const regex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
  if (!regex.test(email)) {
    return false;
  }
  //   if (!email.endsWith("@fsoft.com.vn")) {
  //     return false;
  //   }
  return true;
}

// Validate Phone
function isPhone(phone) {
  var regex = /^\d{10}$/;
  if (!regex.test(phone)) {
    return false;
  }
  return true;
}

// Validate Year
function isYear(year) {
  var regex = /^\d{4}$/;
  if (!regex.test(year)) {
    return false;
  }
  return true;
}

// Validate Month
function isMonth(month) {
  var regex = /^(0[1-9]|1[0-2])$/;
  if (!regex.test(month)) {
    return false;
  }
  return true;
}

//Kiểm tra tên: chỉ alphabet đấu cách và số
function isName(name) {
  let regex = /^[a-zA-Z0-9\s]+$/;
  return regex.test(name);
}

//Ngày nhỏ hơn này hiện tại
function isDate(date) {
  const today = new Date();
  const inputDate = new Date(date);

  // Đặt thời gian của cả hai ngày là 00:00:00 để so sánh chỉ phần ngày
  today.setHours(0, 0, 0, 0);
  inputDate.setHours(0, 0, 0, 0);

  return inputDate < today;
}

//Kiem tra so nguyen
function isInteger(value) {
  const number = Number(value);
  return Number.isInteger(number);
}

//kiem tra so thuc
function isRealNumber(value) {
  const number = Number(value);
  return !isNaN(number) && isFinite(number);
}

//Kiểm tra điểm có 1 hoặc 2 số thập phân. tư 1-10
function isPoint(value) {
  const numberValue = parseFloat(value);

  // Kiểm tra nếu giá trị không phải là số hoặc bị bỏ trống
  if (isNaN(numberValue) || value === "") {
    return false;
  }

  // Kiểm tra nếu giá trị nằm ngoài khoảng từ 0 đến 10
  if (numberValue < 0 || numberValue > 10) {
    return false;
  }

  // Kiểm tra số lượng chữ số thập phân
  if (value.includes(".")) {
    const decimalPart = value.split(".")[1];
    if (decimalPart.length > 2) {
      return false;
    }
  }
  return true;
}

//100000 -> 100,000
function numberFormat(number = 0) {
  return new Intl.NumberFormat().format(number);
}

//100,000 -> 100000
function parseCurrency(formattedNumber) {
  var numberWithoutCommas = formattedNumber.replace(/,/g, "");
  return parseInt(numberWithoutCommas);
}
function showGender(gender = 0) {
  return gender == 0 ? "Nữ" : gender == 1 ? "Nam" : "Khác";
}

//alert xac nhan
function showConfirm(msg = "") {
  msg = msg != "" ? msg : "Bạn có chắc chắn muốn tiếp tục?";
  var result = confirm(msg);
  if (result) {
    return true;
  } else {
    return false;
  }
}

//alert input
function showPrompt() {
  var input = prompt("Vui lòng nhập tên của bạn:");
  if (input !== null && input.trim() != "") {
    return input;
  } else {
    return null;
  }
}
