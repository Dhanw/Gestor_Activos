/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Activos.Logic;

import Activos.Data.Dao;
import java.sql.SQLException;

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

}
