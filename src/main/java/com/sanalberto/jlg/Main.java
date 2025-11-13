package com.sanalberto.jlg;

import com.sanalberto.jlg.DTO.VentaDTO;
import com.sanalberto.jlg.cmd.MenuInicial;
import com.sanalberto.jlg.dataBase.ConexionDB;
import com.sanalberto.jlg.dataBase.ConexionSGDB;
import com.sanalberto.jlg.dataBase.EstructuraDB;
import com.sanalberto.jlg.repositories.RecuperarVentaDB;
import com.sanalberto.jlg.repositories.StartUpDB;

import java.util.Scanner;

import static java.lang.IO.println;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        RecuperarVentaDB recuperarVentaDB = new RecuperarVentaDB();
        Scanner sc = new Scanner(System.in);
        println("Introduce el id de venta:");
        int id = Integer.parseInt(sc.nextLine());
        VentaDTO ventaDTO = new VentaDTO();
        ventaDTO = recuperarVentaDB.recuperarVenta(id);
        println(ventaDTO.getId_venta()+ " "+ ventaDTO.getNombreCliente()+ " "+ ventaDTO.getModelo()+ " "+ ventaDTO.getFechaVenta()+ " "+ ventaDTO.getPrecioVenta());
        /*MenuInicial menuInicial = new MenuInicial();
        menuInicial.muestraMenu();*/
    }
}
