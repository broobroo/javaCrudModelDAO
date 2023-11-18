package com.example.util;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServletUtils {

    public static JsonObject parseJsonRequest(javax.servlet.http.HttpServletRequest request) throws IOException {
        try (BufferedReader reader = request.getReader();
             JsonReader jsonReader = Json.createReader(reader)) {
            return jsonReader.readObject();
        }
    }

    public static void validateRequestData(JsonObject data, String... keys) throws IOException {
        for (String key : keys) {
            if (data.getString(key, "").isEmpty()) {
                throw new IOException("Missing required field: " + key);
            }
        }
    }

    public static String convertResultSetToJson(ResultSet resultSet) throws SQLException {
        return ResultSetTableDisplay.toJson(resultSet);
    }

    public static void validateRequestData(JsonObject data) throws IOException {
    if (data.getString("nom", "").isEmpty() || data.getString("hexadecimal_rvb", "").isEmpty()) {
        throw new IOException("Missing color name or hexadecimal value");
        }
    }

    public static void sendJsonResponse(HttpServletResponse response, String json) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.print(json);
        }
    }

    public static void handleSqlException(HttpServletResponse response, SQLException ex) throws IOException {
        // Log SQL Exception here
        sendErrorResponse(response, HttpServletResponse.SC_BAD_REQUEST, "SQL Error: " + ex.getMessage());
    }

    public static void sendErrorResponse(HttpServletResponse response, int statusCode, String message) throws IOException {
        response.sendError(statusCode, message);
    }

}
