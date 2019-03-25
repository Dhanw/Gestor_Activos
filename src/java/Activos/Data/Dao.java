/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Activos.Data;

import Activos.Logic.Funcionario;
import Activos.Logic.Usuario;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author jorac
 */
public class Dao {

    private  RelDatabase db;

    public Dao() {
        db = new RelDatabase();
    }

    
   private  Funcionario getFuncionarioH(ResultSet rs) throws SQLException {
        Funcionario func = null;
        if (rs.next()) {
            func = new Funcionario();
            func.setID(rs.getInt("ID"));
            func.setIdentificacion(rs.getString("identificacion"));
            func.setNombre(rs.getString("nombre"));
        }
        return func;
    }

    private  Usuario getUsuarioH(ResultSet rs) throws SQLException {
        Usuario user = null;
        if (rs.next()) {
            user = new Usuario();
            user.setID(rs.getInt("ID"));
            user.setCuenta(rs.getString("cuenta"));
            user.setPassword(rs.getString("password"));
            user.setRol(rs.getInt("rol"));
            user.setFuncionario(getFuncionarioH(rs));
        }
        return user;
    }

    
    
    // Funcionario ----------------------------------------------------------------------------
    public Funcionario getFuncionario(int id) throws SQLException {
        String sql = "select * from Funcionarios where id= %d";
        sql = String.format(sql, id);
        ResultSet rs;
        rs = db.executeQuery(sql);
        Funcionario func = null;
        func =this.getFuncionarioH(rs);
        return func;
    
    }

    public void addFuncionario(Funcionario func) throws Exception {
        String sql = "insert into Funcionarios(identificacion, nombre)"
                + "values('%s','%s')";

        sql = String.format(sql, func.getIdentificacion(), func.getNombre());

        int count = db.executeUpdate(sql);

        if (count == 0) {
            throw new Exception("Ocurrio un error al tratar de agregar el funcionario");
        }
    }

// Usuario ------------------------------------------------------------------------------
    public Usuario getUsuario(String cuenta, String pass) throws SQLException {

        String sql = "select * from usuarios where cuenta= '%s' AND password = '%s'";
        sql = String.format(sql, cuenta, pass);
        ResultSet rs = db.executeQuery(sql);
        Usuario user = this.getUsuarioH(rs);
        return user;
    }

    public void addUsuario(Usuario usuario) throws Exception {
        String sql = "insert into Usuarios(cuenta, password, rol, funcionario)"
                + "values('%s','%s',%d, %d)";

        sql = String.format(sql, usuario.getCuenta(), usuario.getPassword(),
                usuario.getRol(), usuario.getFuncionario().getID());
        int count = db.executeUpdate(sql);
        if (count == 0) {
            throw new Exception("Ocurrio un error al tratar de agregar el usuario");
        }

    }

}
