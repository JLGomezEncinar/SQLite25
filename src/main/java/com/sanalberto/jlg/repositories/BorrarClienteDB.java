package com.sanalberto.jlg.repositories;

import com.sanalberto.jlg.dataBase.ConexionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BorrarClienteDB {
    public static boolean eliminarCliente(int id_cliente){
        boolean clienteEliminado = false;
        String eliminarClienteString = "DELETE FROM clientes WHERE id_cliente = ?;";
        PreparedStatement seleccionarCliente = null;
        ConexionDB conexionDB = new ConexionDB();
        try (Connection conDB = conexionDB.connect()) {
            seleccionarCliente = conDB.prepareStatement(eliminarClienteString);
            seleccionarCliente.setInt(1, id_cliente);
            seleccionarCliente.executeUpdate();
            clienteEliminado = true;



        } catch (SQLException e) {
            clienteEliminado = false;
        }
        return clienteEliminado;
    }
}
