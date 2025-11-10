package com.sanalberto.jlg.cmd;

import com.sanalberto.jlg.models.Cliente;
import com.sanalberto.jlg.models.Venta;
import com.sanalberto.jlg.repositories.InsertarClientesDB;
import com.sanalberto.jlg.repositories.InsertarCochesDB;
import com.sanalberto.jlg.repositories.InsertarVentasDB;
import com.sanalberto.jlg.services.ClientesService;
import com.sanalberto.jlg.services.CochesService;
import com.sanalberto.jlg.services.VentasService;

import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.IO.println;


public class MenuInicial {
    private Scanner scanner = new Scanner(System.in);
    private boolean salir = false;

    CochesService cochesService = new CochesService();
    ClientesService clientesService = new ClientesService();
    VentasService ventasService = new VentasService();
    ArrayList<Cliente> clientes = new ArrayList<>();
    ArrayList<Venta> ventas = new ArrayList<>();
    InsertarCochesDB insertarCochesDB = new InsertarCochesDB();
    InsertarClientesDB insertarClientesDB = new InsertarClientesDB();
    InsertarVentasDB  insertarVentasDB = new InsertarVentasDB();
    MenuVentas menuVentas = new MenuVentas();


    public void muestraMenu() {

        String opcion;
        do {
            System.out.println("Elige una opcion:");
            System.out.println("1. Insertar coches en BD");
            System.out.println("2. Insertar clientes en BD");
            System.out.println("3. Insertar ventas en BD");
            System.out.println("4. Menú ventas");
            System.out.println("0. Salir");
            opcion = this.pideOpcion();
            this.procesaOpcion(opcion);
        } while (!salir);
    }

    private void procesaOpcion(String opcion) {
        switch (opcion) {
            case "0" -> salir = true;
            case "1" -> {
                println(cochesService.leerCochesCSV("src/main/resources/coches.csv"));



            }
            case "2" -> {
                println(clientesService.leerClientesCSV("src/main/resources/clientes.csv"));


            }
            case "3" -> {
                println(ventasService.leerVentasCSV("src/main/resources/ventas.csv"));


            }
            case "4" -> {
                menuVentas.muestraMenu();
            }
            default -> System.out.println("Opcion no valida");
        }
    }

    private String pideOpcion() {
        return this.scanner.nextLine();
    }
}
