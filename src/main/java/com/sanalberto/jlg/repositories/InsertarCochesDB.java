package com.sanalberto.jlg.repositories;

import com.sanalberto.jlg.dataBase.ConexionDB;
import com.sanalberto.jlg.DAO.Coche;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertarCochesDB {
    public static boolean insertCoche(Coche coche) {
        Boolean respuesta = false;
        String insertCocheString = "INSERT INTO coches VALUES (?,?,?,?,?,?,?)";
        PreparedStatement insertCoche = null;
        ConexionDB conexionDB = new ConexionDB();
        try (Connection conDB = conexionDB.connect()){
            insertCoche = conDB.prepareStatement(insertCocheString);
            insertCoche.setInt(1,coche.getIdCoche());
            insertCoche.setString(2,coche.getMarca());
            insertCoche.setString(3,coche.getModelo());
            insertCoche.setShort(4,coche.getAnio());
            insertCoche.setString(5,coche.getBastidor());
            insertCoche.setDouble(6,coche.getPrecio());
            insertCoche.setBoolean(7,coche.getDisponible());
            insertCoche.executeUpdate();
            respuesta = true;


        } catch (SQLException e) {
            respuesta = false;
        }
        return respuesta;
    }

}
