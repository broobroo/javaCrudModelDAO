package com.example.servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.example.dao.AlimentDAO;
import com.example.dao.CouleurDAO;
import com.example.dao.DatabaseConnection;
import com.example.util.ResultSetTableDisplay;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

;@WebServlet("/dataaccess")
public class DataAccessServletTest extends HttpServlet  {

    private DatabaseConnection dbConnection;
    private CouleurDAO couleurDao;
    private AlimentDAO alimentDao;

    public void init() {
        // Récupérer la connexion à partir du contexte de l'application
        ServletContext contextdb = getServletContext();
        dbConnection = (DatabaseConnection) contextdb.getAttribute("DB_CONNECTION");
        
        couleurDao = new CouleurDAO(dbConnection);
        alimentDao = new AlimentDAO(dbConnection);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            dbConnection.connect();
            // Ici, vous pouvez effectuer des opérations sur la base de données
            // Exemple : récupérer des données et les afficher
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            
            // Envoyer le HTML à la réponse
           
            ResultSetTableDisplay.displayHtmlTable(couleurDao.listAllCouleurs(), out);
            ResultSetTableDisplay.displayHtmlTable(alimentDao.listAllAliments(), out);

            dbConnection.disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
