package com.sanalberto.jlg.repositories;

import com.sanalberto.jlg.dataBase.ConexionDB;
import com.sanalberto.jlg.models.Venta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertarVentasDB {
    public static boolean insertVenta(Venta venta) {
        Boolean respuesta = false;
        String insertVentaString = "INSERT INTO ventas VALUES (?,?,?,?,?)";
        PreparedStatement insertVenta = null;
        ConexionDB conexionDB = new ConexionDB();
        try (Connection conDB = conexionDB.connect()){
            insertVenta = conDB.prepareStatement(insertVentaString);
            insertVenta.setInt(1,venta.getIdVenta());
            insertVenta.setInt(2,venta.getIdCoche());
            insertVenta.setInt(3,venta.getIdCliente());
            insertVenta.setString(4,venta.getFechaVenta().toString());
            insertVenta.setDouble(5,venta.getPrecioVenta());
            insertVenta.executeUpdate();
            respuesta = true;


        } catch (SQLException e) {
            respuesta = false;
        }
        return respuesta;
    }
}
