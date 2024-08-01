/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/10.1.26
 * Generated at: 2024-08-01 03:31:48 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.jsp.pages.khach_002dhang;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.jsp.*;
import java.util.Map;
import fa.training.utils.DateTimeUtil;
import fa.training.models.SelectData;
import java.util.List;
import fa.training.entities.SuDungDichVuMay;
import fa.training.models.PagedResult;
import fa.training.models.ResponseData;

public final class danh_002dsach_002dsu_002ddung_002ddich_002dvu_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports,
                 org.apache.jasper.runtime.JspSourceDirectives {

  private static final jakarta.servlet.jsp.JspFactory _jspxFactory =
          jakarta.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.LinkedHashSet<>(4);
    _jspx_imports_packages.add("jakarta.servlet");
    _jspx_imports_packages.add("jakarta.servlet.http");
    _jspx_imports_packages.add("jakarta.servlet.jsp");
    _jspx_imports_classes = new java.util.LinkedHashSet<>(10);
    _jspx_imports_classes.add("java.util.List");
    _jspx_imports_classes.add("fa.training.entities.SuDungDichVuMay");
    _jspx_imports_classes.add("java.util.Map");
    _jspx_imports_classes.add("fa.training.models.PagedResult");
    _jspx_imports_classes.add("fa.training.models.SelectData");
    _jspx_imports_classes.add("fa.training.models.ResponseData");
    _jspx_imports_classes.add("fa.training.utils.DateTimeUtil");
  }

  private volatile jakarta.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public boolean getErrorOnELNotFound() {
    return false;
  }

  public jakarta.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final jakarta.servlet.http.HttpServletRequest request, final jakarta.servlet.http.HttpServletResponse response)
      throws java.io.IOException, jakarta.servlet.ServletException {

    if (!jakarta.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      final java.lang.String _jspx_method = request.getMethod();
      if ("OPTIONS".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        return;
      }
      if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET, POST or HEAD. Jasper also permits OPTIONS");
        return;
      }
    }

    final jakarta.servlet.jsp.PageContext pageContext;
    jakarta.servlet.http.HttpSession session = null;
    final jakarta.servlet.ServletContext application;
    final jakarta.servlet.ServletConfig config;
    jakarta.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    jakarta.servlet.jsp.JspWriter _jspx_out = null;
    jakarta.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");

	//set title
	request.setAttribute("pageTitle", "Danh sách khách hàng sử dụng dịch vụ");

    ResponseData responseData = request.getAttribute("responseData") != null ? ((ResponseData) request.getAttribute("responseData")) : new ResponseData(204, null);
    PagedResult<SuDungDichVuMay> pagedResult = (PagedResult<SuDungDichVuMay>) responseData.getObject();
    
    List<SuDungDichVuMay> sudungdichvumayList = pagedResult.getResults();
    int currentPage =pagedResult.getCurrentPage();
    int totalPages = pagedResult.getTotalPages();

      out.write('\r');
      out.write('\n');
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../../header.jsp", out, false);
      out.write("\r\n");
      out.write("<style>\r\n");
      out.write("    .container {\r\n");
      out.write("        margin: 10px 15px 10px 15px;\r\n");
      out.write("        max-width: 97%;\r\n");
      out.write("    }\r\n");
      out.write("    </style>\r\n");
      out.write("<h1 class=\"pb-2 border-bottom\">Danh sách sử dụng dịch vụ</h1>\r\n");
      out.write("<div class=\"flex-row\">\r\n");
      out.write("  <div class=\"table-responsive\">\r\n");
      out.write("    <table class=\"table table-bordered\">\r\n");
      out.write("      <thead>\r\n");
      out.write("        <tr>\r\n");
      out.write("          <th>Mã KH</th>\r\n");
      out.write("          <th>Tên KH</th>\r\n");
      out.write("          <th>Mã máy</th>\r\n");
      out.write("          <th>Vị trí</th>\r\n");
      out.write("          <th>Trạng thái</th>\r\n");
      out.write("          <th>Ngày BĐSD</th>\r\n");
      out.write("          <th>Giờ BĐSD</th>\r\n");
      out.write("          <th>Thời gian SD</th>\r\n");
      out.write("          <th>Mã DV</th>\r\n");
      out.write("          <th>Ngày SDDV</th>\r\n");
      out.write("          <th>Giờ SDDV</th>\r\n");
      out.write("          <th>Số lượng</th>\r\n");
      out.write("          <th>Đơn giá</th>\r\n");
      out.write("          <th>Tổng tiền</th>\r\n");
      out.write("        </tr>\r\n");
      out.write("      </thead>\r\n");
      out.write("      <tbody id=\"table-body\">      \r\n");
 
    if (sudungdichvumayList != null) {
        // Tính tổng tiền cho các dịch vụ trùng id
        Map<String, Integer> totalPriceMap = SuDungDichVuMay.calculateTotalPrice(sudungdichvumayList);
        
        for (int i = 0; i < sudungdichvumayList.size(); i++) {
            SuDungDichVuMay sudungdichvumay = sudungdichvumayList.get(i);
            

      out.write("\r\n");
      out.write("    <tr>\r\n");
      out.write("        <td>");
      out.print( sudungdichvumay.getMaKH() );
      out.write("</td>\r\n");
      out.write("        <td>");
      out.print( sudungdichvumay.getTenKH() );
      out.write("</td>\r\n");
      out.write("        <td>");
      out.print( sudungdichvumay.getMaMay() );
      out.write("</td>\r\n");
      out.write("        <td>");
      out.print( sudungdichvumay.getViTri() );
      out.write("</td>\r\n");
      out.write("        <td>");
      out.print( SelectData.trangThai(sudungdichvumay.getTrangThai()) );
      out.write("</td>\r\n");
      out.write("        <td>");
      out.print( DateTimeUtil.formatLocalDate(sudungdichvumay.getNgaybatDauSuDung()) );
      out.write("</td>\r\n");
      out.write("        <td>");
      out.print( DateTimeUtil.formatLocalTime(sudungdichvumay.getGioBatDauSuDung()) );
      out.write("</td>\r\n");
      out.write("        <td>");
      out.print( sudungdichvumay.getThoiGianSuDung() != null ? sudungdichvumay.getThoiGianSuDung():"Đang sử dụng" );
      out.write("</td>\r\n");
      out.write("        <td>");
      out.print( sudungdichvumay.getMaDV() == null ? "-":sudungdichvumay.getMaDV() );
      out.write("</td>\r\n");
      out.write("        <td>");
      out.print( DateTimeUtil.formatLocalDate(sudungdichvumay.getNgaySuDung()) );
      out.write("</td>\r\n");
      out.write("        <td>");
      out.print( DateTimeUtil.formatLocalTime(sudungdichvumay.getGioSuDung()) );
      out.write("</td>\r\n");
      out.write("        <td>");
      out.print( sudungdichvumay.getSoLuong() == null ? "-":sudungdichvumay.getSoLuong());
      out.write("</td>\r\n");
      out.write("        <td>");
      out.print( sudungdichvumay.getDonGia() == null ? "-":sudungdichvumay.getDonGia() );
      out.write("</td>\r\n");
      out.write("        <td>\r\n");
      out.write("            ");
      out.print(totalPriceMap.get(sudungdichvumay.getId()) == null ? 0:totalPriceMap.get(sudungdichvumay.getId()));
      out.write("\r\n");
      out.write("        </td>\r\n");
      out.write("    </tr>\r\n");
 
        }
    }

      out.write("\r\n");
      out.write("      </tbody>\r\n");
      out.write("    </table>\r\n");
      out.write("<nav aria-label=\"Page navigation example\">\r\n");
      out.write("    <ul class=\"pagination justify-content-center\">\r\n");
      out.write("        <li class=\"page-item ");
      out.print( currentPage == 1 ? "d-none" : "" );
      out.write("\">\r\n");
      out.write("            <a class=\"page-link\" href=\"?page=");
      out.print( currentPage - 1 );
      out.write("\">Previous</a>\r\n");
      out.write("        </li>\r\n");
      out.write("        ");
 for (int i = 1; i <= totalPages; i++) { 
      out.write("\r\n");
      out.write("            <li class=\"page-item ");
      out.print( currentPage == i ? "active" : "" );
      out.write("\">\r\n");
      out.write("                <a class=\"page-link\" href=\"?page=");
      out.print( i );
      out.write('"');
      out.write('>');
      out.print( i );
      out.write("</a>\r\n");
      out.write("            </li>\r\n");
      out.write("        ");
 } 
      out.write("\r\n");
      out.write("        <li class=\"page-item ");
      out.print( currentPage == totalPages ? "d-none" : "" );
      out.write("\">\r\n");
      out.write("            <a class=\"page-link\" href=\"?page=");
      out.print( currentPage + 1 );
      out.write("\">Next</a>\r\n");
      out.write("        </li>\r\n");
      out.write("    </ul>\r\n");
      out.write("</nav>\r\n");
      out.write("  </div>\r\n");
      out.write("</div>\r\n");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../../footer.jsp", out, false);
      out.write('\r');
      out.write('\n');
    } catch (java.lang.Throwable t) {
      if (!(t instanceof jakarta.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
