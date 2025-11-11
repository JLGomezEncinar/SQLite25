package com.sanalberto.jlg.libs;

import java.util.Scanner;

import static java.lang.IO.println;

public class UserMethods {
    public static double leerDouble(String mensaje, Scanner scanner){
        double decimal = 0;
        boolean valido = false;
        while (!valido){
            println(mensaje);
            try {
                decimal = Double.parseDouble(scanner.nextLine());
                valido = true;
            } catch (NumberFormatException e) {
                println("El valor introducido no es válido");
            }
        }
        return decimal;
    }
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
}
