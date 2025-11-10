package com.sanalberto.jlg.repositories;

import com.sanalberto.jlg.dataBase.ConexionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConsultarDisponibilidadCocheDB {
    public static boolean mostrarDisponibilidad(Integer idCoche) {
        Boolean cocheDisponible = false;
        String mostrarDisponibilidadString = "SELECT * from coches where id_coche = ?";
        PreparedStatement mostrarCoches = null;
        ConexionDB conexionDB = new ConexionDB();
        try (Connection conDB = conexionDB.connect()) {
            mostrarCoches = conDB.prepareStatement(mostrarDisponibilidadString);
            mostrarCoches.setInt(1, idCoche);
            ResultSet resultSet = mostrarCoches.executeQuery();
            if (resultSet.next()) {
                cocheDisponible = resultSet.getBoolean("disponible");
            }


        } catch (SQLException e) {
            cocheDisponible = false;
        }
        return cocheDisponible;
    }
}
