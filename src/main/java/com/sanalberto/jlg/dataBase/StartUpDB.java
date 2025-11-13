package com.sanalberto.jlg.dataBase;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.Statement;

import static java.lang.IO.println;

public class StartUpDB {



        public static void hacerCargaInicialDB() {


                ConexionDB conexionDB = new ConexionDB();
            try (Connection conDB = conexionDB.connect()){
                Statement statement = conDB.createStatement();

                // path to our SQL Script file
                String filePath = "src/main/resources/concesionario.sql";
                BufferedReader br = new BufferedReader(new FileReader(filePath));

                // String Builder to build the query line by line.
                StringBuilder query = new StringBuilder();
                String line;

                while((line = br.readLine()) != null) {

                    if(line.trim().startsWith("-- ")) {
                        continue;
                    }

                    // Append the line into the query string and add a space after that
                    query.append(line).append(" ");

                    if(line.trim().endsWith(";")) {
                        // Execute the Query
                        statement.execute(query.toString().trim());
                        // Empty the Query string to add new query from the file
                        query = new StringBuilder();
                    }
                }

                println("Se ha creado la base de datos correctamente");


            }
            catch (Exception e) {
                // Error handling Statements
                System.out.println(e);
            }
        }
    }