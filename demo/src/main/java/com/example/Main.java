package com.example;
import java.io.File;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.Wrapper;
import org.apache.catalina.startup.Tomcat;

import com.example.dao.DatabaseConnection;
import com.example.servlet.AlimentServlet;
import com.example.servlet.CouleurServlet;
import com.example.servlet.DataAccessServletTest;

public class Main {
    public static void main(String[] args) throws LifecycleException {

      
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);
        
        // Initialisation de la connexion à la base de données
        String jdbcURL = "jdbc:mysql://localhost:3306/alimentations";
        String jdbcUsername = "root";
        String jdbcPassword = "";
        DatabaseConnection dbConnection = new DatabaseConnection(jdbcURL, jdbcUsername, jdbcPassword);


        // Contexte de l'application
        String contextPath = "/";
        String docBase = new File(".").getAbsolutePath();
        Context context = tomcat.addWebapp(contextPath, docBase);

        // Stocker la connexion dans le contexte de l'application
        context.getServletContext().setAttribute("DB_CONNECTION", dbConnection);


        // Ajouter un servlet
        Wrapper servletWrapper = Tomcat.addServlet(context, "DataAccessServlet", new DataAccessServletTest());
        servletWrapper.setLoadOnStartup(1);
        servletWrapper.addMapping("/dataaccess");

        // Ajouter un autre servlet pour CouleurServlet
        Wrapper couleurServletWrapper = Tomcat.addServlet(context, "CouleurServlet", new CouleurServlet());
        couleurServletWrapper.setLoadOnStartup(1);
        couleurServletWrapper.addMapping("/couleur/*");     
        
       // Ajouter un autre servlet pour AlimentServlet
        Wrapper alimentServletWrapper = Tomcat.addServlet(context, "ailmentServlet", new AlimentServlet());
        alimentServletWrapper.setLoadOnStartup(1);
        alimentServletWrapper.addMapping("/aliment/*");          
        
        tomcat.getConnector();

        // Démarrer le serveur
        tomcat.start();
        tomcat.getServer().await();
    }
}
