package com.sanalberto.jlg.repositories;

import com.sanalberto.jlg.dataBase.ConexionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EliminarVentaDB {
    public static boolean eliminarVenta(Integer idVenta){
        boolean ventaEliminada = false;
        String EliminarVentaString = "DELETE FROM ventas WHERE id_venta = ?;";
        PreparedStatement seleccionarCliente = null;
        ConexionDB conexionDB = new ConexionDB();
        try (Connection conDB = conexionDB.connect()) {
            seleccionarCliente = conDB.prepareStatement(EliminarVentaString);
            seleccionarCliente.setInt(1, idVenta);
            seleccionarCliente.executeUpdate();
            ventaEliminada = true;            



        } catch (SQLException e) {
            ventaEliminada = false;
        }
        return ventaEliminada;
    }

}
