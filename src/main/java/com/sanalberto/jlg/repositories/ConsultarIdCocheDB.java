package com.sanalberto.jlg.repositories;

import com.sanalberto.jlg.dataBase.ConexionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConsultarIdCocheDB {
    public static int mostrarCoches(String modelo) {
        int idCoche = 0;
        String mostrarCochesString = "SELECT * from coches where modelo = ?";
        PreparedStatement mostrarCoches = null;
        ConexionDB conexionDB = new ConexionDB();
        try (Connection conDB = conexionDB.connect()) {
            mostrarCoches = conDB.prepareStatement(mostrarCochesString);
            mostrarCoches.setString(1, modelo);
            ResultSet resultSet = mostrarCoches.executeQuery();
            if (resultSet.next()) {
                idCoche = resultSet.getInt("id_coche");
            }


        } catch (SQLException e) {
            idCoche = 0;
        }
        return idCoche;
    }
}
