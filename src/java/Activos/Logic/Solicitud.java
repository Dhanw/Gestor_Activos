/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Activos.Logic;

import java.sql.Date;
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
    String codigo_comprobante;
    Date fecha_adquision;
    int tipo_adquisicion;
    int estado;
    Usuario registrador;

    //Atributos que no estan en la BD
    List<Bien> bienes;
    double total;

    public Solicitud() {

    }

    public Solicitud(String codigo_comprobante, Date fecha_adquision, int tipo_adquisicion, int estado, Usuario registrador, List<Bien> bienes, double total) {
        this.codigo_comprobante = codigo_comprobante;
        this.fecha_adquision = fecha_adquision;
        this.tipo_adquisicion = tipo_adquisicion;
        this.estado = estado;
        this.registrador = registrador;
        this.bienes = bienes;
        this.total = total;

        for (Bien _bien : this.bienes) {
            _bien.setSolicitud(this);
            this.total += _bien.getPrecio_cu();
        }
    }

    public Solicitud(String codigo_comprobante, Date fecha_adquision, int tipo_adquisicion, int estado, Usuario registrador) {
        this.codigo_comprobante = codigo_comprobante;
        this.fecha_adquision = fecha_adquision;
        this.tipo_adquisicion = tipo_adquisicion;
        this.estado = estado;
        this.registrador = registrador;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getCodigo_comprobante() {
        return codigo_comprobante;
    }

    public void setCodigo_comprobante(String codigo_comprobante) {
        this.codigo_comprobante = codigo_comprobante;
    }

    public Date getFecha_adquision() {
        return fecha_adquision;
    }

    public void setFecha_adquision(Date fecha_adquision) {
        this.fecha_adquision = fecha_adquision;
    }

    public int getTipo_adquisicion() {
        return tipo_adquisicion;
    }

    public void setTipo_adquisicion(int tipo_adquisicion) {
        this.tipo_adquisicion = tipo_adquisicion;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Usuario getRegistrador() {
        return registrador;
    }

    public void setRegistrador(Usuario registrador) {
        this.registrador = registrador;
    }

    public List<Bien> getBienes() {
        return bienes;
    }

    public void setBienes(List<Bien> bienes) {
        this.bienes = bienes;
        for (Bien _bien : this.bienes) {
            _bien.setSolicitud(this);
            this.total += _bien.getPrecio_cu();
        }
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

}
