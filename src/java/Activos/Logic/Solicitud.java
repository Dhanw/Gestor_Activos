/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Activos.Logic;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jorac
 */
public class Solicitud {

    //Tipos de solicitudes
    public static final int INDF = 0;
    public static final int COMPRA = 1;
    public static final int DONACION = 2;
    public static final int PRODUCCION = 3;

    //Estados
    public static final int RECIBIDA = 1;
    public static final int POR_VERIFICAR = 2;
    public static final int RECHAZADA = 3;
    public static final int ESPERA_ROTULACION = 4;
    public static final int PROCESADA = 5;

    int ID;
    String comprobante;
    Date fecha;
    int tipo;
    int estado;
    Funcionario registrador;
    Dependencia dependencia;
    int cantidad;
    double total;
    List<Bien> bienes;

    public Solicitud() {
        bienes = new ArrayList<>();
    }

    public Solicitud(String comprobante, Date fecha, int tipo, int estado, Funcionario registrador, Dependencia dependencia, int cantidad, double total, List<Bien> bienes) {
        this.comprobante = comprobante;
        this.fecha = fecha;
        this.tipo = tipo;
        this.estado = estado;
        this.registrador = registrador;
        this.dependencia = dependencia;
        this.cantidad = cantidad;
        this.total = total;
        this.bienes = bienes;
    }

    public Solicitud(String comprobante, Date fecha, int tipo, Funcionario registrador, Dependencia dependencia, List<Bien> bienes) {
        this.comprobante = comprobante;
        this.fecha = fecha;
        this.tipo = tipo;
        this.registrador = registrador;
        this.dependencia = dependencia;
        this.bienes = bienes;
        this.cantidad = bienes.size();
        for (Bien b : bienes) {
            total = total + b.getPrecio();
        }
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getComprobante() {
        return comprobante;
    }

    public void setComprobante(String comprobante) {
        this.comprobante = comprobante;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Funcionario getRegistrador() {
        return registrador;
    }

    public void setRegistrador(Funcionario registrador) {
        this.registrador = registrador;
    }

    public Dependencia getDependencia() {
        return dependencia;
    }

    public void setDependencia(Dependencia dependencia) {
        this.dependencia = dependencia;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<Bien> getBienes() {
        return bienes;
    }

    public void setBienes(List<Bien> bienes) {
        this.bienes = bienes;
        this.cantidad = bienes.size();
        for (Bien b : bienes) {
            total = total + b.getPrecio();
        }
    }

    public String getDescripcionTipo() {
        switch (tipo) {
            case COMPRA:
                return "Compra";
            case DONACION:
                return "Donacion";
            case PRODUCCION:
                return "Produccion";
        }

        return "Indefinido";
    }

    public String getDescripcionEstado() {
        switch (estado) {
            case RECIBIDA:
                return "Recibida";
            case POR_VERIFICAR:
                return "Por verificar";
            case RECHAZADA:
                return "rechazada";
            case ESPERA_ROTULACION:
                return "En espera de rotulacion";
            case PROCESADA:
                return "Procesada";
        }
        return "Indefinda";
    }

}
