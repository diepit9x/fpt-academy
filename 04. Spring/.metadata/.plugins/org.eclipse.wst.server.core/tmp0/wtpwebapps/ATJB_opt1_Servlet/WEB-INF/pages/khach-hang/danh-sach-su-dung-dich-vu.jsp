<%@page import="java.util.Map"%>
<%@page import="utils.DateTimeUtil"%>
<%@page import="models.SelectData"%>
<%@page import="entities.SuDungDichVuMay"%>
<%@page import="models.PagedResult"%>
<%@page import="entities.KhachHang"%>
<%@page import="java.util.List"%>
<%@page import="models.ResponseData"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	//set title
	request.setAttribute("pageTitle", "Danh sách khách hàng sử dụng dịch vụ");

    ResponseData responseData = request.getAttribute("responseData") != null ? ((ResponseData) request.getAttribute("responseData")) : new ResponseData(204, null);
    PagedResult<SuDungDichVuMay> pagedResult = (PagedResult<SuDungDichVuMay>) responseData.getObject();
    
    List<SuDungDichVuMay> sudungdichvumayList = pagedResult.getResults();
    int currentPage =pagedResult.getCurrentPage();
    int totalPages = pagedResult.getTotalPages();
%>
<jsp:include page="../../header.jsp" />
<style>
    .container {
        margin: 10px 15px 10px 15px;
        max-width: 97%;
    }
    </style>
<h1 class="pb-2 border-bottom">Danh sách sử dụng dịch vụ</h1>
<div class="flex-row">
  <div class="table-responsive">
    <table class="table table-bordered">
      <thead>
        <tr>
          <th>Mã KH</th>
          <th>Tên KH</th>
          <th>Mã máy</th>
          <th>Vị trí</th>
          <th>Trạng thái</th>
          <th>Ngày BĐSD</th>
          <th>Giờ BĐSD</th>
          <th>Thời gian SD</th>
          <th>Mã DV</th>
          <th>Ngày SDDV</th>
          <th>Giờ SDDV</th>
          <th>Số lượng</th>
          <th>Đơn giá</th>
          <th>Tổng tiền</th>
        </tr>
      </thead>
      <tbody id="table-body">      
<% 
    if (sudungdichvumayList != null) {
        // Tính tổng tiền cho các dịch vụ trùng id
        Map<String, Integer> totalPriceMap = SuDungDichVuMay.calculateTotalPrice(sudungdichvumayList);
        
        for (int i = 0; i < sudungdichvumayList.size(); i++) {
            SuDungDichVuMay sudungdichvumay = sudungdichvumayList.get(i);
            
%>
    <tr>
        <td><%= sudungdichvumay.getMaKH() %></td>
        <td><%= sudungdichvumay.getTenKH() %></td>
        <td><%= sudungdichvumay.getMaMay() %></td>
        <td><%= sudungdichvumay.getViTri() %></td>
        <td><%= SelectData.trangThai(sudungdichvumay.getTrangThai()) %></td>
        <td><%= DateTimeUtil.formatLocalDate(sudungdichvumay.getNgaybatDauSuDung()) %></td>
        <td><%= DateTimeUtil.formatLocalTime(sudungdichvumay.getGioBatDauSuDung()) %></td>
        <td><%= sudungdichvumay.getThoiGianSuDung() != null ? sudungdichvumay.getThoiGianSuDung():"Đang sử dụng" %></td>
        <td><%= sudungdichvumay.getMaDV() == null ? "-":sudungdichvumay.getMaDV() %></td>
        <td><%= DateTimeUtil.formatLocalDate(sudungdichvumay.getNgaySuDung()) %></td>
        <td><%= DateTimeUtil.formatLocalTime(sudungdichvumay.getGioSuDung()) %></td>
        <td><%= sudungdichvumay.getSoLuong() == null ? "-":sudungdichvumay.getSoLuong()%></td>
        <td><%= sudungdichvumay.getDonGia() == null ? "-":sudungdichvumay.getDonGia() %></td>
        <td>
            <%=totalPriceMap.get(sudungdichvumay.getId()) == null ? 0:totalPriceMap.get(sudungdichvumay.getId())%>
        </td>
    </tr>
<% 
        }
    }
%>
      </tbody>
    </table>
<nav aria-label="Page navigation example">
    <ul class="pagination justify-content-center">
        <li class="page-item <%= currentPage == 1 ? "d-none" : "" %>">
            <a class="page-link" href="?page=<%= currentPage - 1 %>">Previous</a>
        </li>
        <% for (int i = 1; i <= totalPages; i++) { %>
            <li class="page-item <%= currentPage == i ? "active" : "" %>">
                <a class="page-link" href="?page=<%= i %>"><%= i %></a>
            </li>
        <% } %>
        <li class="page-item <%= currentPage == totalPages ? "d-none" : "" %>">
            <a class="page-link" href="?page=<%= currentPage + 1 %>">Next</a>
        </li>
    </ul>
</nav>
  </div>
</div>
<jsp:include page="../../footer.jsp" />