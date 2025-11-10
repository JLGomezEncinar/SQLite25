package com.sanalberto.jlg.repositories;

import com.sanalberto.jlg.dataBase.ConexionDB;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertNewVentaDB {

    public static boolean insertarNuevaVenta(Integer idCoche, Integer idCliente, Date fechaActual,Double precioVenta) {
        Boolean ventaInsertada = false;
        String insertarNuevaVentaString = "INSERT INTO ventas (id_coche,id_cliente,fecha_venta,precio_final) VALUES (?,?,?,?)";
        PreparedStatement preparedStatement = null;
        ConexionDB conexionDB = new ConexionDB();
        try (Connection conDB = conexionDB.connect()) {
            preparedStatement = conDB.prepareStatement(insertarNuevaVentaString);
            preparedStatement.setInt(1, idCoche);
            preparedStatement.setInt(2, idCliente);
            preparedStatement.setString(3, fechaActual.toString());
            preparedStatement.setDouble(4, precioVenta);
            preparedStatement.executeUpdate();
            ventaInsertada = true;
        } catch (SQLException e) {
            ventaInsertada = false;
        }
        return ventaInsertada;
    }

}
