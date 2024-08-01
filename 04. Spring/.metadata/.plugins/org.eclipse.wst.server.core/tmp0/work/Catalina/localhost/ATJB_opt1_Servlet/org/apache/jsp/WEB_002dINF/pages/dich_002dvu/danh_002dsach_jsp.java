/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/9.0.82
 * Generated at: 2024-07-31 09:08:00 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.pages.dich_002dvu;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import entities.DichVu;
import models.PagedResult;
import java.util.List;
import models.ResponseData;
import models.SelectData;

public final class danh_002dsach_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = new java.util.HashSet<>();
    _jspx_imports_classes.add("models.ResponseData");
    _jspx_imports_classes.add("models.SelectData");
    _jspx_imports_classes.add("java.util.List");
    _jspx_imports_classes.add("entities.DichVu");
    _jspx_imports_classes.add("models.PagedResult");
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
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

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
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

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    if (!javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
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

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


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

	//set title
	request.setAttribute("pageTitle", "Danh sách dịch vụ");
	String keyword = (String) request.getAttribute("keyword");

    ResponseData responseData = request.getAttribute("responseData") != null ? ((ResponseData) request.getAttribute("responseData")) : new ResponseData(204, null);
    PagedResult<DichVu> pagedResult = (PagedResult<DichVu>) responseData.getObject();
    
    List<DichVu> dichVus = pagedResult.getResults();
    int currentPage =pagedResult.getCurrentPage();
    int totalPages = pagedResult.getTotalPages();

      out.write("\r\n");
      out.write("\r\n");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../../header.jsp", out, false);
      out.write("\r\n");
      out.write("\r\n");
      out.write("<h1 class=\"pb-2 border-bottom\">Danh sách dịch vụ</h1>\r\n");
      out.write("<div class=\"flex-row\">\r\n");
      out.write("  <form action=\"");
      out.print(request.getContextPath());
      out.write("/dich-vu/danh-sach\" method=\"POST\">\r\n");
      out.write("    <div class=\"input-group mb-2 mt-4\">\r\n");
      out.write("      <input type=\"text\" class=\"form-control\" name=\"keyword\" placeholder=\"Tìm kiếm...\" value=\"");
      out.print(keyword != null ? keyword :"" );
      out.write("\" />\r\n");
      out.write("      <div class=\"input-group-append\">\r\n");
      out.write("        <button class=\"btn btn-outline-info\" id=\"searchBtn\" type=\"submit\"><i class=\"fa fa-search\"></i></button>\r\n");
      out.write("      </div>\r\n");
      out.write("    </div>\r\n");
      out.write("  </form>\r\n");
      out.write("</div>\r\n");
      out.write("<div class=\"flex-row\">\r\n");
      out.write("  <div class=\"table-responsive\">\r\n");
      out.write("    <table class=\"table table-bordered\">\r\n");
      out.write("      <thead>\r\n");
      out.write("        <tr>\r\n");
      out.write("          <th>Mã DV</th>\r\n");
      out.write("          <th>Tên DV</th>\r\n");
      out.write("          <th>Đơn vị</th>\r\n");
      out.write("          <th>Đơn giá</th>\r\n");
      out.write("          <th>Thao tác</th>\r\n");
      out.write("        </tr>\r\n");
      out.write("      </thead>\r\n");
      out.write("      <tbody id=\"table-body\">\r\n");
      out.write("        ");
 if (dichVus != null) {
            for (DichVu dichVu : dichVus) { 
      out.write("\r\n");
      out.write("              <tr>\r\n");
      out.write("                <td>");
      out.print( dichVu.getMaDV());
      out.write("</td>\r\n");
      out.write("                <td>");
      out.print( dichVu.getTenDV());
      out.write("</td>\r\n");
      out.write("                <td>");
      out.print( SelectData.donVi(dichVu.getDonViTinh()));
      out.write("</td>\r\n");
      out.write("                <td>");
      out.print( dichVu.getDonGia());
      out.write("</td>\r\n");
      out.write("                <td>\r\n");
      out.write("                  <a href=\"");
      out.print(request.getContextPath());
      out.write("/dich-vu/cap-nhat?maDichVu=");
      out.print(dichVu.getMaDV() );
      out.write("\" class=\"btn btn-warning edit-may\"><i class=\"fa fa-pencil-square-o\"></i></a>\r\n");
      out.write("                  <button class=\"btn btn-danger delete-dichvu\"><i class=\"fa fa-trash-o\"></i></button>\r\n");
      out.write("                </td>\r\n");
      out.write("              </tr>\r\n");
      out.write("        ");
 } } 
      out.write("\r\n");
      out.write("      </tbody>\r\n");
      out.write("    </table>\r\n");
      out.write("<nav aria-label=\"Page navigation example\">\r\n");
      out.write("    <ul class=\"pagination justify-content-center\">\r\n");
      out.write("        <li class=\"page-item ");
      out.print( currentPage == 1 ? "d-none" : "" );
      out.write("\">\r\n");
      out.write("            <a class=\"page-link\" href=\"?");
      out.print(keyword != null ? "keyword="+keyword+"&" :"" );
      out.write("page=");
      out.print( currentPage - 1 );
      out.write("\">Previous</a>\r\n");
      out.write("        </li>\r\n");
      out.write("        ");
 for (int i = 1; i <= totalPages; i++) { 
      out.write("\r\n");
      out.write("            <li class=\"page-item ");
      out.print( currentPage == i ? "active" : "" );
      out.write("\">\r\n");
      out.write("                <a class=\"page-link\" href=\"?");
      out.print(keyword != null ? "keyword="+keyword+"&" :"" );
      out.write("page=");
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
      out.write("            <a class=\"page-link\" href=\"?");
      out.print(keyword != null ? "keyword="+keyword+"&" :"" );
      out.write("page=");
      out.print( currentPage + 1 );
      out.write("\">Next</a>\r\n");
      out.write("        </li>\r\n");
      out.write("    </ul>\r\n");
      out.write("</nav>\r\n");
      out.write("  </div>\r\n");
      out.write("</div>\r\n");
      out.write("<script>\r\n");
      out.write("$(\"#table-body\").on(\"click\", \".delete-dichvu\", function () {\r\n");
      out.write("	var index = $(this).closest(\"tr\").find(\"td:first\").text().trim();\r\n");
      out.write("    var confirmDelete = confirm(\"Bạn có chắc chắn muốn xóa không ?\");\r\n");
      out.write("    if (confirmDelete) {\r\n");
      out.write("		window.location.href = \"");
      out.print(request.getContextPath());
      out.write("/dich-vu/xoa-dich-vu?maDichVu=\"+index;\r\n");
      out.write("    }\r\n");
      out.write("  });\r\n");
      out.write("</script>\r\n");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../../footer.jsp", out, false);
      out.write('\r');
      out.write('\n');
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
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