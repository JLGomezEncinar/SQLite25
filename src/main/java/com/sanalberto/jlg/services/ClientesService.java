package com.sanalberto.jlg.services;

import com.sanalberto.jlg.DAO.Cliente;
import com.sanalberto.jlg.repositories.ConsultarClienteDB;
import com.sanalberto.jlg.repositories.InsertarClientesDB;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import static java.lang.IO.println;

public class ClientesService {
    public static StringBuilder leerClientesCSV(String mensaje) {
        Scanner scanner = new Scanner(System.in);
        InsertarClientesDB insertarClientesDB = new InsertarClientesDB();
        StringBuilder respuesta = new StringBuilder();
        println(mensaje);
        String rutaArchivo = scanner.nextLine();
        String linea;



        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {


            while ((linea = br.readLine()) != null) {
                // Divide la línea en columnas separadas por comas
                String[] columnas = linea.split(",");

                // Ejemplo: imprimir los valores

                Cliente clienteAux = new Cliente();
                clienteAux.setIdCliente(Integer.parseInt(columnas[0]));
                clienteAux.setNombreCliente(columnas[1]);
                clienteAux.setTelefonoCliente(Integer.parseInt(columnas[2]));
                clienteAux.setEmailCliente(columnas[3]);
                if (insertarClientesDB.insertCliente(clienteAux)){
                    respuesta.append("Cliente " + clienteAux.getIdCliente() + " insertado correctamente\n");
                } else {
                    respuesta.append("Cliente " + clienteAux.getIdCliente() + " NO insertado correctamente\n");
                }
            }


        } catch (IOException e) {
            respuesta.append("Error al leer la ruta Archivo " + rutaArchivo + "\n");
        }
        return respuesta;
    }
    public static int buscarCliente(String nombre) {
        ConsultarClienteDB consultarClienteDB = new ConsultarClienteDB();

        return consultarClienteDB.seleccionarCliente(nombre);

    }
}
