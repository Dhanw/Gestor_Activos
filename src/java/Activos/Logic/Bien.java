/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Activos.Logic;

/**
 *
 * @author jorac
 */
public class Bien {

    int ID;
    String descripciones;
    String marca;
    String modelo;
    double precio_cu;
    int cantidad;
    Solicitud solicitud;

    public Bien() {
    }

    public Bien(String descripciones, String marca, String modelo, double precio_cu, int cantidad) {
        this.descripciones = descripciones;
        this.marca = marca;
        this.modelo = modelo;
        this.precio_cu = precio_cu;
        this.cantidad = cantidad;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getDescripciones() {
        return descripciones;
    }

    public void setDescripciones(String descripciones) {
        this.descripciones = descripciones;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public double getPrecio_cu() {
        return precio_cu;
    }

    public void setPrecio_cu(double precio_cu) {
        this.precio_cu = precio_cu;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Solicitud getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(Solicitud solicitud) {
        this.solicitud = solicitud;
    }

}
