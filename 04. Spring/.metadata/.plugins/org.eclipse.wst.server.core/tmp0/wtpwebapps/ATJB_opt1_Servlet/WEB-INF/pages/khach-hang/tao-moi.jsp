<%@page import="entities.May"%>
<%@page import="models.ResponseData"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	//set title
	request.setAttribute("pageTitle", "Tạo mới khách hàng"); %>
<%
	ResponseData responseData = request.getAttribute("responseData") != null ? ((ResponseData) request.getAttribute("responseData")) : new ResponseData(204, null);
%>

<jsp:include page="../../header.jsp" />

<h1 class="pb-2 border-bottom">Tạo mới khách hàng</h1>
<form action="" method="POST" id="tao-moi-khach-hang">

          <%
          	if(responseData.getStatus() == 200){
          %>
          <div class="row">
            <div class="col-md-2"></div>
            <div class="col-md-10">
              <span class="text-success"> Tạo mới thành công</span>
            </div>
          </div>
          <% }
          if(responseData.getStatus() == 400){
          %>
          <div class="row">
            <div class="col-md-2"></div>
            <div class="col-md-10">
              <span class="text-danger"><%=responseData.getObject() != null ? responseData.getObject():"Tạo khách hàng thất bại"%></span>
            </div>
          </div>
          <% } %>
  <div class="form-group row pt-md-3">
    <label for="maKhachHang" class="col-md-2 col-form-label">Mã khách hàng</label>
    <div class="col-md-10">
      <input type="text" class="form-control" id="maKhachHang" name="maKhachHang" placeholder="Mã khách hàng" />
    </div>
  </div>
  <div class="form-group row pt-md-3">
    <label for="tenKhachHang" class="col-md-2 col-form-label">Tên khách hàng</label>
    <div class="col-md-10">
      <input type="text" class="form-control" id="tenKhachHang" name="tenKhachHang" placeholder="Tên khách hàng" />
    </div>
  </div>
  <div class="form-group row pt-md-3">
    <label for="diaChi" class="col-md-2 col-form-label">Địa chỉ</label>
    <div class="col-md-10">
      <input type="text" class="form-control" id="diaChi" name="diaChi" placeholder="Địa chỉ" />
    </div>
  </div>
  <div class="form-group row pt-md-3">
    <label for="soDienThoai" class="col-md-2 col-form-label">Số điện thoại</label>
    <div class="col-md-10">
      <input type="text" class="form-control" id="soDienThoai" name="soDienThoai" placeholder="Số điện thoại" />
    </div>
  </div>
  <div class="form-group row pt-md-3">
    <label for="email" class="col-md-2 col-form-label">Email</label>
    <div class="col-md-10">
      <input type="email" class="form-control" id="email" name="email" placeholder="Email" />
    </div>
  </div>
  <div class="form-group row">
    <div class="col-md-2"></div>
    <div class="col-md-10">
      <button type="submit" class="btn btn-outline-dark">Tạo</button>
    </div>
  </div>
</form>

<jsp:include page="../../footer.jsp" />