var data = [
  {
    id: 1,
    name: "Superior Ocean Twin",
    maxPeople: 3,
    tienPhong: 1500000,
  },
  {
    id: 2,
    name: "Superior Ocean Double",
    maxPeople: 3,
    tienPhong: 1700000,
  },
  {
    id: 3,
    name: "Deluxe Ocean Twin",
    maxPeople: 3,
    tienPhong: 2500000,
  },
];
var countId = 0;

$(".plus-nguoilon").click(function () {
  let row = $(this).closest("div");
  let quantity = parseInt(row.find("#nguoiLon").val());
  quantity += 1;
  if (!checkPeople()) {
    return;
  }
  row.find("#nguoiLon").val(quantity);
  updatePeople();
});

$(".minutes-nguoilon").click(function () {
  let row = $(this).closest("div");
  let quantity = parseInt(row.find("#nguoiLon").val());
  if (quantity > 1) {
    quantity -= 1;
    row.find("#nguoiLon").val(quantity);
    if (!checkPeople()) {
      return;
    }
  } else {
    alert("Số lượng người lớn phải lớn hơn 0");
  }
  updatePeople();
});

$(".plus-treem").click(function () {
  let row = $(this).closest("div");
  let quantity = parseInt(row.find("#treEm").val());
  quantity += 1;
  row.find("#treEm").val(quantity);
  if (checkPeople()) {
    themDoTuoi(quantity);
  } else {
    themDoTuoi(0);
    return;
  }
  updatePeople();
});

$(".minutes-treem").click(function () {
  let row = $(this).closest("div");
  let quantity = parseInt(row.find("#treEm").val());
  if (quantity > 0) {
    quantity -= 1;
    row.find("#treEm").val(quantity);
  } else {
    alert("Số lượng người lớn phải lớn hơn hoặc bằng 0");
  }
  updatePeople();
  if (checkPeople()) {
    themDoTuoi(quantity);
  } else {
    themDoTuoi(0);
  }
});

function checkPeople() {
  var loaiPhong = $("#loaiPhong").val();
  var nguoiLon = $("#nguoiLon").val();
  var treEm = $("#treEm").val();
  var getData = data.filter((d) => d.id == loaiPhong);
  var error = $(".text-danger").empty();
  debugger;
  if (getData.length == 0) {
    error.text("Loại phòng không hợp lệ");
    return false;
  }
  if (Number(treEm) > getData[0].maxPeople && nguoiLon == 0) {
    error.text("Phải có ít nhất 01 người lớn trong phòng");
    return false;
  }
  if (Number(nguoiLon) + Number(treEm) >= getData[0].maxPeople) {
    error.text("Số người vượt quá giới hạn của phòng. Chỉ tối đa " + getData[0].maxPeople + " người / phòng");
    return false;
  }

  return true;
}

$(document).on("change", "#loaiPhong", function () {
  var loaiPhong = $(this).val();
  var getData = data.filter((d) => d.id == loaiPhong);
  if (getData) {
    $("td.loaiPhong").text(getData[0].name);
    $("td.tienPhong").text(numberFormat(getData[0].tienPhong) + " VND");
  } else {
    $("td.loaiPhong").empty();
    $("td.tienPhong").empty();
  }
});

function updatePeople() {
  var nguoiLon = $("#nguoiLon").val();
  var treEm = $("#treEm").val();

  if (nguoiLon > 0) {
    $("td.nguoiLon").text(nguoiLon);
  } else {
    $("td.nguoiLon").text("");
  }
  if (treEm > 0) {
    $("td.treEm").text(treEm);
  } else {
    $("td.treEm").text("");
  }
}

$(".button-datcho").click(function () {
  var loaiPhong = $("#loaiPhong").val();
  var nguoiLon = $("#nguoiLon").val();
  var treEm = $("#treEm").val();

  var getData = data.filter((d) => d.id == loaiPhong);
  debugger;
  if (getData.length == 0) {
    alert("Vui lòng chọn loại phòng");
    return;
  }

  if (!isInteger(nguoiLon)) {
    alert("Số người lớn phải là số");
    return;
  }
  if (!isInteger(treEm)) {
    alert("Số trẻ em phải là số");
    return;
  }
  if (!checkPeople()) {
    return;
  }
});

function themDoTuoi(number) {
  $(".doTuoiTreEm").empty();
  for (let index = 1; index <= number; index++) {
    html = $(`
        <div class="col-4">
          <div class="form-group">
            <select name="doTuoiTreEm[]" class="form-control">
              <option value="">-Độ tuổi trẻ em ${index}-</option>
              <option value="1">Dưới 10</option>
              <option value="2">Trên 10</option>
            </select>
          </div>
        </div>`);
    $(".doTuoiTreEm").append(html);
  }
}
