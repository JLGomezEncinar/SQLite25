package com.sanalberto.jlg.repositories;

import com.sanalberto.jlg.DTO.VentaDTO;
import com.sanalberto.jlg.dataBase.ConexionDB;

import java.sql.*;

public class RecuperarVentaDB {
    public static VentaDTO recuperarVenta(int id_venta) {
        VentaDTO ventaDTO = new VentaDTO();
        //Sentencia SQL que retorna los datos de las 3 tablas que coincidan con la condición introducida
        String recuperarVentaString = "select * from ventas inner join coches on ventas.id_coche= coches.id_coche inner join clientes on ventas.id_cliente = clientes.id_cliente where id_venta = ?";
        PreparedStatement mostrarCoches = null;
        ConexionDB conexionDB = new ConexionDB();
        try (Connection conDB = conexionDB.connect()) {
            mostrarCoches = conDB.prepareStatement(recuperarVentaString);
            mostrarCoches.setInt(1, id_venta);
            ResultSet resultSet = mostrarCoches.executeQuery();
            if (resultSet.next()) {
                ventaDTO.setId_venta(resultSet.getInt("id_venta"));
                ventaDTO.setNombreCliente(resultSet.getString("nombre"));
                ventaDTO.setModelo(resultSet.getString("modelo"));
                // Convertimos la fecha por la forma en la que SQLite guarda los datos de fecha
                ventaDTO.setFechaVenta(Date.valueOf(resultSet.getString("fecha_venta")));
                ventaDTO.setPrecioVenta(resultSet.getDouble("precio_final"));
            }


        } catch (SQLException e) {
            ventaDTO = null;
        }
        return ventaDTO;
    }
}
