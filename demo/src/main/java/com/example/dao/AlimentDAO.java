package com.example.dao;

import java.io.IOException;
import java.sql.*;

import javax.json.JsonObject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import com.example.model.Aliment;
import com.example.util.ServletUtils;

public class AlimentDAO implements IGenericCRUD{

    private DatabaseConnection dbConnection;

    public AlimentDAO(DatabaseConnection dbConnection) {
        this.dbConnection = dbConnection;
    }


  @Override
    public void handleGet(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
       
        try {
            dbConnection.connect();
            ResultSet resultSet = listAllAliments();
            String jsonResponse = ServletUtils.convertResultSetToJson(resultSet);
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
    
            // Récupérer les valeurs requises
            String nom = jsonObject.getString("nom", null); // Assuming 'nom' is a mandatory field
            float poidsMoyen = (float) jsonObject.getJsonNumber("poids_moyen").doubleValue();
            int calories = jsonObject.getInt("calories");
            float vitaminesC = (float) jsonObject.getJsonNumber("vitamines_C").doubleValue();
            int typeId = jsonObject.getInt("type_id");
            int couleurId = jsonObject.getInt("couleur_id");
    
            // Validation
    

        
    
            // Insérer le nouvel aliment et récupérer un ResultSet
            String jsonResponse = insertAlimentAndGet(nom, poidsMoyen, calories, vitaminesC, typeId, couleurId);
    
            // Utiliser toJson pour convertir le ResultSet en JSON
            ServletUtils.sendJsonResponse(response, jsonResponse);
    
            // Envoyer la réponse JSON
            ServletUtils.sendJsonResponse(response, jsonResponse);
        } catch (SQLException ex) {
            ServletUtils.handleSqlException(response, ex);
        } catch (IOException ex) {
            ServletUtils.sendErrorResponse(response, HttpServletResponse.SC_BAD_REQUEST, ex.getMessage());
        } catch (Exception ex) {
            ServletUtils.sendErrorResponse(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error inserting aliment: " + ex.getMessage());
        } 
        
    }
    

    @Override
    public void handlePut(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        try {
            // Parse the JSON body of the request
            JsonObject jsonObject = ServletUtils.parseJsonRequest(request);
    
            // Retrieve the data from the JSON object
            String nom = jsonObject.getString("nom", null); // Assuming 'nom' is a mandatory field
            float poidsMoyen = (float) jsonObject.getJsonNumber("poids_moyen").doubleValue();
            int calories = jsonObject.getInt("calories");
            float vitaminesC = (float) jsonObject.getJsonNumber("vitamines_C").doubleValue();
            int typeId = jsonObject.getInt("type_id");
            int couleurId = jsonObject.getInt("couleur_id");
            int id = jsonObject.getInt("id");
    
            // Validate the required fields
            if (nom == null || nom.isEmpty()) {
                ServletUtils.sendErrorResponse(response, HttpServletResponse.SC_BAD_REQUEST, "Missing required field: nom");
                return;
            }
    
            // Update the aliment
            boolean updated = updateAliment(id, nom, poidsMoyen, calories, vitaminesC, typeId, couleurId);
    
            // Send a response based on the success of the update
            if (updated) {
                ServletUtils.sendJsonResponse(response, "{\"message\": \"Aliment updated successfully.\"}");
            } else {
                ServletUtils.sendErrorResponse(response, HttpServletResponse.SC_NOT_FOUND, "Aliment not found or not updated");
            }
        } catch (NumberFormatException ex) {
            ServletUtils.sendErrorResponse(response, HttpServletResponse.SC_BAD_REQUEST, "Invalid number format");
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
            boolean deleted = deleteAliment(id);
            if (deleted) {
                ServletUtils.sendJsonResponse(response, "{\"message\": \"Aliment deleted successfully.\"}");
            } else {
                ServletUtils.sendErrorResponse(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error deleting aliment");
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
            // Extraire l'ID de l'aliment à partir des paramètres de la requête
            int id = Integer.parseInt(request.getParameter("id"));
    
            // Récupérer l'aliment par son ID
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
    // Create (Insert)
    // private boolean insertAliment(Aliment aliment) {
    //     String sql = "INSERT INTO aliments (nom, poids_moyen, calories, vitamines_C, type_id, couleur_id) VALUES (?, ?, ?, ?, ?, ?)";
    
    //     try {
    //         dbConnection.connect();
    //         try (PreparedStatement statement = dbConnection.getJdbcConnection().prepareStatement(sql)) {
    //             statement.setString(1, aliment.getNom());
    //             statement.setFloat(2, aliment.getPoidsMoyen());
    //             statement.setInt(3, aliment.getCalories());
    //             statement.setFloat(4, aliment.getVitaminesC());
    
    //             if (aliment.getTypeId() != null) {
    //                 statement.setInt(5, aliment.getTypeId());
    //             } else {
    //                 statement.setNull(5, Types.INTEGER);
    //             }
    
    //             if (aliment.getCouleurId() != null) {
    //                 statement.setInt(6, aliment.getCouleurId());
    //             } else {
    //                 statement.setNull(6, Types.INTEGER);
    //             }
    
    //             return statement.executeUpdate() > 0;
    //         } finally {
    //             dbConnection.disconnect();
    //         }
    //     } catch (SQLException e) {
    //         e.printStackTrace(); // ou une autre gestion des exceptions
    //         return false;
    //     }
    // }        

    public boolean insertAliment(String nom, float poidsMoyen, int calories, float vitaminesC, Integer typeId, Integer couleurId) throws SQLException {
        String sql = "INSERT INTO aliments (nom, poids_moyen, calories, vitamines_C, type_id, couleur_id) VALUES (?, ?, ?, ?, ?, ?)";
        dbConnection.connect();

        PreparedStatement statement = dbConnection.getJdbcConnection().prepareStatement(sql);
        statement.setString(1, nom);
        statement.setFloat(2, poidsMoyen);
        statement.setInt(3, calories);
        statement.setFloat(4, vitaminesC);
        if (typeId != null) {
            statement.setInt(5, typeId);
        } else {
            statement.setNull(5, Types.INTEGER);
        }
        if (couleurId != null) {
            statement.setInt(6, couleurId);
        } else {
            statement.setNull(6, Types.INTEGER);
        }

        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        dbConnection.disconnect();
        return rowInserted;
    }

    // Read (Select)
    public ResultSet listAllAliments() throws SQLException {
        String sql = "SELECT * FROM aliments";
        dbConnection.connect();

        Statement statement = dbConnection.getJdbcConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        // Note: The caller should handle closing the resultSet and disconnecting
        return resultSet;
    }

    public String insertAlimentAndGet(String nom, float poidsMoyen, int calories, float vitaminesC, Integer typeId, Integer couleurId) throws SQLException {
        String insertSql = "INSERT INTO aliments (nom, poids_moyen, calories, vitamines_C, type_id, couleur_id) VALUES (?, ?, ?, ?, ?, ?)";
        String selectSql = "SELECT * FROM aliments WHERE id = ?";
        
        dbConnection.connect();
        
        // Insertion du nouvel aliment
        try (PreparedStatement insertStatement = dbConnection.getJdbcConnection().prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS)) {
            insertStatement.setString(1, nom);
            insertStatement.setFloat(2, poidsMoyen);
            insertStatement.setInt(3, calories);
            insertStatement.setFloat(4, vitaminesC);
    
            if (typeId != null) {
                insertStatement.setInt(5, typeId);
            } else {
                insertStatement.setNull(5, Types.INTEGER);
            }
    
            if (couleurId != null) {
                insertStatement.setInt(6, couleurId);
            } else {
                insertStatement.setNull(6, Types.INTEGER);
            }
    
            insertStatement.executeUpdate();
            
            // Récupération de l'identifiant généré
            try (ResultSet generatedKeys = insertStatement.getGeneratedKeys()) {
                if (!generatedKeys.next()) {
                    throw new SQLException("Creating aliment failed, no ID obtained.");
                }
                int newAlimentId = generatedKeys.getInt(1);
    
                // Récupération du nouvel aliment inséré
                try (PreparedStatement selectStatement = dbConnection.getJdbcConnection().prepareStatement(selectSql)) {
                    selectStatement.setInt(1, newAlimentId);
                    try (ResultSet resultSet = selectStatement.executeQuery()) {
                        // Convert the resultSet to JSON here, before closing the connection
                        String jsonResponse = ServletUtils.convertResultSetToJson(resultSet);
                        return jsonResponse; // Return the JSON string
                    }
                }
            }
        }
    }
    

    // Update

  public boolean updateAliment(int id, String nom, float poidsMoyen, int calories, float vitaminesC, int typeId, int couleurId) throws SQLException {
        String sql = "UPDATE aliments SET nom = ?, poids_moyen = ?, calories = ?, vitamines_C = ?, type_id = ?, couleur_id = ? WHERE id = ?";
        dbConnection.connect();
    
        try (PreparedStatement updateStatement = dbConnection.getJdbcConnection().prepareStatement(sql)) {
            updateStatement.setString(1, nom);
            updateStatement.setFloat(2, poidsMoyen);
            updateStatement.setInt(3, calories);
            updateStatement.setFloat(4, vitaminesC);
            updateStatement.setInt(5, typeId);
            updateStatement.setInt(6, couleurId);
            updateStatement.setInt(7, id);

            boolean state = updateStatement.executeUpdate() > 0;
    
            return state;
        } 
        catch(Exception ex){
            return false;
        }
    }



    // Delete
    public boolean deleteAliment(int id) throws SQLException {
        String sql = "DELETE FROM aliments WHERE id = ?";
        dbConnection.connect();

        PreparedStatement statement = dbConnection.getJdbcConnection().prepareStatement(sql);
        statement.setInt(1, id);

        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        dbConnection.disconnect();
        return rowDeleted;
    }

    // Méthode pour trouver un aliment par son ID
    public ResultSet findById(int id) throws SQLException {
        String sql = "SELECT * FROM aliments WHERE id = ?";
        dbConnection.connect();
        PreparedStatement statement = dbConnection.getJdbcConnection().prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        // Note: La gestion de la fermeture du ResultSet et de la déconnexion devrait être effectuée par l'appelant
        return resultSet;
    }

    public ResultSet findByName(String name) throws SQLException {
        String sql = "SELECT * FROM aliments WHERE nom = ?";
        dbConnection.connect();
        PreparedStatement statement = dbConnection.getJdbcConnection().prepareStatement(sql);
        statement.setString(1, name);
        return statement.executeQuery();
    }
}
