package com.sanalberto.jlg.services;

import com.sanalberto.jlg.models.Coche;
import com.sanalberto.jlg.repositories.ActualizarDisponibilidadCocheDB;
import com.sanalberto.jlg.repositories.ConsultarDisponibilidadCocheDB;
import com.sanalberto.jlg.repositories.ConsultarIdCocheDB;
import com.sanalberto.jlg.repositories.InsertarCochesDB;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CochesService {
    public static StringBuilder leerCochesCSV(String rutaArchivo) {
        InsertarCochesDB insertarCochesDB = new InsertarCochesDB();
        StringBuilder respuesta = new StringBuilder();

        String linea;


        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {


            while ((linea = br.readLine()) != null) {
                // Divide la línea en columnas separadas por comas
                String[] columnas = linea.split(",");

                // Ejemplo: imprimir los valores

                Coche cocheAux = new Coche();
                cocheAux.setIdCoche(Integer.parseInt(columnas[0]));
                cocheAux.setMarca(columnas[1]);
                cocheAux.setModelo(columnas[2]);
                cocheAux.setAnio(Short.valueOf(columnas[3]));
                cocheAux.setBastidor(columnas[4]);
                cocheAux.setPrecio(Double.parseDouble(columnas[5]));
                cocheAux.setDisponible(Boolean.valueOf(columnas[6]));
                if (insertarCochesDB.insertCoche(cocheAux)){
                    respuesta.append("Coche " + cocheAux.getIdCoche() + " insertado correctamente\n");
                } else {
                    respuesta.append("Coche " + cocheAux.getIdCoche() + " NO insertado correctamente\n");
                }
            }


        } catch (IOException e) {
            respuesta.append("Error al leer la ruta Archivo " + rutaArchivo + "\n");
        }
        return respuesta;
    }
    public static int devolverCoche(String modelo) {
        ConsultarIdCocheDB consultarIdCocheDB = new ConsultarIdCocheDB();
        return consultarIdCocheDB.mostrarCoches(modelo);
    }
    public static boolean devolverDisponibilidad(Integer idCoche) {
        ConsultarDisponibilidadCocheDB consultarDisponibilidadCocheDB = new ConsultarDisponibilidadCocheDB();
        return consultarDisponibilidadCocheDB.mostrarDisponibilidad(idCoche);
    }
    public static boolean updateDisponibilidad(Integer idCoche) {
        ActualizarDisponibilidadCocheDB actualizarDisponibilidadCocheDB = new ActualizarDisponibilidadCocheDB();
        return actualizarDisponibilidadCocheDB.updateDisponibilidad(idCoche);
    }


}
