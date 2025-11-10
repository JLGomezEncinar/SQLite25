package com.sanalberto.jlg.repositories;

import com.sanalberto.jlg.dataBase.ConexionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static java.lang.IO.println;

public class ConsultarClienteDB {
    public static int seleccionarCliente(String nombre) {
        int id_cliente = 0;
        String seleccionarClienteString = "SELECT * FROM clientes WHERE nombre LIKE ?;";
        PreparedStatement seleccionarCliente = null;
        ConexionDB conexionDB = new ConexionDB();
        try (Connection conDB = conexionDB.connect()) {
            seleccionarCliente = conDB.prepareStatement(seleccionarClienteString);
            seleccionarCliente.setString(1, nombre + "%");
            ResultSet resultSet = seleccionarCliente.executeQuery();
            if (!resultSet.next()) {
                id_cliente = 0;
            } else {
                id_cliente = resultSet.getInt("id_cliente");
            }


        } catch (SQLException e) {
            println("Error al realizar la consulta");
        }
        return id_cliente;
    }
}
