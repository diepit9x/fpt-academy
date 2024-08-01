<%@page import="entities.May"%>
<%@page import="models.ResponseData"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	//set title
	request.setAttribute("pageTitle", "Đăng ký sử dụng máy");
	ResponseData responseData = request.getAttribute("responseData") != null ? ((ResponseData) request.getAttribute("responseData")) : new ResponseData(204, null);
%>
<jsp:include page="../../header.jsp" />
<h1 class="pb-2 border-bottom">Đăng kí sử dụng dịch vụ</h1>
<form action="" method="POST" id="dang-ki-su-dung-dich-vu">
<%
          	if(responseData.getStatus() == 400){
          %>
          <div class="row">
            <div class="col-md-2"></div>
            <div class="col-md-10">
              <span class="text-danger"><%=responseData.getObject()%></span>
            </div>
          </div>
          <% }
          	if(responseData.getStatus() == 200){
          %>
          <div class="row">
            <div class="col-md-2"></div>
            <div class="col-md-10">
              <span class="text-success"> Đăng ký thành công</span>
            </div>
          </div>
          <% } %>
  <div class="form-group row pt-md-3">
    <label for="maKhachHang" class="col-md-2 col-form-label">Mã KH</label>
    <div class="col-md-10">
      <input type="text" class="form-control" id="maKhachHang" name="maKhachHang" placeholder="Mã khách hàng" />
    </div>
  </div>
  <div class="form-group row pt-md-3">
    <label for="maDichVu" class="col-md-2 col-form-label">Mã DV</label>
    <div class="col-md-10">
      <input type="text" class="form-control" id="maDichVu" name="maDichVu" placeholder="Mã dịch vụ" />
    </div>
  </div>
  <!-- 
  <div class="form-group row pt-md-3">
    <label for="ngaySuDung" class="col-md-2 col-form-label">Ngày sử dụng</label>
    <div class="col-md-10">
      <input type="date" class="form-control" id="ngaySuDung" name="ngaySuDung" placeholder="Ngày sử dụng" />
    </div>
  </div>
  <div class="form-group row pt-md-3">
    <label for="gioSuDung" class="col-md-2 col-form-label">Giờ sử dụng</label>
    <div class="col-md-10">
      <input type="time" class="form-control" id="gioSuDung" name="gioSuDung" placeholder="hrs:mins" />
    </div>
  </div> -->
  <div class="form-group row pt-md-3">
    <label for="soLuong" class="col-md-2 col-form-label">Số lượng</label>
    <div class="col-md-10">
      <input type="number" class="form-control" id="soLuong" name="soLuong" placeholder="Số lượng" />
    </div>
  </div>
  <div class="form-group row">
    <div class="col-md-2"></div>
    <div class="col-md-10">
      <button type="submit" class="btn btn-success">Đăng ký</button>
    </div>
  </div>
</form>
<jsp:include page="../../footer.jsp" />
