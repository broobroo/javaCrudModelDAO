package com.example.dao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.io.IOException;


public interface IGenericCRUD {
    void handleGet(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException ;
    void handlePost(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException;
    void handlePut(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException;
    void handleDelete(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException;

    void handleFindById(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException;
    void handleFindByName(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException;
}
