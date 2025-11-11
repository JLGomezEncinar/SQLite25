package com.sanalberto.jlg.repositories;

import com.sanalberto.jlg.dataBase.ConexionDB;
import com.sanalberto.jlg.DAO.Venta;

import java.sql.*;
import java.time.LocalDate;

public class SelectVentasDB {
    public static Venta selectVenta(Integer idVenta){
        Venta venta = new Venta();
        String selectVentaString = "SELECT * from ventas where id_venta = ?";
        PreparedStatement selectVenta = null;
        ConexionDB conexionDB = new ConexionDB();
        try (Connection conDB = conexionDB.connect()) {
            selectVenta = conDB.prepareStatement(selectVentaString);
            selectVenta.setInt(1, idVenta);
            ResultSet resultSet = selectVenta.executeQuery();
            if (resultSet.next()) {
                venta.setIdVenta(resultSet.getInt("id_venta"));
                venta.setIdCliente(resultSet.getInt("id_cliente"));
                venta.setFechaVenta(Date.valueOf(LocalDate.parse(resultSet.getString("fecha_venta"))));
                venta.setIdCoche(resultSet.getInt("id_coche"));
                venta.setPrecioVenta(resultSet.getDouble("precio_final"));
            } else {
                venta = null;
            }


        } catch (SQLException e) {
            venta = null;
        }
        return venta;
    }
    }

