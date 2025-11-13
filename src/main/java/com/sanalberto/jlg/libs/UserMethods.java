package com.sanalberto.jlg.libs;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import static java.lang.IO.println;

public class UserMethods {
    public static int leerEntero(String mensaje, Scanner scanner){
        int entero = 0;
        boolean valido = false;
        while (!valido){
            println(mensaje);
            try {
                entero = Integer.parseInt(scanner.nextLine());
                valido = true;
            } catch (NumberFormatException e) {
                println("El valor introducido no es válido");
            }
        }
        return entero;
    }
    public static String borrarDB(String mensaje, Scanner scanner){
        String respuesta = "";
        println(mensaje);
        Path rutaFichero = Path.of(scanner.nextLine());
        if (!Files.exists(rutaFichero)) {
            respuesta = "No se ha encontrado ninguna base de datos en esa ruta";
        } else {
            try {
                Files.delete(rutaFichero);
                respuesta = "Base de datos borrada exitosamente";
            } catch (IOException e) {
                respuesta = "No se ha podido eliminar la base de datos";
            }
        }
        return respuesta;
        }

    }

