$(document).on("submit", "#tao-moi-dich-vu", function (e) {
  var isValid = true;
  debugger;
  const maDV = $("#maDichVu").val();
  const tenDichVu = $("#tenDichVu").val();
  const donViTinh = $("#donViTinh").val();
  const donGia = $("#donGia").val();
  debugger;
  if (!isMaDV(maDV)) {
    alert("Mã DV: Có format là DVxxx (x: ký tự số)");
    isValid = false;
  }
  if (!isNotEmpty(tenDichVu)) {
    alert("Tên dịch vụ không hợp lệ");
    isValid = false;
  }
  if (!isNotEmpty(donViTinh)) {
    alert("Đơn vị tính không hợp lệ");
    isValid = false;
  }

  if (!isInteger(donGia) || Number(donGia) <= 0) {
    alert("Đơn giá không hợp lệ");
    isValid = false;
  }

  if (!isValid) {
    e.preventDefault();
    return;
  }
});

$(document).on("submit", "#tao-moi-khach-hang", function (e) {
  var isValid = true;
  const maKhachHang = $("#maKhachHang").val();
  const tenKhachHang = $("#tenKhachHang").val();
  const diaChi = $("#diaChi").val();
  const soDienThoai = $("#soDienThoai").val();
  const email = $("#email").val();

  if (!isMaKH(maKhachHang)) {
    alert("Mã KH: Có format là KHxxxxx (x: ký tự số)");
    isValid = false;
  }

  if (!isName(tenKhachHang)) {
    alert("Tên khách hàng không hợp lệ");
    isValid = false;
  }
  if (!isNotEmpty(diaChi)) {
    alert("Địa chỉ không hợp lệ");
    isValid = false;
  }
  if (!isPhone(soDienThoai)) {
    alert("Số điện thoại không hợp lệ");
    isValid = false;
  }
  if (!isEmail(email)) {
    alert("Email không hợp lệ");
    isValid = false;
  }
  if (!isValid) {
    e.preventDefault();
    return;
  }
});
$(document).on("submit", "#tao-may-moi", function (e) {
  var isValid = true;
  const viTri = $("#viTri").val();
  const trangThai = $("#trangThai").val();

  if (!isNotEmpty(viTri)) {
    alert("Vị trí không được để trống");
    isValid = false;
  }

  if (!isNotEmpty(trangThai)) {
    alert("Trạng thái không được để trống");
    isValid = false;
  }

  if (!isValid) {
    e.preventDefault();
    return;
  }
});
$(document).on("submit", "#dang-ki-su-dung-may", function (e) {
  var isValid = true;

  const maKhachHang = $("#maKhachHang").val();
  const maMay = $("#maMay").val();
  const ngayBatDauSuDung = $("#ngayBatDauSuDung").val();
  const gioBatDauSuDung = $("#gioBatDauSuDung").val();

  if (!isMaKH(maKhachHang)) {
    alert("Mã KH: Có format là KHxxxxx (x: ký tự số)");
    isValid = false;
  }
  if (!isNotEmpty(ngayBatDauSuDung)) {
    alert("Ngày bắt đầu sử dụng không hợp lệ");
    isValid = false;
  }
  if (!isNotEmpty(gioBatDauSuDung)) {
    alert("Giờ bắt đầu sử dụng không hợp lệ");
    isValid = false;
  }

  if (!isInteger(maMay) || Number(maMay) <= 0) {
    alert("Mã máy không hợp lệ");
    isValid = false;
  }
  if (!isValid) {
    e.preventDefault();
    return;
  }
});
$(document).on("submit", "#dang-ki-su-dung-dich-vu", function (e) {
  var isValid = true;

  //const
  const maKhachHang = $("#maKhachHang").val();
  const maDichVu = $("#maDichVu").val();
  const ngaySuDung = $("#ngaySuDung").val();
  const gioSuDung = $("#gioSuDung").val();
  const soLuong = $("#soLuong").val();

  if (!isMaKH(maKhachHang)) {
    alert("Mã KH: Có format là KHxxxxx (x: ký tự số)");
    isValid = false;
  }
  if (!isMaDV(maDichVu)) {
    alert("Mã DV: Có format là DVxxx (x: ký tự số)");
    isValid = false;
  }
  if (!isNotEmpty(ngaySuDung)) {
    alert("Ngày sử dụng không hợp lệ");
    isValid = false;
  }
  if (!isNotEmpty(gioSuDung)) {
    alert("Giờ sử dụng không hợp lệ");
    isValid = false;
  }

  if (!isInteger(soLuong) || Number(soLuong) <= 0) {
    alert("Số lượng không hợp lệ");
    isValid = false;
  }
  if (!isValid) {
    e.preventDefault();
    return;
  }
});
