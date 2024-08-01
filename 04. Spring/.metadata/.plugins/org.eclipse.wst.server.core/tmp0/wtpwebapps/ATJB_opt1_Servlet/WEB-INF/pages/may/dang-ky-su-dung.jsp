<%@page import="entities.May"%>
<%@page import="models.ResponseData"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	//set title
	request.setAttribute("pageTitle", "Đăng ký sử dụng máy");
	ResponseData responseData = request.getAttribute("responseData") != null ? ((ResponseData) request.getAttribute("responseData")) : new ResponseData(204, null);
%>

<jsp:include page="../../header.jsp" />

<h1 class="pb-2 border-bottom">Đăng ký sử dụng máy</h1>
<form action="" method="POST" id="dang-ki-su-dung-may">
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
    <label for="maMay" class="col-md-2 col-form-label">Mã máy</label>
    <div class="col-md-10">
      <input type="number" class="form-control" id="maMay" name="maMay" placeholder="Mã máy" />
    </div>
  </div>
<!--  <div class="form-group row pt-md-3">
    <label for="ngayBatDauSuDung" class="col-md-2 col-form-label">Ngày bắt đầu sử dụng</label>
    <div class="col-md-10">
      <input type="date" class="form-control" id="ngayBatDauSuDung" name="ngayBatDauSuDung" placeholder="Ngày bắt đầu sử dụng" />
    </div>
  </div>
  <div class="form-group row pt-md-3">
    <label for="gioBatDauSuDung" class="col-md-2 col-form-label">Giờ bắt đầu sử dụng</label>
    <div class="col-md-10">
      <input type="time" min="00:00" max="23:59" class="form-control" id="gioBatDauSuDung" name="gioBatDauSuDung" placeholder="hrs:mins" />
    </div>
  </div>
    <div class="form-group row pt-md-3">
    <label for="thoiGianSuDung" class="col-md-2 col-form-label">Thời gian sử dụng (phút)</label>
    <div class="col-md-10">
      <input type="number" class="form-control" id="thoiGianSuDung" name="thoiGianSuDung" placeholder="Thời gian sử dụng (phút)" />
    </div>
  </div>-->  
  <div class="form-group row">
    <div class="col-md-2"></div>
    <div class="col-md-10">
      <button type="submit" class="btn btn-success">Đăng ký</button>
    </div>
  </div>
</form>

<jsp:include page="../../footer.jsp" />
