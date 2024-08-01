// Validate Email
function isEmail(email) {
  const regex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
  return regex.test(email);
}
// Validate Phone
function isPhone(phone) {
  var regex = /^(090|091|(\(84\)\+90)|(\(84\)\+91))\d{7}$/;
  return regex.test(phone);
}

function isNotEmpty(text) {
  return text !== "";
}

function isMaKH(maKH) {
  var regex = /^KH\d{5}$/;
  return regex.test(maKH);
}

function isMaDV(maDV) {
  var regex = /^DV\d{3}$/;
  return regex.test(maDV);
}
//Kiểm tra tên: chỉ alphabet đấu cách và số
function isName(name) {
  let regex = /^[a-zA-Z0-9\s]+$/;
  return regex.test(name);
}

//Kiem tra so nguyen
function isInteger(value) {
  const number = Number(value);
  return Number.isInteger(number);
}
