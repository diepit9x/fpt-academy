<%@page import="fa.training.models.ResponseData"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%
//set title
request.setAttribute("pageTitle", "Tạo máy mới");
%>
<jsp:include page="../../header.jsp" />
<h1 class="pb-2 border-bottom">Tạo mới máy</h1>
<form:form action="${pageContext.request.contextPath}/may/tao-moi"
	method="POST" modelAttribute="may" id="tao-may-moi">
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
		<div class="alert alert-success">Tạo mới máy thành công!</div>
	</c:if>
	<div class="form-group row pt-md-3">
		<form:label for="viTri" path="viTri" class="col-md-2 col-form-label">Vị trí</form:label>
		<div class="col-md-10">
			<form:input type="text" class="form-control" path="viTri" id="viTri"
				name="viTri" placeholder="Vị trí" />
		</div>
	</div>
	<div class="form-group row pt-md-3">
		<form:label for="trangThai" path="trangThai"
			class="col-md-2 col-form-label">Trạng thái</form:label>
		<div class="col-md-10">
			<form:select class="form-control" path="trangThai" name="trangThai"
				id="trangThai">
				<option value="1">Rảnh</option>
				<option value="2">Bận</option>
				<option value="3">Đang sửa chữa</option>
			</form:select>
		</div>
	</div>
	<div class="form-group row">
		<div class="col-md-2"></div>
		<div class="col-md-10">
			<form:button type="submit" class="btn btn-outline-dark">Tạo</form:button>
		</div>
	</div>
</form:form>
<jsp:include page="../../footer.jsp" />
