package com.sanalberto.jlg.dataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static java.lang.IO.println;

public class ConexionSGDB {
    private static final String URL = "jdbc:postgresql://localhost:5532/postgres";
    private static final String USUARIO = "postgres";
    private static final String PASSWORD = "admin";
    public static Connection connect() {
        Connection conexion = null;
        try {
            conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
            println("Conexión OK");
        } catch (SQLException e) {
            println("Conexión mal");
        }
        return conexion;

    }
}
