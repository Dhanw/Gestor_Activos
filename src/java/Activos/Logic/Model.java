/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Activos.Logic;

import Activos.Data.Dao;
import java.sql.SQLException;
import java.util.List;

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

    public Usuario getUsuario(String cuenta, String pass) throws SQLException {
        return dao.getUsuario(cuenta, pass);
    }
    
    public void addUsuario(Usuario usuario) throws Exception{
        dao.addUsuario(usuario);
    }
    
    public Funcionario getFuncionario(int id) throws SQLException{
        return dao.getFuncionario(id);
    }
    
    public void addFuncionario(Funcionario funcionario) throws Exception{
        dao.addFuncionario(funcionario);
    }
    
    public Dependencia getDependencia(int id) throws SQLException{
        return dao.getDependencia(id);
    }
    
    public void addDependencia(Dependencia dependencia) throws Exception{
        dao.addDependencia(dependencia);
    }
    
    public Bien getBien(int id) throws SQLException{
        return dao.getBien(id);
    }
    
    public List<Bien> getBienesPorSolicitud(Solicitud solicitud) throws SQLException{
        return dao.getBienes(solicitud);
    }
    
    public void addBien(Bien bien) throws Exception{
        dao.addBien(bien);
    }
    
}
