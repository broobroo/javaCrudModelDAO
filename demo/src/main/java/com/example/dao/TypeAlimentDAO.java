package com.example.dao;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.util.ResultSetTableDisplay;
import com.example.util.ServletUtils;

import javax.json.JsonObject;



public class TypeAlimentDAO implements IGenericCRUD {

    private DatabaseConnection dbConnection;

    public TypeAlimentDAO(DatabaseConnection dbConnection) {
        this.dbConnection = dbConnection;
    }
    
    public void handleGetHTML(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        // Connexion à la base de données
        dbConnection.connect();
    
        // Configuration de la réponse HTTP
        response.setContentType("text/html");
    
        // Récupération du flux de sortie pour écrire la réponse
        PrintWriter out = response.getWriter();          
    
        // Affichage des résultats de la requête sous forme de tableau HTML
        ResultSetTableDisplay.displayHtmlTable(listAllTypeAliment(), out);
    
        // Conversion des résultats de la requête en une chaîne de caractères contenant un tableau HTML
        String typeAlimentHtml  = ResultSetTableDisplay.toHtmlTable(listAllTypeAliment());
        System.out.println(typeAlimentHtml);
    
        // Déconnexion de la base de données
        dbConnection.disconnect();
    }
    
    @Override
    public void handleGet(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        try {
            // Connexion à la base de données et récupération des données
            dbConnection.connect();
            ResultSet resultSet = listAllTypeAliment();
    
            // Convertir le ResultSet en JSON
            String jsonResponse = ServletUtils.convertResultSetToJson(resultSet);
    
            // Envoyer la réponse JSON
            ServletUtils.sendJsonResponse(response, jsonResponse);
    
            // Fermer ResultSet et déconnexion de la base de données
            resultSet.close();
            dbConnection.disconnect();
        } catch (SQLException ex) {
            ServletUtils.handleSqlException(response, ex);
        } catch (Exception ex) {
            ServletUtils.sendErrorResponse(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Server error: " + ex.getMessage());
        }
    }
    
   
    
    
    @Override
    public void handlePost(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        try {
            // Lire et parser le corps de la requête JSON
            JsonObject jsonObject = ServletUtils.parseJsonRequest(request);

            // Récupérer les valeurs 'nom' et 'hexadecimal_rvb'
            String nom = jsonObject.getString("nom");

            // Validation
            ServletUtils.validateRequestData(jsonObject, "nom");

            // Insérer la nouvelle couleur et récupérer un ResultSet
            ResultSet resultSet = insertTypeAlimentAndGet(nom);

            // Utiliser toJson pour convertir le ResultSet en JSON
            String jsonResponse = ServletUtils.convertResultSetToJson(resultSet);

            // Envoyer la réponse JSON
            ServletUtils.sendJsonResponse(response, jsonResponse);
        } catch (SQLException ex) {
            ServletUtils.handleSqlException(response, ex);
        } catch (IOException ex) {
            ServletUtils.sendErrorResponse(response, HttpServletResponse.SC_BAD_REQUEST, ex.getMessage());
        } catch (Exception ex) {
            ServletUtils.sendErrorResponse(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error inserting TypeAliment: " + ex.getMessage());
        }
    }


    @Override
    public void handlePut(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        try {
            // Lire et parser le corps de la requête JSON
            JsonObject jsonObject = ServletUtils.parseJsonRequest(request);
    
            // Récupérer les données 'id', 'nom', et 'hexadecimal_rvb'
            int id = jsonObject.getInt("id");
            String nomTypeAliment = jsonObject.getString("nom");
    
            // Validation
            if (nomTypeAliment.isEmpty()) {
                ServletUtils.sendErrorResponse(response, HttpServletResponse.SC_BAD_REQUEST, "Missing required fields");
                return;
            }
    
            // Mettre à jour la couleur
            boolean updated = updateTypeAliment(id, nomTypeAliment);
    
            // Envoyer une réponse en fonction du succès de la mise à jour
            if (updated) {
                ServletUtils.sendJsonResponse(response, "{\"message\": \"TypeAliment updated successfully.\"}");
            } else {
                ServletUtils.sendErrorResponse(response, HttpServletResponse.SC_NOT_FOUND, "TypeAliment not found or not updated");
            }
        } catch (NumberFormatException ex) {
            ServletUtils.sendErrorResponse(response, HttpServletResponse.SC_BAD_REQUEST, "Invalid format for ID");
        } catch (SQLException ex) {
            ServletUtils.handleSqlException(response, ex);
        } catch (Exception ex) {
            ServletUtils.sendErrorResponse(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Server error: " + ex.getMessage());
        }
    }
    

    @Override
    public void handleDelete(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        try {
            // Lire et parser le corps de la requête JSON
            JsonObject jsonObject = ServletUtils.parseJsonRequest(request);
    
            // Récupérer l'identifiant de la couleur à supprimer
            int id = jsonObject.getInt("id");
            
    
            // Supprimer la couleur
            boolean deleted = deleteTypeAliment(id);
    
            // Envoyer une réponse en fonction du succès de la suppression
            if (deleted) {
                ServletUtils.sendJsonResponse(response, "{\"message\": \"TypeAliment deleted successfully.\"}");
            } else {
                ServletUtils.sendErrorResponse(response, HttpServletResponse.SC_NOT_FOUND, "TypeAliment not found or not deleted");
            }
        } catch (NumberFormatException ex) {
            ServletUtils.sendErrorResponse(response, HttpServletResponse.SC_BAD_REQUEST, "Invalid format for ID");
        } catch (SQLException ex) {
            ServletUtils.handleSqlException(response, ex);
        } catch (Exception ex) {
            ServletUtils.sendErrorResponse(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Server error: " + ex.getMessage());
        }
    }
    
    @Override
    public void handleFindById(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        try {
            // Extraire l'ID de la couleur à partir des paramètres de la requête
            int id = Integer.parseInt(request.getParameter("id"));

            // Récupérer la couleur par son ID
            ResultSet resultSet = findById(id);

            // Convertir le ResultSet en JSON
            String jsonResponse = ServletUtils.convertResultSetToJson(resultSet);

            // Envoyer la réponse JSON
            ServletUtils.sendJsonResponse(response, jsonResponse);

            // Fermer ResultSet et déconnexion de la base de données
            resultSet.close();
            dbConnection.disconnect();
        } catch (NumberFormatException ex) {
            ServletUtils.sendErrorResponse(response, HttpServletResponse.SC_BAD_REQUEST, "Invalid ID format");
        } catch (SQLException ex) {
            ServletUtils.handleSqlException(response, ex);
        } catch (Exception ex) {
            ServletUtils.sendErrorResponse(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Server error: " + ex.getMessage());
        }
    }

    @Override
    public void handleFindByName(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        try {
            String name = request.getParameter("name");
            ResultSet resultSet = findByName(name);

            String jsonResponse = ServletUtils.convertResultSetToJson(resultSet);
            ServletUtils.sendJsonResponse(response, jsonResponse);

            resultSet.close();
            dbConnection.disconnect();
        } catch (SQLException ex) {
            ServletUtils.handleSqlException(response, ex);
        } catch (Exception ex) {
            ServletUtils.sendErrorResponse(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Server error: " + ex.getMessage());
        }
    }

    public boolean insertTypeAliment(String nom) throws SQLException {
        String sql = "INSERT INTO type_aliment (nom) VALUES (?)";
        dbConnection.connect();
        PreparedStatement statement = dbConnection.getJdbcConnection().prepareStatement(sql);
    
        statement.setString(1, nom);
    
        boolean result = statement.executeUpdate() > 0;
    
        statement.close();
        dbConnection.disconnect();
        
        return result;
    }
    
    public ResultSet insertTypeAlimentAndGet(String nom) throws SQLException {
        String insertSql = "INSERT INTO type_aliment (nom) VALUES (?)";
        String selectSql = "SELECT * FROM type_aliment WHERE id = ?";
        
        dbConnection.connect();
        
        // Insertion de la nouvelle couleur
        PreparedStatement insertStatement = dbConnection.getJdbcConnection().prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
        insertStatement.setString(1, nom);
        insertStatement.executeUpdate();
        
        // Récupération de l'identifiant généré
        ResultSet generatedKeys = insertStatement.getGeneratedKeys();
        if (!generatedKeys.next()) {
            throw new SQLException("Creating TypeAliment failed, no ID obtained.");
        }
        int newTypeAlimentId = generatedKeys.getInt(1);
        insertStatement.close();
    
        // Récupération de la nouvelle couleur insérée
        PreparedStatement selectStatement = dbConnection.getJdbcConnection().prepareStatement(selectSql);
        selectStatement.setInt(1, newTypeAlimentId);
        ResultSet resultSet = selectStatement.executeQuery();
    
        // Note: La gestion de la fermeture du ResultSet et de la déconnexion de la base de données devrait être effectuée par l'appelant.
        
        return resultSet;
    }
    

    public ResultSet listAllTypeAliment() throws SQLException {
        String sql = "SELECT * FROM type_aliment";
        dbConnection.connect();

        Statement statement = dbConnection.getJdbcConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        // Note: The caller should handle closing the resultSet and disconnecting
        return resultSet;
    }

    public boolean updateTypeAliment(int id, String nomTypeAliment) throws SQLException {
        String sql = "UPDATE type_aliment SET nom = ? WHERE id = ?";
        dbConnection.connect();

        PreparedStatement statement = dbConnection.getJdbcConnection().prepareStatement(sql);
        statement.setString(1, nomTypeAliment);
        statement.setInt(2, id);

        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        dbConnection.disconnect();
        return rowUpdated;
    }

    public boolean deleteTypeAliment(int id) throws SQLException {
        String sql = "DELETE FROM type_aliment WHERE id = ?";

        dbConnection.connect();

        PreparedStatement statement = dbConnection.getJdbcConnection().prepareStatement(sql);
        statement.setInt(1, id);

        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        dbConnection.disconnect();
        return rowDeleted;
    }

    public ResultSet getLastInsertedTypeAliment() throws SQLException {
        String sql = "SELECT * FROM type_aliment ORDER BY id DESC LIMIT 1";

        //dbConnection.connect();

        Statement statement = dbConnection.getJdbcConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        
        // Note: The caller should handle closing the resultSet and disconnecting
        return resultSet;
    }
    
    // Méthode pour trouver une couleur par son ID
    public ResultSet findById(int id) throws SQLException {
        String sql = "SELECT * FROM type_aliment WHERE id = ?";
        dbConnection.connect();
        PreparedStatement statement = dbConnection.getJdbcConnection().prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        // Note: La gestion de la fermeture du ResultSet et de la déconnexion de la base de données devrait être effectuée par l'appelant.
        return resultSet;
    }

    public ResultSet findByName(String name) throws SQLException {
        String sql = "SELECT * FROM type_aliment WHERE nom = ?";
        dbConnection.connect();
        PreparedStatement statement = dbConnection.getJdbcConnection().prepareStatement(sql);
        statement.setString(1, name);
        return statement.executeQuery();
    }
}
