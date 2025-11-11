package com.sanalberto.jlg.cmd;

import com.sanalberto.jlg.DTO.CocheDTO;
import com.sanalberto.jlg.libs.UserMethods;
import com.sanalberto.jlg.services.ClientesService;
import com.sanalberto.jlg.services.CochesService;
import com.sanalberto.jlg.services.VentasService;

import java.util.Scanner;

import static java.lang.IO.println;

public class MenuVentas {
    private Scanner scanner = new Scanner(System.in);
    Boolean salir = false;
    UserMethods userMethods = new UserMethods();
    CocheDTO cocheDTO = new CocheDTO();
    Boolean ventaInsertada = false;

    public void muestraMenu() {
        String opcion;

        do {
            System.out.println("Elige una opcion:");
            System.out.println("1. Introducir nombre del cliente");
            System.out.println("2. Introducir modelo del coche");
            System.out.println("3. Insertar nueva venta en BD");
            System.out.println("4. Cambiar disponibilidad coche en BD");
            System.out.println("5. Eliminar una venta en BD");
            System.out.println("0. Salir");
            opcion = this.pideOpcion();
            this.procesaOpcion(opcion);
        } while (!salir);
    }

    private void procesaOpcion(String opcion) {
        switch (opcion) {
            case "0" -> salir = true;
            case "1" -> {
                ClientesService clientesService = new ClientesService();
                println("Ingrese el nombre del cliente:");
                opcion = this.pideOpcion();
                cocheDTO.setIdCliente(clientesService.buscarCliente(opcion));
                if (cocheDTO.getIdCliente() == 0) {
                    println("El cliente NO existe en la base de datos");
                } else {
                    println(cocheDTO.getIdCliente());
                }

            }
            case "2" -> {
                CochesService cochesService = new CochesService();
                println("Escribe el modelo del coche: ");
                opcion = this.pideOpcion();
                int idCoche = cochesService.devolverCoche(opcion);
                if (idCoche == 0) {
                    println("No hay ningún modelo del tipo elegido disponible");
                } else if (!cochesService.devolverDisponibilidad(idCoche)) {
                    println("El coche no se encuentra disponible");
                } else {
                    cocheDTO.setIdCoche(idCoche);
                    println(cocheDTO.getIdCoche());
                }


            }
            case "3" -> {
                VentasService ventasService = new VentasService();
                cocheDTO.setPrecio(userMethods.leerDouble("Introduce el precio de venta del coche", scanner));
                println(cocheDTO.getPrecio());

                if (ventasService.comprobarVenta(cocheDTO.getIdCoche(), cocheDTO.getIdCliente(), cocheDTO.getPrecio())) {
                    println("La venta se ha insertado correctamente");
                    ventaInsertada = true;

                } else {
                    println("No se ha podido insertar la venta");
                }


            }
            case "4" -> {
                CochesService cochesService = new CochesService();
                if (!ventaInsertada) {
                    println("No hay ningún cambio de estado para los coches");
                } else {
                    cochesService.updateDisponibilidad(cocheDTO.getIdCoche());
                    println("Se ha cambiado el estado del coche " + cocheDTO.getIdCoche() + " a no disponible");
                    ventaInsertada = false;
                }
            }
            case "5" -> {
                VentasService ventasService = new VentasService();
                int idVenta = userMethods.leerEntero("Introduce el id de venta:", scanner);
                println(ventasService.anularVenta(idVenta));


            }

            default -> System.out.println("Opcion no valida");
        }
    }

    private String pideOpcion() {
        return this.scanner.nextLine();
    }
}

