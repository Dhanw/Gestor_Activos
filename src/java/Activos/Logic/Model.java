/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Activos.Logic;

import Activos.Data.Dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jose model creado
 */
public class Model {

    private Dao dao;

    private static Model uniqueInstance;

    public static Model instance() {
        if (uniqueInstance == null) {
            uniqueInstance = new Model();
        }
        return uniqueInstance;
    }

    public Model() {
        dao = new Dao();
    }

    public Usuario getUsuario(String cuenta, String pass) throws SQLException, Exception {
        return dao.getUsuario(cuenta, pass);
    }

    public void addUsuario(Usuario usuario) throws Exception {
        dao.addUsuario(usuario);
    }

    public Funcionario getFuncionario(int id) throws SQLException, Exception {
        return dao.getFuncionario(id);
    }

    public void addFuncionario(Funcionario funcionario) throws Exception {
        dao.addFuncionario(funcionario);
    }

    public Dependencia getDependencia(int id) throws SQLException, Exception {
        return dao.getDependencia(id);
    }

    public Dependencia getDependencia_fromFuncionario(int id) throws SQLException, Exception {
        return dao.getDependencia_fromFuncionario(id);
    }

    public void addDependencia(Dependencia dependencia) throws Exception {
        dao.addDependencia(dependencia);
    }

    public Bien getBien(int id) throws SQLException, Exception {
        return dao.getBien(id);
    }

    public List<Bien> getBienesPorSolicitud(Solicitud solicitud) throws SQLException {
        return dao.getBienes(solicitud);
    }

    public void addBien(Bien bien) throws Exception {
        dao.addBien(bien);
    }

    public Solicitud getSolicitud(int id) throws SQLException, Exception {
        return dao.getSolicitud(id);
    }

    public List<Solicitud> solicitudesPorDependencia(Dependencia depe) throws SQLException, Exception {
        return dao.getSolicitudes(depe);
    }

    public void eliminarSolicitud(int solicitud) throws SQLException {
        dao.eliminarSolicitud(solicitud);
    }

    public List<Solicitud> SolitudesTipo(String tipo) throws SQLException, Exception {
        int nTipo = 0;
        if ("Compra".startsWith(tipo) || "compra".startsWith(tipo) || "COMPRA".startsWith(tipo)) {
            nTipo = 1;
        } else if ("DONACION".startsWith(tipo) || "Donacion".startsWith(tipo) || "donacion".startsWith(tipo)) {
            nTipo = 2;
        } else if ("Produccion".startsWith(tipo) || "produccion".startsWith(tipo) || "PRODUCCION".startsWith(tipo)) {
            nTipo = 3;
        }
        return dao.SolitudesTipo(nTipo);

    }

    public List<Solicitud> SolitudesEstado(String estado) throws SQLException, Exception {

        int estadoParseado = 0;
        if ("recibida".equals(estado) || "Recibida".equals(estado) || "RECIBIDA".equals(estado)) {
            estadoParseado = 1;
        }
        else if("verificar".equals(estado) || "Por verificar".equals(estado) || "por verificar".equals(estado)){
            estadoParseado = 2;
        }
        else if("rechazada".equals(estado) || "Rechazada".equals(estado) || "RECHAZADA".equals(estado)){
            estadoParseado = 3;
        }
        else if("Espera".equals(estado) || "Espera rotulacion".equals(estado) || "espera rotulacion".equals(estado)){
            estadoParseado = 4;
        }
        else if("Procesada".equals(estado) || "procesada".equals(estado) || "PROCESADA".equals(estado)){
            estadoParseado = 5;
        }
        return dao.SolitudesEstado(estadoParseado);
    }

    public List<Solicitud> SolicitudesPorComprobante(String comprobante) throws Exception {
        return dao.getByComprobante(comprobante);
    }

}
