package com.sanalberto.jlg.services;

import com.sanalberto.jlg.models.Venta;
import com.sanalberto.jlg.repositories.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

public class VentasService {
    public static StringBuilder leerVentasCSV(String rutaArchivo) {
        InsertarVentasDB insertarVentasDB = new InsertarVentasDB();
        StringBuilder respuesta = new StringBuilder();

        String linea;


        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {


            while ((linea = br.readLine()) != null) {
                // Divide la línea en columnas separadas por comas
                String[] columnas = linea.split(",");

                // Ejemplo: imprimir los valores

                Venta ventaAux = new Venta();
                ventaAux.setIdVenta(Integer.parseInt(columnas[0]));
                ventaAux.setIdCoche(Integer.parseInt(columnas[1]));
                ventaAux.setIdCliente(Integer.parseInt(columnas[2]));
                ventaAux.setFechaVenta(Date.valueOf(columnas[3]));
                ventaAux.setPrecioVenta(Double.parseDouble(columnas[4]));
                if (insertarVentasDB.insertVenta(ventaAux)) {
                    respuesta.append("Venta " + ventaAux.getIdVenta() + " insertada correctamente\n");
                } else {
                    respuesta.append("Venta " + ventaAux.getIdVenta() + " NO insertada correctamente\n");
                }

            }


        } catch (IOException e) {
            respuesta.append("Error al leer la ruta Archivo " + rutaArchivo + "\n");
        }
        return respuesta;
    }

    public static boolean comprobarVenta(Integer idCoche, Integer idCliente, Double precioVenta) {
        InsertNewVentaDB insertNewVentaDB = new InsertNewVentaDB();
        Date fechaActual = Date.valueOf(LocalDate.now());
        return insertNewVentaDB.insertarNuevaVenta(idCoche, idCliente, fechaActual, precioVenta);
    }

    public static boolean eliminarVenta(Integer idVenta) {
        EliminarVentaDB eliminarVentaDB = new EliminarVentaDB();
        return eliminarVentaDB.eliminarVenta(idVenta);
    }

    public static Venta consultarVenta(Integer idVenta) {
        SelectVentasDB selectVentasDB = new SelectVentasDB();
        return selectVentasDB.selectVenta(idVenta);
    }

    public static StringBuilder anularVenta(Integer idVenta) {
        StringBuilder respuesta = new StringBuilder();
        EliminarVentaDB eliminarVentaDB = new EliminarVentaDB();
        SelectVentasDB selectVentasDB = new SelectVentasDB();
        ActualizarDisponibilidadCocheDB actualizarDisponibilidadCocheDB = new ActualizarDisponibilidadCocheDB();
        Venta venta = null;
        venta = selectVentasDB.selectVenta(idVenta);
        if (venta == null) {
            respuesta.append("Venta no encontrada");
        } else {
            respuesta.append("Venta " + venta.getIdVenta() + " encontrada\n");
            if (!eliminarVentaDB.eliminarVenta(idVenta)) {
                respuesta.append("No se ha podido eliminar la venta " + venta.getIdVenta());
            } else {
                respuesta.append("Se ha eliminado la venta " + venta.getIdVenta() + "\n");
                if (!actualizarDisponibilidadCocheDB.updateDisponibilidad(venta.getIdCoche())) {
                    respuesta.append("No se ha podido cambiar el estado del coche a disponible");
                } else {
                    respuesta.append("Se ha cambiado el estado del coche a disponible");
                }

            }
        }


        return respuesta;
}
}
