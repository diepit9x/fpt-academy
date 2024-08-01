<%@page import="models.ResponseData"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	//set title
	request.setAttribute("pageTitle", "Tạo máy mới"); %>
<%
	ResponseData responseData = request.getAttribute("responseData") != null ? ((ResponseData) request.getAttribute("responseData")) : new ResponseData(204, null);
%>

<jsp:include page="../../header.jsp" />

        <h1 class="pb-2 border-bottom">Tạo mới máy</h1>
        <form action="<%=request.getContextPath()%>/may/tao-moi" method="POST" id="tao-may-moi">
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
            <label for="viTri" class="col-md-2 col-form-label">Vị trí</label>
            <div class="col-md-10">
              <input type="text" class="form-control" id="viTri" name="viTri" placeholder="Vị trí" />
            </div>
          </div>
          <div class="form-group row pt-md-3">
            <label for="trangThai" class="col-md-2 col-form-label">Trạng thái</label>
            <div class="col-md-10">
              <select class="form-control" name="trangThai" id="trangThai">
                <option value="1">Rảnh</option>
                <option value="2">Bận</option>
                <option value="3">Đang sửa chữa</option>
              </select>
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