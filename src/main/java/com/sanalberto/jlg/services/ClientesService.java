package com.sanalberto.jlg.services;


import com.sanalberto.jlg.repositories.BorrarClienteDB;
import com.sanalberto.jlg.repositories.ConsultarClienteDB;


public class ClientesService {
    public static int buscarCliente(String nombre) {
        ConsultarClienteDB consultarClienteDB = new ConsultarClienteDB();

        return consultarClienteDB.seleccionarCliente(nombre);

    }
    public static boolean eliminarCliente(int id_cliente){
        BorrarClienteDB borrarClienteDB = new BorrarClienteDB();
        return (borrarClienteDB.eliminarCliente(id_cliente));


    }
}
