<%@page import="entities.KhachHang"%>
<%@page import="entities.DichVu"%>
<%@page import="entities.May"%>
<%@page import="models.ResponseData"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
//set title
request.setAttribute("pageTitle", "Cập nhật khách hàng");
%>
<%
ResponseData responseData = request.getAttribute("responseData") != null
		? ((ResponseData) request.getAttribute("responseData"))
		: new ResponseData(204, null);
%>

<jsp:include page="../../header.jsp" />
<%
if (responseData.getStatus() == 201) {
	KhachHang khachHang = (KhachHang) responseData.getObject();
%>
<h1 class="pb-2 border-bottom">
	Cập nhật mã khách hàng <b><%=khachHang.getMaKH()%></b>
</h1>
<form action="<%=request.getContextPath()%>/khach-hang/cap-nhat"
	method="POST" id="tao-moi-khach-hang">
	<input type="hidden" id="maKhachHang" name="maKhachHang" value="<%=khachHang.getMaKH()%>" />
	<div class="form-group row pt-md-3">
    <label for="tenKhachHang" class="col-md-2 col-form-label">Tên khách hàng</label>
    <div class="col-md-10">
      <input type="text" class="form-control" id="tenKhachHang" name="tenKhachHang" placeholder="Tên khách hàng"  value="<%=khachHang.getTenKH()%>" />
    </div>
  </div>
  <div class="form-group row pt-md-3">
    <label for="diaChi" class="col-md-2 col-form-label">Địa chỉ</label>
    <div class="col-md-10">
      <input type="text" class="form-control" id="diaChi" name="diaChi" placeholder="Địa chỉ"  value="<%=khachHang.getDiaChi()%>"  />
    </div>
  </div>
  <div class="form-group row pt-md-3">
    <label for="soDienThoai" class="col-md-2 col-form-label">Số điện thoại</label>
    <div class="col-md-10">
      <input type="text" class="form-control" id="soDienThoai" name="soDienThoai" placeholder="Số điện thoại" value="<%=khachHang.getSoDienThoai()%>"  />
    </div>
  </div>
  <div class="form-group row pt-md-3">
    <label for="email" class="col-md-2 col-form-label">Email</label>
    <div class="col-md-10">
      <input type="email" class="form-control" id="email" name="email" placeholder="Email" value="<%=khachHang.getEmail()%>"  />
    </div>
  </div>
	<div class="form-group row">
		<div class="col-md-2"></div>
		<div class="col-md-10">
			<button type="submit" class="btn btn-outline-dark">Cập nhật</button>
		</div>
	</div>
</form>
<%
} else if (responseData.getStatus() == 200) {
%>
<h3 class="pb-2 text-success">Cập nhật thành công</h3>
<%
} else {
%>
<h3 class="pb-2 text-danger"><%=responseData.getObject() == null ? "Cập nhật thất bại" : responseData.getObject()%></h3>
<%
}
%>
<jsp:include page="../../footer.jsp" />