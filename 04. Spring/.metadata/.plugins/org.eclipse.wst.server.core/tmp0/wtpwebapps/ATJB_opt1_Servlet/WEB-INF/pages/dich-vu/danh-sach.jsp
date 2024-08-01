<%@page import="entities.DichVu"%>
<%@page import="models.PagedResult"%>
<%@page import="java.util.List"%>
<%@page import="models.ResponseData"%>
<%@page import="models.SelectData"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	//set title
	request.setAttribute("pageTitle", "Danh sách dịch vụ");
	String keyword = (String) request.getAttribute("keyword");

    ResponseData responseData = request.getAttribute("responseData") != null ? ((ResponseData) request.getAttribute("responseData")) : new ResponseData(204, null);
    PagedResult<DichVu> pagedResult = (PagedResult<DichVu>) responseData.getObject();
    
    List<DichVu> dichVus = pagedResult.getResults();
    int currentPage =pagedResult.getCurrentPage();
    int totalPages = pagedResult.getTotalPages();
%>

<jsp:include page="../../header.jsp" />

<h1 class="pb-2 border-bottom">Danh sách dịch vụ</h1>
<div class="flex-row">
  <form action="<%=request.getContextPath()%>/dich-vu/danh-sach" method="POST">
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
          <th>Mã DV</th>
          <th>Tên DV</th>
          <th>Đơn vị</th>
          <th>Đơn giá</th>
          <th>Thao tác</th>
        </tr>
      </thead>
      <tbody id="table-body">
        <% if (dichVus != null) {
            for (DichVu dichVu : dichVus) { %>
              <tr>
                <td><%= dichVu.getMaDV()%></td>
                <td><%= dichVu.getTenDV()%></td>
                <td><%= SelectData.donVi(dichVu.getDonViTinh())%></td>
                <td><%= dichVu.getDonGia()%></td>
                <td>
                  <a href="<%=request.getContextPath()%>/dich-vu/cap-nhat?maDichVu=<%=dichVu.getMaDV() %>" class="btn btn-warning edit-may"><i class="fa fa-pencil-square-o"></i></a>
                  <button class="btn btn-danger delete-dichvu"><i class="fa fa-trash-o"></i></button>
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
$("#table-body").on("click", ".delete-dichvu", function () {
	var index = $(this).closest("tr").find("td:first").text().trim();
    var confirmDelete = confirm("Bạn có chắc chắn muốn xóa không ?");
    if (confirmDelete) {
		window.location.href = "<%=request.getContextPath()%>/dich-vu/xoa-dich-vu?maDichVu="+index;
    }
  });
</script>
<jsp:include page="../../footer.jsp" />