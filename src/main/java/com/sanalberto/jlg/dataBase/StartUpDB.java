package com.sanalberto.jlg.dataBase;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static java.lang.IO.println;

public class StartUpDB {


    public static String hacerCargaInicialDB() {

        String respuesta = "";
        if (!Files.exists(Path.of("src/main/resources/concesionario.db"))) {
            ConexionDB conexionDB = new ConexionDB();
            try (Connection conDB = conexionDB.connect()) {
                Statement statement = conDB.createStatement();

                // Indicamos la ruta al archivo SQL y empezamos a leer el archivo
                String filePath = "src/main/resources/concesionario.sql";
                BufferedReader br = new BufferedReader(new FileReader(filePath));

                // En el StringBuilder iremos añadiendo las sentencias SQL
                StringBuilder query = new StringBuilder();
                String line;

                while ((line = br.readLine()) != null) {
                    //Ignoramos las líneas de comentarios
                    if (line.trim().startsWith("-- ")) {
                        continue;
                    }

                    // Añadimos las líneas que contienen sentencias SQL
                    query.append(line).append(" ");

                    if (line.trim().endsWith(";")) {
                        // Ejecutamos el StringBuilder cuando encontramos una línea que acabe en ;
                        statement.execute(query.toString().trim());
                        // Vaciamos el StringBuilder para la siguiente sentencia SQL
                        query = new StringBuilder();
                    }
                }

                respuesta = "Se ha creado la base de datos correctamente";


            } catch (SQLException | FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        } else {
            respuesta = "Ya existe la base de datos";
        }
        return respuesta;
    }
}