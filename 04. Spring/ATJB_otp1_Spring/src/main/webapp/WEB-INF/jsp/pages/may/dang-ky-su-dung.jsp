<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%
//set title
request.setAttribute("pageTitle", "Đăng ký sử dụng máy");
%>
<jsp:include page="../../header.jsp" />
<h1 class="pb-2 border-bottom">Đăng ký sử dụng máy</h1>
<form action="${pageContext.request.contextPath}/may/dang-ky-su-dung"
	method="POST" id="dang-ki-su-dung-may">
	<c:if test="${not empty errors}">
		<div class="alert alert-danger">
			<ul>
				<c:forEach var="error" items="${errors}">
					<li>${error}</li>
				</c:forEach>
			</ul>
		</div>
	</c:if>
	<c:if test="${not empty success}">
		<div class="alert alert-success">Đăng ký sử dụng máy thành công!
		</div>
	</c:if>
	<div class="form-group row pt-md-3">
		<label for="maKhachHang" class="col-md-2 col-form-label">Mã KH</label>
		<div class="col-md-10">
			<input type="text" class="form-control" id="maKhachHang"
				name="maKhachHang" placeholder="Mã khách hàng" />
		</div>
	</div>
	<div class="form-group row pt-md-3">
		<label for="maMay" class="col-md-2 col-form-label">Mã máy</label>
		<div class="col-md-10">
			<input type="number" class="form-control" id="maMay" name="maMay"
				placeholder="Mã máy" />
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
