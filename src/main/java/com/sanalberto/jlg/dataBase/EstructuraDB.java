package com.sanalberto.jlg.dataBase;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static java.lang.IO.println;

public class EstructuraDB {
    public static void createDB(String nameDB) {
        ConexionSGDB conexionSGDB = new ConexionSGDB();
        try (Connection conSGDB = conexionSGDB.connect()) {
            Statement crearDB = conSGDB.createStatement();
            crearDB.execute("CREATE DATABASE " + nameDB);
        } catch (SQLException e) {
            println("La sentencia SQL es incorrecta");
        }
    }

    public static void createTableCoches() {
        ConexionDB conexionDB = new ConexionDB();
        // Tipos cambiados: NUMBER -> INTEGER/REAL, VARCHAR2 -> TEXT
        // Autoincremental cambiado: IDENTITY (...) PRIMARY KEY -> INTEGER PRIMARY KEY AUTOINCREMENT
        String tablaCochesString = "CREATE TABLE coches (\n" +
                "    id_coche INTEGER \n" +
                "        PRIMARY KEY AUTOINCREMENT,\n" +
                "    marca TEXT NOT NULL,\n" +
                "    modelo TEXT NOT NULL,\n" +
                "    año INTEGER NOT NULL,\n" +
                "    bastidor TEXT UNIQUE NOT NULL,\n" +
                "    precio REAL NOT NULL, -- Uso REAL para decimales\n" +
                "    disponible INTEGER DEFAULT 1 NOT NULL, -- Uso INTEGER para simular BOOLEAN\n" +
                "    \n" +
                "    -- La restricción CHECK sigue siendo válida\n" +
                "    CONSTRAINT chk_disponible CHECK (disponible IN (0, 1))\n" +
                ")";
        try (Connection conDB = conexionDB.connect()) {
            Statement crearTable = conDB.createStatement();
            crearTable.executeUpdate(tablaCochesString);
            println("Tabla creada correctamente");
        } catch (SQLException e) {
            println("La sentencia SQL es incorrecta");
        }
    }

    public static void createTableClientes() {
        ConexionDB conexionDB = new ConexionDB();
        // Tipos cambiados: NUMBER -> INTEGER, VARCHAR2 -> TEXT
        // Autoincremental cambiado: IDENTITY (...) PRIMARY KEY -> INTEGER PRIMARY KEY AUTOINCREMENT
        // Nota: Para empezar en 101, tendrías que hacer un INSERT o ajustar el valor en el shell de SQLite,
        // pero la sintaxis AUTOINCREMENT no lo soporta directamente en el CREATE TABLE.
        String tablaClientesString = "CREATE TABLE clientes (\n" +
                "    id_cliente INTEGER \n" +
                "        PRIMARY KEY AUTOINCREMENT,\n" +
                "    nombre TEXT NOT NULL,\n" +
                "    telefono TEXT,\n" +
                "    email TEXT UNIQUE\n" +
                ")";
        try (Connection conDB = conexionDB.connect()) {
            Statement crearTable = conDB.createStatement();
            crearTable.executeUpdate(tablaClientesString);
            println("Tabla creada correctamente");
        } catch (SQLException e) {
            println("La sentencia SQL es incorrecta");
        }
    }

    public static void createTableVentas() {
        ConexionDB conexionDB = new ConexionDB();
        // Tipos cambiados: NUMBER -> INTEGER/REAL, DATE -> TEXT
        // Autoincremental cambiado: IDENTITY (...) PRIMARY KEY -> INTEGER PRIMARY KEY AUTOINCREMENT
        // Claves foráneas (FOREIGN KEY) son compatibles.
        String tablaVentasString = "CREATE TABLE ventas (\n" +
                "    id_venta INTEGER \n" +
                "        PRIMARY KEY AUTOINCREMENT,\n" +
                "    id_coche INTEGER UNIQUE NOT NULL,\n" +
                "    id_cliente INTEGER NOT NULL,\n" +
                "    fecha_venta TEXT NOT NULL, -- Usar formato 'YYYY-MM-DD'\n" +
                "    precio_final REAL NOT NULL,\n" +
                "    \n" +
                "    -- Definición de Claves Foráneas (compatibles en sintaxis)\n" +
                "    CONSTRAINT fk_ventas_coche \n" +
                "        FOREIGN KEY (id_coche) \n" +
                "        REFERENCES coches(id_coche),\n" +
                "        \n" +
                "    CONSTRAINT fk_ventas_cliente \n" +
                "        FOREIGN KEY (id_cliente) \n" +
                "        REFERENCES clientes(id_cliente)\n" +
                ")";
        try (Connection conDB = conexionDB.connect()) {
            Statement crearTable = conDB.createStatement();
            crearTable.executeUpdate(tablaVentasString);
            println("Tabla creada correctamente");
        } catch (SQLException e) {
            println("La sentencia SQL es incorrecta");
        }
    }
}
