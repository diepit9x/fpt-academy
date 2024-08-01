<%@page import="models.ResponseData"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	//set title
	request.setAttribute("pageTitle", "Tạo dịch vụ mới"); %>
<%
	ResponseData responseData = request.getAttribute("responseData") != null ? ((ResponseData) request.getAttribute("responseData")) : new ResponseData(204, null);
%>
<jsp:include page="../../header.jsp" />

<h1 class="pb-2 border-bottom">Tạo mới dịch vụ</h1>
<form action="<%=request.getContextPath()%>/dich-vu/tao-moi" method="POST" id="tao-moi-dich-vu">
   <%
          	if(responseData.getStatus() == 400){
          %>
          <div class="row">
            <div class="col-md-2"></div>
            <div class="col-md-10">
              <span class="text-danger"><%=responseData.getObject()%></span>
            </div>
          </div>
          <% } %>
          <%
          	if(responseData.getStatus() == 200){
          %>
          <div class="row">
            <div class="col-md-2"></div>
            <div class="col-md-10">
              <span class="text-success"> Tạo mới thành công</span>
            </div>
          </div>
          <% } %>
  <div class="form-group row pt-md-3">
    <label for="maDichVu" class="col-md-2 col-form-label">Mã dịch vụ</label>
    <div class="col-md-10">
      <input type="text" class="form-control" id="maDichVu" name="maDichVu" placeholder="Mã dịch vụ" />
    </div>
  </div>
  <div class="form-group row pt-md-3">
    <label for="tenDichVu" class="col-md-2 col-form-label">Tên dịch vụ</label>
    <div class="col-md-10">
      <input type="text" class="form-control" id="tenDichVu" name="tenDichVu" placeholder="Tên dịch vụ" />
    </div>
  </div>
  <div class="form-group row pt-md-3">
    <label for="donViTinh" class="col-md-2 col-form-label">Đơn vị tính</label>
    <div class="col-md-10">
      <select class="form-control" name="donViTinh" id="donViTinh">
        <option value="1">Chai</option>
        <option value="2">Đĩa</option>
        <option value="3">Cái</option>
        <option value="4">Lon</option>
      </select>
    </div>
  </div>
  <div class="form-group row pt-md-3">
    <label for="donGia" class="col-md-2 col-form-label">Đơn giá</label>
    <div class="col-md-10">
      <input type="number" class="form-control" id="donGia" name="donGia" placeholder="Đơn giá" />
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
