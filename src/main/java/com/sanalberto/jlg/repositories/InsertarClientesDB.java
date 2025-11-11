package com.sanalberto.jlg.repositories;

import com.sanalberto.jlg.dataBase.ConexionDB;
import com.sanalberto.jlg.DAO.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertarClientesDB {
    public static boolean insertCliente(Cliente cliente) {
        Boolean respuesta = false;
        String insertClienteString = "INSERT INTO clientes VALUES (?,?,?,?)";
        PreparedStatement insertCliente = null;
        ConexionDB conexionDB = new ConexionDB();
        try (Connection conDB = conexionDB.connect()){
            insertCliente = conDB.prepareStatement(insertClienteString);
            insertCliente.setInt(1,cliente.getIdCliente());
            insertCliente.setString(2,cliente.getNombreCliente());
            insertCliente.setInt(3,cliente.getTelefonoCliente());
            insertCliente.setString(4,cliente.getEmailCliente());
            insertCliente.executeUpdate();
            respuesta = true;


        } catch (SQLException e) {
            respuesta = false;
        }
        return respuesta;
    }
}
