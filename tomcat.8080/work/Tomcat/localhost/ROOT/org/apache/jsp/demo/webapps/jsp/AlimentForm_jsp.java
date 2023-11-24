/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/9.0.41
 * Generated at: 2023-11-24 10:22:57 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.demo.webapps.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class AlimentForm_jsp extends org.apache.jasper.runtime.HttpJspBase
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
    _jspx_imports_classes = null;
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
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("    <title>Create Aliment</title>\r\n");
      out.write("    <link rel=\"stylesheet\" type=\"text/css\" href=\"../css/style.css\">\r\n");
      out.write("    <!-- Inclure les fichiers CSS/JS si nécessaire -->\r\n");
      out.write("     <!-- http://localhost:8080/demo/webapps/jsp/AlimentForm.jsp -->\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("     <!-- Inclure le menu -->\r\n");
      out.write("     ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "menu.jsp", out, false);
      out.write("\r\n");
      out.write("\r\n");
      out.write("     <h1>Alimentations</h1>\r\n");
      out.write("    <h2>Create Aliment Form</h2>\r\n");
      out.write("    <form id=\"createAlimentForm\" action=\"/aliment\" method=\"post\">\r\n");
      out.write("        <label for=\"nom\">Name:</label>\r\n");
      out.write("        <input type=\"text\" id=\"nom\" name=\"nom\" required><br><br>\r\n");
      out.write("        \r\n");
      out.write("        <label for=\"poids_moyen\">Average Weight:</label>\r\n");
      out.write("        <input type=\"number\" step=\"0.01\" id=\"poids_moyen\" name=\"poids_moyen\" required><br><br>\r\n");
      out.write("        \r\n");
      out.write("        <label for=\"calories\">Calories:</label>\r\n");
      out.write("        <input type=\"number\" id=\"calories\" name=\"calories\" required><br><br>\r\n");
      out.write("        \r\n");
      out.write("        <label for=\"vitamines_C\">Vitamin C:</label>\r\n");
      out.write("        <input type=\"number\" step=\"0.01\" id=\"vitamines_C\" name=\"vitamines_C\" required><br><br>\r\n");
      out.write("        \r\n");
      out.write("        <label for=\"type_id\">Type ID:</label>\r\n");
      out.write("        <input type=\"number\" id=\"type_id\" name=\"type_id\" required><br><br>\r\n");
      out.write("        \r\n");
      out.write("        <label for=\"couleur_id\">Color ID:</label>\r\n");
      out.write("        <select id=\"couleur_id\" name=\"couleur_id\" required></select><br><br>\r\n");
      out.write("        \r\n");
      out.write("        <input type=\"submit\" value=\"Submit\">\r\n");
      out.write("    </form>\r\n");
      out.write("    <!-- Élément pour afficher la réponse -->\r\n");
      out.write("    <div id=\"response\"></div>\r\n");
      out.write("    <script>\r\n");
      out.write("        // Charger les options de couleur au chargement de la page\r\n");
      out.write("        window.onload = function() {\r\n");
      out.write("            fetch('http://localhost:8080/couleur')\r\n");
      out.write("                .then(response => response.json())\r\n");
      out.write("                .then(data => {\r\n");
      out.write("                    var select = document.getElementById('couleur_id');\r\n");
      out.write("                    data.forEach(function(couleur) {\r\n");
      out.write("                        var option = new Option(couleur.nom, couleur.id);\r\n");
      out.write("                        select.add(option);\r\n");
      out.write("                    });\r\n");
      out.write("                })\r\n");
      out.write("                .catch(error => console.error('Error:', error));\r\n");
      out.write("        };\r\n");
      out.write("\r\n");
      out.write("      document.getElementById('createAlimentForm').addEventListener('submit', function(event) {\r\n");
      out.write("    event.preventDefault();\r\n");
      out.write("\r\n");
      out.write("    var formData = new FormData(this);\r\n");
      out.write("    var jsonData = {};\r\n");
      out.write("\r\n");
      out.write("    for (var [key, value] of formData.entries()) {\r\n");
      out.write("        if (key === 'poids_moyen' || key === 'vitamines_C'  ) {\r\n");
      out.write("        jsonData[key] = parseFloat(value);\r\n");
      out.write("    } else if( key === 'calories' || key === 'type_id' || key === 'couleur_id'){\r\n");
      out.write("        jsonData[key] = parseInt(value);\r\n");
      out.write("    } \r\n");
      out.write("    else {\r\n");
      out.write("        jsonData[key] = value;\r\n");
      out.write("    }\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    console.log(jsonData);\r\n");
      out.write("\r\n");
      out.write("    fetch('http://localhost:8080/aliment', {\r\n");
      out.write("        method: 'POST',\r\n");
      out.write("        headers: {\r\n");
      out.write("            'Content-Type': 'application/json',\r\n");
      out.write("        },\r\n");
      out.write("        body: JSON.stringify(jsonData)\r\n");
      out.write("    })\r\n");
      out.write("    .then(response => {\r\n");
      out.write("        if (!response.ok) {\r\n");
      out.write("            throw new Error('Network response was not ok: ' + response.statusText);\r\n");
      out.write("        }\r\n");
      out.write("        return response.json();\r\n");
      out.write("    })\r\n");
      out.write("    .then(data => {\r\n");
      out.write("        // Traiter la réponse (par exemple, afficher un message de succès)\r\n");
      out.write("        document.getElementById('response').innerHTML = 'Response: ' + JSON.stringify(data);\r\n");
      out.write("    })\r\n");
      out.write("    .catch(error => {\r\n");
      out.write("        // Gérer les erreurs ici\r\n");
      out.write("        console.error('Error:', error);\r\n");
      out.write("    });\r\n");
      out.write("});\r\n");
      out.write("\r\n");
      out.write("    </script>\r\n");
      out.write("    \r\n");
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
