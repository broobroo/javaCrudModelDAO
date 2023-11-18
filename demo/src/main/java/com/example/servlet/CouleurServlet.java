package com.example.servlet;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import com.example.dao.CouleurDAO;
import com.example.dao.DatabaseConnection;
import com.example.util.Routeur;

import java.io.IOException;

@WebServlet("/couleur/*")
public class CouleurServlet extends HttpServlet {

    //private DatabaseConnection dbConnection;
    private CouleurDAO couleurDao;
    private Routeur routeur;

    public void init() {
        // Initialisation avec vos paramètres de connexion
        ServletContext context = getServletContext();
        DatabaseConnection dbConnection = (DatabaseConnection) context.getAttribute("DB_CONNECTION");
        couleurDao = new CouleurDAO(dbConnection);
        routeur = new Routeur();
    }

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        routeur.routeRequest(request, response, couleurDao);
    }
    
}
