<%@page import="fa.training.models.SelectData"%>
<%@page import="java.util.List"%>
<%@ page import="fa.training.models.ResponseData" %>
<%@ page import="fa.training.models.PagedResult" %>
<%@ page import="fa.training.entities.May" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	//set title
	request.setAttribute("pageTitle", "Tạo máy mới");

	ResponseData responseData = request.getAttribute("responseData") != null ? ((ResponseData) request.getAttribute("responseData")) : new ResponseData(204, null);
    PagedResult<May> pagedResult = (PagedResult<May>) responseData.getObject();
    List<May> mays = pagedResult.getResults();
    int currentPage = pagedResult.getCurrentPage();
    int totalPages = pagedResult.getTotalPages();
    String keyword = (String) request.getAttribute("keyword");
%>

<jsp:include page="../../header.jsp" />

<h1 class="pb-2 border-bottom">Danh sách máy</h1>
<div class="flex-row">
  <form action="<%=request.getContextPath()%>/may/danh-sach" method="GET">
    <div class="input-group mb-2 mt-4">
      <input type="text" class="form-control" name="keyword" placeholder="Tìm kiếm..." value="<%=keyword != null ? keyword :"" %>" />
      <div class="input-group-append">
        <button class="btn btn-outline-info" id="searchBtn" type="submit"><i class="fa fa-search"></i></button>
      </div>
    </div>
  </form>
</div>
<div class="flex-row">
  <div class="table-responsive">
    <table class="table table-bordered">
      <thead>
        <tr>
          <th>Mã máy</th>
          <th>Vị trí</th>
          <th>Trạng thái</th>
          <th>Thao tác</th>
        </tr>
      </thead>
      <tbody id="table-body">
        <% if (mays != null) {
            for (May may : mays) { %>
              <tr>
                <td><%= may.getMaMay() %></td>
                <td><%= may.getViTri() %></td>
                <td><%= SelectData.trangThai(may.getTrangThai()) %></td>
                <td>
                  <a href="<%=request.getContextPath()%>/may/cap-nhat?maMay=<%=may.getMaMay() %>" class="btn btn-warning edit-may"><i class="fa fa-pencil-square-o"></i></a>
                  <button class="btn btn-danger delete-may"><i class="fa fa-trash-o"></i></button>
                </td>
              </tr>
        <% } } %>
      </tbody>
    </table>
<nav aria-label="Page navigation example">
    <ul class="pagination justify-content-center">
        <li class="page-item <%= currentPage == 1 ? "d-none" : "" %>">
            <a class="page-link" href="?<%=keyword != null ? "keyword="+keyword+"&" :"" %>page=<%= currentPage - 1 %>">Previous</a>
        </li>
        <% for (int i = 1; i <= totalPages; i++) { %>
            <li class="page-item <%= currentPage == i ? "active" : "" %>">
                <a class="page-link" href="?<%=keyword != null ? "keyword="+keyword+"&" :"" %>page=<%= i %>"><%= i %></a>
            </li>
        <% } %>
        <li class="page-item <%= currentPage == totalPages ? "d-none" : "" %>">
            <a class="page-link" href="?<%=keyword != null ? "keyword="+keyword+"&" :"" %>page=<%= currentPage + 1 %>">Next</a>
        </li>
    </ul>
</nav>
  </div>
</div>
<script>
$("#table-body").on("click", ".delete-may", function () {
	var index = $(this).closest("tr").find("td:first").text().trim();
    var confirmDelete = confirm("Bạn có chắc chắn muốn xóa không ?");
    if (confirmDelete) {
		window.location.href = "<%=request.getContextPath()%>/may/xoa-may?maMay="+index;
    }
  });
</script>
<jsp:include page="../../footer.jsp" />