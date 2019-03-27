/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Activos.Data;

import Activos.Logic.Bien;
import Activos.Logic.Dependencia;
import Activos.Logic.Funcionario;
import Activos.Logic.Solicitud;
import Activos.Logic.Usuario;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jorac
 */
public class Dao {

    private RelDatabase db;

    public Dao() {
        db = new RelDatabase();
    }

    //--------------------------HELPERS--------------------------------------
    private Funcionario getFuncionarioH(ResultSet rs) throws SQLException {
        Funcionario func = new Funcionario();
        if (rs.next()) {

            func.setID(rs.getInt("ID"));
            func.setIdentificacion(rs.getString("identificacion"));
            func.setNombre(rs.getString("nombre"));
        }
        return func;
    }

    private Usuario getUsuarioH(ResultSet rs) throws SQLException {
        Usuario user = new Usuario();
        if (rs.next()) {

            user.setID(rs.getInt("ID"));
            user.setCuenta(rs.getString("cuenta"));
            user.setPassword(rs.getString("password"));
            user.setRol(rs.getInt("rol"));
            user.setFuncionario(getFuncionarioH(rs));
        }
        return user;
    }

    private Dependencia getDependenciaH(ResultSet rs) throws SQLException {
        Dependencia dependencia = new Dependencia();
        if (rs.next()) {
            dependencia.setID(rs.getInt("ID"));
            dependencia.setNombre(rs.getString("nombre"));
            dependencia.setUbicacion(rs.getString("ubicacion"));
            dependencia.setAdministrador(getFuncionarioH(rs));
        }
        return dependencia;
    }

    private Solicitud getSolicitudH(ResultSet rs) throws SQLException {
        Solicitud solicitud = null;
        if (rs.next()) {
            solicitud = new Solicitud();
            solicitud.setID(rs.getInt("ID"));
            solicitud.setComprobante(rs.getString("comprobante"));
            solicitud.setFecha(rs.getDate("fecha"));
            solicitud.setTipo(rs.getInt("tipo"));
            solicitud.setCantidad(rs.getInt("cantidad"));
            solicitud.setTotal(rs.getFloat("total"));
            solicitud.setEstado(rs.getInt("estado"));
            solicitud.setDependencia(getDependenciaH(rs));
            solicitud.setRegistrador(getFuncionarioH(rs));
            solicitud.setBienes(this.getBienes(solicitud));
        }
        return solicitud;
    }

    private Bien getBienH(ResultSet rs) throws SQLException {
        Bien bien = null;
        if (rs.next()) {
            bien = new Bien();
            bien.setID(rs.getInt("ID"));
            bien.setMarca(rs.getString("marca"));
            bien.setModelo(rs.getString("modelo"));
            bien.setDescripcion(rs.getString("descripcion"));
            bien.setPrecio(rs.getFloat("precio"));
            bien.setCantidad(rs.getInt("cantidad"));
            bien.setSolicitud(getSolicitudH(rs));
        }

        return bien;
    }
    // METODOS -----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // Funcionario ----------------------------------------------------------------------------

    public Funcionario getFuncionario(int id) throws SQLException {
        Funcionario func = null;
        String sql = "select * from Funcionarios where ID = %d";
        sql = String.format(sql, id);
        ResultSet rs = db.executeQuery(sql);
        func = this.getFuncionarioH(rs);
        return func;
    }

    public void addFuncionario(Funcionario func) throws Exception {
        String sql = "insert into Funcionarios(identificacion, nombre)"
                + " values('%s','%s')";

        sql = String.format(sql, func.getIdentificacion(), func.getNombre());

        int PK = db.executeUpdateWithKeys(sql);

        if (PK == 0) {
            throw new Exception("Ocurrio un error al tratar de agregar el funcionario");
        } else {
            func.setID(PK);
        }
    }

// Usuario ------------------------------------------------------------------------------
    public Usuario getUsuario(String cuenta, String pass) throws SQLException {
        Usuario user = null;
        String sql = "select * from Usuarios where cuenta= '%s' AND password = '%s'";
        sql = String.format(sql, cuenta, pass);
        ResultSet rs = db.executeQuery(sql);
        user = this.getUsuarioH(rs);
        return user;
    }

    public void addUsuario(Usuario usuario) throws Exception {
        String sql = "insert into Usuarios(cuenta, password, rol, funcionario)"
                + " values('%s','%s',%d, %d)";

        sql = String.format(sql, usuario.getCuenta(), usuario.getPassword(),
                usuario.getRol(), usuario.getFuncionario().getID());
        int PK = db.executeUpdateWithKeys(sql);
        if (PK == 0) {
            throw new Exception("Ocurrio un error al tratar de agregar el usuario");
        } else {
            usuario.setID(PK);
        }

    }

// Dependencia ------------------------------------------------------------------------------
    public Dependencia getDependencia(int id) throws SQLException {
        Dependencia dependencia = null;
        String sql = "select * from Dependencias where ID = %d";
        sql = String.format(sql, id);
        ResultSet rs = db.executeQuery(sql);
        dependencia = getDependenciaH(rs);
        return dependencia;
    }

    public void addDependencia(Dependencia dependencia) throws Exception {
        String sql = "insert into Dependencias(nombre,ubicacion,administrador)"
                + " values('%s','%s',%d)";

        sql = String.format(sql, dependencia.getNombre(), dependencia.getUbicacion(), dependencia.getAdministrador().getID());
        int PK = db.executeUpdateWithKeys(sql);
        if (PK == 0) {
            throw new Exception("Ocurrio un error al tratar de agregar el Funcionario");
        } else {
            dependencia.setID(PK);
        }
    }

// Bien ------------------------------------------------------------------------------
    public Bien getBien(int id) throws SQLException {
        Bien bien = null;
        String sql = "select * from Bienes where ID = %d";
        sql = String.format(sql, id);
        ResultSet rs = db.executeQuery(sql);
        bien = this.getBienH(rs);
        return bien;
    }

    public List<Bien> getBienes(Solicitud solicitud) throws SQLException {
        List<Bien> bienes = new ArrayList<>();
        String sql = "select * from Bienes where solicitud = %d";
        sql = String.format(sql, solicitud.getID());
        ResultSet rs = db.executeQuery(sql);
        Bien bien = null;
        while (rs.next()) {
            bien = new Bien();
            bien.setID(rs.getInt("ID"));
            bien.setMarca(rs.getString("marca"));
            bien.setModelo(rs.getString("modelo"));
            bien.setDescripcion(rs.getString("descripcion"));
            bien.setPrecio(rs.getFloat("precio"));
            bien.setCantidad(rs.getInt("cantidad"));
            bien.setSolicitud(solicitud);
            bienes.add(bien);
        }

        return bienes;
    }

    public void addBien(Bien bien) throws Exception {
        String sql = "insert into Bienes(marca,modelo,descripcion,precio,cantidad,solicitud)"
                + " values('%s','%s','%s',%f,%d,%d)";
        sql = String.format(sql, bien.getMarca(), bien.getModelo(), bien.getDescripcion(), bien.getPrecio(), bien.getCantidad(), bien.getSolicitud().getID());
        int PK = db.executeUpdateWithKeys(sql);

        if (PK == 0) {
            throw new Exception("Ocurrio un error al tratar de agregar el Bien");
        } else {
            bien.setID(PK);
        }
    }

    // Solicitud ------------------------------------------------------------------------------
    public Solicitud getSolicitud(int id) throws SQLException {
        Solicitud solicitud = null;
        String sql = "Select * from Solicitudes where ID = %d";
        sql = String.format(sql, id);
        ResultSet rs = db.executeQuery(sql);
        solicitud = this.getSolicitudH(rs);
        return solicitud;
    }

    public void addSolicitud(Solicitud solicitud) throws Exception {
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        String currentTime = sdf1.format(solicitud.getFecha());

        String sql = "insert into Solicitudes(comprobante,fecha,tipo,cantidad,total,estado,dependencia,registrador)"
                + " values('%s','%s', %d,%d,%f,%d,%d,%d) ";

        sql = String.format(sql, solicitud.getComprobante(), currentTime, solicitud.getTipo(), solicitud.getCantidad(), solicitud.getTotal(),
                solicitud.getEstado(), solicitud.getDependencia().getID(), solicitud.getRegistrador().getID());

        int PK = db.executeUpdateWithKeys(sql);

        if (PK == 0) {
            throw new Exception("Ocurrio un error al tratar de agregar el Bien");
        } else {
            solicitud.setID(PK);
        }
    }
    
    public List<Solicitud> getSolicitudes(Dependencia depe) throws SQLException{
        List<Solicitud> solicitudes = new ArrayList<>();
        String sql = "select * from Solicitudes where dependencia = %d";
        sql = String.format(sql, depe.getID());
        ResultSet rs = db.executeQuery(sql);
        Solicitud solicitud = null;
        while(rs.next()){
            solicitud = new Solicitud();
            solicitud.setID(rs.getInt("ID"));
            solicitud.setComprobante(rs.getString("comprobante"));
            solicitud.setFecha(rs.getDate("fecha"));
            solicitud.setTipo(rs.getInt("tipo"));
            solicitud.setCantidad(rs.getInt("cantidad"));
            solicitud.setTotal(rs.getFloat("total"));
            solicitud.setEstado(rs.getInt("estado"));
            solicitud.setDependencia(depe);
            solicitud.setRegistrador(getFuncionarioH(rs));
            solicitud.setBienes(this.getBienes(solicitud));
        }
        return solicitudes;
    }
    
    

}
