<%@page import="entities.May"%>
<%@page import="models.ResponseData"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	//set title
	request.setAttribute("pageTitle", "Cập nhật máy"); %>
<%
	ResponseData responseData = request.getAttribute("responseData") != null ? ((ResponseData) request.getAttribute("responseData")) : new ResponseData(204, null);
%>

<jsp:include page="../../header.jsp" />
  <%
  if(responseData.getStatus() == 201 ){
	  May may = (May) responseData.getObject();
%>
        <h1 class="pb-2 border-bottom">Cập nhật mã máy <b><%=may.getMaMay()%></b></h1>
        <form action="<%=request.getContextPath()%>/may/cap-nhat" method="POST" id="tao-may-moi">
          <input type="hidden" name="maMay" value="<%=may.getMaMay()%>" />
          <div class="form-group row pt-md-3">
            <label for="viTri" class="col-md-2 col-form-label">Vị trí</label>
            <div class="col-md-10">
              <input type="text" class="form-control" id="viTri" name="viTri" placeholder="Vị trí" value="<%=may.getViTri()%>" />
            </div>
          </div>
          <div class="form-group row pt-md-3">
            <label for="trangThai" class="col-md-2 col-form-label">Trạng thái</label>
            <div class="col-md-10">
              <select class="form-control" name="trangThai" id="trangThai">
                <option value="1" <%=may.getTrangThai().equals("1") ? "selected":""%>>Rảnh</option>
                <option value="2" <%=may.getTrangThai().equals("2") ? "selected":""%>>Bận</option>
                <option value="3" <%=may.getTrangThai().equals("3") ? "selected":""%>>Đang sửa chữa</option>
              </select>
            </div>
          </div>
          <div class="form-group row">
            <div class="col-md-2"></div>
            <div class="col-md-10">
              <button type="submit" class="btn btn-outline-dark">Cập nhật</button>
            </div>
          </div>
        </form>
<% } else if (responseData.getStatus() == 200) { %>
<h3 class="pb-2 text-success">Cập nhật thành công</h3>
<% } else { %>
<h3 class="pb-2 text-danger"><%=responseData.getObject() == null ? "Cập nhật thất bại": responseData.getObject() %></h3>
<% } %>
<jsp:include page="../../footer.jsp" />
