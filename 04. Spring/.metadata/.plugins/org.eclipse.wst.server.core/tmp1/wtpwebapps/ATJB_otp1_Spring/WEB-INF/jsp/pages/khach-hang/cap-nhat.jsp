<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%
//set title
request.setAttribute("pageTitle", "Cập nhật khách hàng");
%>
<jsp:include page="../../header.jsp" />
<h1 class="pb-2 border-bottom">
	Cập nhật mã máy <b>${khachHang.maKH}</b>
</h1>
<form:form
	action="${pageContext.request.contextPath}/khach-hang/cap-nhat"
	method="POST" id="tao-moi-khach-hang" modelAttribute="khachHang">
	<c:if test="${not empty errors}">
		<div class="alert alert-danger">
			<ul>
				<c:forEach var="error" items="${errors}">
					<li>${error}</li>
				</c:forEach>
			</ul>
		</div>
	</c:if>
	<c:if test="${success}">
		<div class="alert alert-success">Cập nhật thành công!</div>
	</c:if>
	<form:input type="hidden" path="maKH" id="maKH" name="maKH"
		value="${khachHang.maKH }" />
	<div class="form-group row pt-md-3">
		<form:label path="tenKH" class="col-md-2 col-form-label">Tên khách hàng</form:label>
		<div class="col-md-10">
			<form:input type="text" class="form-control" path="tenKH" id="tenKH"
				name="tenKH" placeholder="Tên khách hàng" value="${khachHang.tenKH}" />
		</div>
	</div>
	<div class="form-group row pt-md-3">
		<form:label path="diaChi" class="col-md-2 col-form-label">Địa chỉ</form:label>
		<div class="col-md-10">
			<form:input type="text" class="form-control" path="diaChi"
				id="diaChi" name="diaChi" placeholder="Địa chỉ"
				value="${khachHang.diaChi}" />
		</div>
	</div>
	<div class="form-group row pt-md-3">
		<form:label path="soDienThoai" class="col-md-2 col-form-label">Số điện thoại</form:label>
		<div class="col-md-10">
			<form:input type="text" class="form-control" path="soDienThoai"
				id="soDienThoai" name="soDienThoai" placeholder="Số điện thoại"
				value="${khachHang.soDienThoai}" />
		</div>
	</div>
	<div class="form-group row pt-md-3">
		<form:label path="email" class="col-md-2 col-form-label">Email</form:label>
		<div class="col-md-10">
			<form:input type="email" class="form-control" path="email" id="email"
				name="email" placeholder="Email" value="${khachHang.email}" />
		</div>
	</div>
	<div class="form-group row">
		<div class="col-md-2"></div>
		<div class="col-md-10">
			<form:button type="submit" class="btn btn-outline-dark">Cập nhật</form:button>
		</div>
	</div>
</form:form>

<jsp:include page="../../footer.jsp" />
