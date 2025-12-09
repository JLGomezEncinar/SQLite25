package com.sanalberto.jlg.cmd;

import com.sanalberto.jlg.DTO.VentaDTO;
import com.sanalberto.jlg.dataBase.StartUpDB;
import com.sanalberto.jlg.libs.UserMethods;
import com.sanalberto.jlg.services.ClientesService;
import com.sanalberto.jlg.services.VentasService;

import java.sql.SQLException;
import java.util.Scanner;

import static java.lang.IO.println;


public class MenuInicial {
    private Scanner scanner = new Scanner(System.in);
    private boolean salir = false;


    public void muestraMenu() {

        String opcion;
        do {
            System.out.println("Elige una opcion:");
            System.out.println("1. Crear la base de datos ");
            System.out.println("2. Eliminar la base de datos");
            System.out.println("3. Obtener datos de venta");
            System.out.println("4. Borrar cliente");
            System.out.println("0. Salir");
            opcion = this.pideOpcion();
            this.procesaOpcion(opcion);
        } while (!salir);
    }

    private void procesaOpcion(String opcion) {
        switch (opcion) {
            case "0" -> salir = true;
            case "1" -> {
                StartUpDB startUpDB = new StartUpDB();
                println(startUpDB.hacerCargaInicialDB());


            }
            case "2" -> {
                UserMethods userMethods = new UserMethods();
                println(userMethods.borrarDB("Introduce la ruta de la base de datos a eliminar: ", scanner));
            }
            case "3" -> {
                VentasService ventasService = new VentasService();
                UserMethods userMethods = new UserMethods();
                int id_venta = userMethods.leerEntero("Introduce el id de venta", scanner);
                VentaDTO ventaDTO = ventasService.recuperarVentaDB(id_venta);
                // Si el id de venta no existe devuelve id = 0
                if (ventaDTO.getId_venta() == 0) {
                    println("No existe ninguna venta con ese id");
                } else {
                    //Imprimimos por pantalla los datos de la venta
                    println(ventaDTO.getId_venta() + " " + ventaDTO.getNombreCliente() + " " + ventaDTO.getModelo() + " " + ventaDTO.getFechaVenta() + " " + ventaDTO.getPrecioVenta());
                }


            }
            case "4" -> {
                ClientesService clientesService = new ClientesService();
                VentasService ventasService = new VentasService();
                println("Introduce el nombre del cliente a borrar: ");
                String nombre = scanner.nextLine();
                int id_cliente = 0;
                try {
                    id_cliente = clientesService.buscarCliente(nombre);
                } catch (SQLException e) {
                    println("Error al realizar la consulta");
                }
                if (id_cliente == 0) {
                    println("El cliente no existe en la base de datos");
                } else {
                    println("Cliente encontrado en la base de datos");
                    if (!clientesService.eliminarCliente(id_cliente)) {
                        println("No se ha podido borrar el cliente");
                    } else {
                        println("Cliente borrado de la base de datos");
                        if (!ventasService.eliminarVentaDB(id_cliente)) {
                            println("No se han podido borrar las ventas del cliente");
                        } else {
                            println("Ventas eliminadas correctamente");
                        }
                    }
                }


            }

            default -> System.out.println("Opcion no valida");
        }
    }

    private String pideOpcion() {
        return this.scanner.nextLine();
    }
}
