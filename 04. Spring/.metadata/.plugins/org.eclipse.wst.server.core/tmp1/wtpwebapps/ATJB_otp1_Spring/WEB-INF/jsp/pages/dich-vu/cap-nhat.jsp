<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%
//set title
request.setAttribute("pageTitle", "Cập nhật dịch vụ");
%>
<jsp:include page="../../header.jsp" />

<h1 class="pb-2 border-bottom">
	Cập nhật mã dịch vụ <b>${dichVu.maDV }</b>
</h1>
<form:form action="${pageContext.request.contextPath}/dich-vu/cap-nhat"
	method="POST" id="tao-moi-dich-vu" modelAttribute="dichVu">
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
		<div class="alert alert-success">Cập nhật dịch vụ thành công!</div>
	</c:if>
	<form:input type="hidden" path="maDV" id="maDV" name="maDV" value="${dichVu.maDV }" />
	<div class="form-group row pt-md-3">
		<form:label path="tenDV" class="col-md-2 col-form-label">Tên dịch vụ</form:label>
		<div class="col-md-10">
			<form:input type="text" class="form-control" path="tenDV" id="tenDV" name="tenDV" placeholder="Tên dịch vụ"  value="${dichVu.tenDV }"/>
		</div>
	</div>
	<div class="form-group row pt-md-3">
		<form:label path="donViTinh" class="col-md-2 col-form-label">Đơn vị
			tính</form:label>
		<div class="col-md-10">
			<form:select class="form-control" path="donViTinh" name="donViTinh" id="donViTinh">
				<option value="1" <c:if test="${dichVu.donViTinh == '1'}">selected</c:if>>Chai</option>
				<option value="2" <c:if test="${dichVu.donViTinh == '2'}">selected</c:if>>Đĩa</option>
				<option value="3" <c:if test="${dichVu.donViTinh == '3'}">selected</c:if>>Cái</option>
				<option value="4" <c:if test="${dichVu.donViTinh == '4'}">selected</c:if>>Lon</option>
			</form:select>
		</div>
	</div>
	<div class="form-group row pt-md-3">
		<form:label path="donGia" class="col-md-2 col-form-label">Đơn giá</form:label>
		<div class="col-md-10">
			<form:input type="number" class="form-control" path="donGia" id="donGia" name="donGia"
				placeholder="Đơn giá"  value="${dichVu.donGia }" />
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