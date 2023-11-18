package com.example.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.dao.IGenericCRUD;

import java.io.IOException;

public class Routeur {

    public void routeRequest(HttpServletRequest request, HttpServletResponse response, IGenericCRUD dao) throws IOException {
        String method = request.getMethod();
        // String pathInfo = request.getPathInfo();
        String idParam = request.getParameter("id");
        String nameParam = request.getParameter("name");

        try {
            if ("GET".equals(method) && nameParam != null) {
                dao.handleFindByName(request, response);
            } else if ("GET".equals(method) && idParam != null) {
                dao.handleFindById(request, response);
            } else {
            switch (method) {
                case "GET":
                    dao.handleGet(request, response);
                    break;
                case "POST":
                    dao.handlePost(request, response);
                    break;
                case "PUT":
                    dao.handlePut(request, response);
                    break;
                case "DELETE":
                    dao.handleDelete(request, response);
                    break;
                default:
                    response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "Méthode non supportée");
                    break;
            }
        }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur interne du serveur");
        }
    }
}
