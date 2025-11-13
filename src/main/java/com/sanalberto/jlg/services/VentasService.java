package com.sanalberto.jlg.services;

import com.sanalberto.jlg.DTO.VentaDTO;

import com.sanalberto.jlg.repositories.*;

public class VentasService {

    public static VentaDTO recuperarVentaDB(int id_venta) {
        RecuperarVentaDB recuperarVentaDB = new RecuperarVentaDB();
        return recuperarVentaDB.recuperarVenta(id_venta);
}
public static boolean eliminarVentaDB(int id_cliente){
        EliminarVentaDB eliminarVentaDB = new EliminarVentaDB();
        return eliminarVentaDB.eliminarVenta(id_cliente);
}
}
