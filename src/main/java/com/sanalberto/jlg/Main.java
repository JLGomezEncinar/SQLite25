package com.sanalberto.jlg;

import com.sanalberto.jlg.cmd.MenuInicial;
import com.sanalberto.jlg.dataBase.ConexionDB;
import com.sanalberto.jlg.dataBase.ConexionSGDB;
import com.sanalberto.jlg.dataBase.EstructuraDB;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        MenuInicial menuInicial = new MenuInicial();
        menuInicial.muestraMenu();
    }
}
