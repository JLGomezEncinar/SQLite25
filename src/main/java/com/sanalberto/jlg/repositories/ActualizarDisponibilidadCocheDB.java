package com.sanalberto.jlg.repositories;

import com.sanalberto.jlg.dataBase.ConexionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;



public class ActualizarDisponibilidadCocheDB {
    public static boolean updateDisponibilidad(Integer idCoche) {
        boolean disponibilidad = false;
        String actualizarDisponibilidadCocheString = "UPDATE coches set disponible = NOT disponible WHERE id_coche = ?;";
        PreparedStatement seleccionarCliente = null;
        ConexionDB conexionDB = new ConexionDB();
        try (Connection conDB = conexionDB.connect()) {
            seleccionarCliente = conDB.prepareStatement(actualizarDisponibilidadCocheString);
            seleccionarCliente.setInt(1, idCoche);
            seleccionarCliente.executeUpdate();
            disponibilidad = true;



        } catch (SQLException e) {
           disponibilidad = false;
        }
        return disponibilidad;
    }
}
