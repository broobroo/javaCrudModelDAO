
package com.example.util;

import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;




public class ResultSetTableDisplay {

    public static void display(ResultSet resultSet) throws SQLException {
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();

        // Affichage des en-têtes de colonnes
        for (int i = 1; i <= columnCount; i++) {
            if (i > 1) System.out.print(",  ");
            System.out.print(metaData.getColumnName(i));
        }
        System.out.println();

        // Affichage des lignes de données
        while (resultSet.next()) {
            for (int i = 1; i <= columnCount; i++) {
                if (i > 1) System.out.print(",  ");
                System.out.print(resultSet.getString(i));
            }
            System.out.println();
        }
    }

    public static String toHtmlTable(ResultSet resultSet) throws SQLException {
        StringBuilder htmlTable = new StringBuilder();
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();

        htmlTable.append("<table border='1'>");

        // En-têtes de colonnes
        htmlTable.append("<tr>");
        for (int i = 1; i <= columnCount; i++) {
            htmlTable.append("<th>").append(metaData.getColumnName(i)).append("</th>");
        }
        htmlTable.append("</tr>");

        // Données
        while (resultSet.next()) {
            htmlTable.append("<tr>");
            for (int i = 1; i <= columnCount; i++) {
                htmlTable.append("<td>").append(resultSet.getString(i)).append("</td>");
            }
            htmlTable.append("</tr>");
        }

        htmlTable.append("</table>");

        return htmlTable.toString();
    }
    
     public static void displayHtmlTable(ResultSet resultSet, PrintWriter out) throws SQLException {
        out.println("<html><body>");
        out.println(toHtmlTable(resultSet));
        out.println("</body></html>");
    }

    public static String toJson(ResultSet resultSet) throws SQLException {
        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();

        while (resultSet.next()) {
            JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
            for (int i = 1; i <= columnCount; i++) {
                jsonObjectBuilder.add(metaData.getColumnName(i), resultSet.getString(i));
            }
            jsonArrayBuilder.add(jsonObjectBuilder);
        }

        return jsonArrayBuilder.build().toString();
    }
    
}
