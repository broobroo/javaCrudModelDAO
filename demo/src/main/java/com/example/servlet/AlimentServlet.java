package com.example.servlet;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import com.example.dao.AlimentDAO;
import com.example.dao.DatabaseConnection;
import com.example.util.Routeur;

import java.io.IOException;

@WebServlet("/aliment/*")
public class AlimentServlet extends HttpServlet {

    private AlimentDAO alimentDao;
    private Routeur routeur;

    public void init() {
        ServletContext context = getServletContext();
        DatabaseConnection dbConnection = (DatabaseConnection) context.getAttribute("DB_CONNECTION");
        alimentDao = new AlimentDAO(dbConnection);
        routeur = new Routeur();
    }

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        routeur.routeRequest(request, response, alimentDao);
    }

    // Vous pouvez ajouter des méthodes supplémentaires si nécessaire
}
