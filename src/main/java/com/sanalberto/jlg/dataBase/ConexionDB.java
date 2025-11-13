package com.sanalberto.jlg.dataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static java.lang.IO.println;

public class ConexionDB {
    static String url = "jdbc:sqlite:src/main/resources/concesionario.db";

    public static Connection connect() {
        Connection conexion = null;
        try {
            conexion = DriverManager.getConnection(url);

        } catch (SQLException e) {
            println("Conexión mal");
        }
        return conexion;

    }
}
