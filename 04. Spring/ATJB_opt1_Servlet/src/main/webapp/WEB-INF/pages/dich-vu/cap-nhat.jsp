<%@page import="entities.DichVu"%>
<%@page import="entities.DichVu"%>
<%@page import="entities.May"%>
<%@page import="models.ResponseData"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
//set title
request.setAttribute("pageTitle", "Cập nhật dịch vụ");
%>
<%
ResponseData responseData = request.getAttribute("responseData") != null
		? ((ResponseData) request.getAttribute("responseData"))
		: new ResponseData(204, null);
%>

<jsp:include page="../../header.jsp" />
<%
if (responseData.getStatus() == 201) {
	DichVu dichVu = (DichVu) responseData.getObject();
%>
<h1 class="pb-2 border-bottom">
	Cập nhật mã dịch vụ <b><%=dichVu.getMaDV()%></b>
</h1>
<form action="<%=request.getContextPath()%>/dich-vu/cap-nhat"
	method="POST" id="tao-moi-dich-vu">

	<input type="hidden" id="maDichVu" name="maDichVu" value="<%=dichVu.getMaDV()%>" />
	<div class="form-group row pt-md-3">
		<label for="tenDichVu" class="col-md-2 col-form-label">Tên dịch vụ</label>
		<div class="col-md-10">
			<input type="text" class="form-control" id="tenDichVu" name="tenDichVu" placeholder="Tên dịch vụ"  value="<%=dichVu.getTenDV() %>"/>
		</div>
	</div>
	<div class="form-group row pt-md-3">
		<label for="donViTinh" class="col-md-2 col-form-label">Đơn vị
			tính</label>
		<div class="col-md-10">
			<select class="form-control" name="donViTinh" id="donViTinh">
				<option value="1" <%=dichVu.getDonViTinh().equals("1") ? "selected":""%>>Chai</option>
				<option value="2" <%=dichVu.getDonViTinh().equals("2") ? "selected":""%>>Đĩa</option>
				<option value="3" <%=dichVu.getDonViTinh().equals("3") ? "selected":""%>>Cái</option>
				<option value="4" <%=dichVu.getDonViTinh().equals("4") ? "selected":""%>>Lon</option>
			</select>
		</div>
	</div>
	<div class="form-group row pt-md-3">
		<label for="donGia" class="col-md-2 col-form-label">Đơn giá</label>
		<div class="col-md-10">
			<input type="number" class="form-control" id="donGia" name="donGia"
				placeholder="Đơn giá"  value="<%=dichVu.getDonGia()%>" />
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