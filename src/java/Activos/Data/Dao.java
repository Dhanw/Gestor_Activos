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
        try {
            func.setID(rs.getInt("ID"));
            func.setIdentificacion(rs.getString("identificacion"));
            func.setNombre(rs.getString("nombre"));
            return func;
        } catch (SQLException ex) {
            return null;
        }
    }

    private Usuario getUsuarioH(ResultSet rs) throws SQLException, Exception {
        Usuario user = new Usuario();
        try {
            user.setID(rs.getInt("ID"));
            user.setCuenta(rs.getString("cuenta"));
            user.setPassword(rs.getString("password"));
            user.setRol(rs.getInt("rol"));
            int idfunc = rs.getInt("funcionario");
            user.setFuncionario(this.getFuncionario(idfunc));
            return user;
        } catch (SQLException ex) {
            return null;
        }
    }

    private Dependencia getDependenciaH(ResultSet rs) throws SQLException, Exception {
        Dependencia dependencia = new Dependencia();
        try {
            dependencia.setID(rs.getInt("ID"));
            dependencia.setNombre(rs.getString("nombre"));
            dependencia.setUbicacion(rs.getString("ubicacion"));
            int setfun = rs.getInt("administrador");
            dependencia.setAdministrador(this.getFuncionario(setfun));
            return dependencia;
        } catch (SQLException ex) {
            return null;
        }
    }

    private Solicitud getSolicitudH(ResultSet rs) throws SQLException, Exception {
        Solicitud solicitud = new Solicitud();
        try {
            solicitud.setID(rs.getInt("ID"));
            solicitud.setComprobante(rs.getString("comprobante"));
            solicitud.setFecha(rs.getDate("fecha"));
            solicitud.setTipo(rs.getInt("tipo"));
            solicitud.setCantidad(rs.getInt("cantidad"));
            solicitud.setTotal(rs.getFloat("total"));
            solicitud.setEstado(rs.getInt("estado"));
            int setfun = rs.getInt("dependencia");
            solicitud.setDependencia(getDependencia(setfun));
            int setfun2 = rs.getInt("registrador");
            solicitud.setRegistrador(this.getFuncionario(setfun2));
            solicitud.setBienes(this.getBienes(solicitud));
            return solicitud;
        } catch (SQLException ex) {
            return null;
        }
    }

    private Bien getBienH(ResultSet rs) throws SQLException, Exception {
        Bien bien = new Bien();
        try {
            bien.setID(rs.getInt("ID"));
            bien.setMarca(rs.getString("marca"));
            bien.setModelo(rs.getString("modelo"));
            bien.setDescripcion(rs.getString("descripcion"));
            bien.setPrecio(rs.getFloat("precio"));
            bien.setCantidad(rs.getInt("cantidad"));
            bien.setSolicitud(getSolicitudH(rs));
            return bien;
        } catch (SQLException ex) {
            return null;
        }
    }
    // METODOS -----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // Funcionario ----------------------------------------------------------------------------

    public Funcionario getFuncionario(int id) throws SQLException, Exception {
        Funcionario func = null;
        String sql = "select * from Funcionarios where ID = %d";
        sql = String.format(sql, id);
        ResultSet rs = db.executeQuery(sql);
        if (rs.next()) {
            return getFuncionarioH(rs);
        } else {
            return null;
        }
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
    public Usuario getUsuario(String cuenta, String pass) throws SQLException, Exception {
        Usuario user = null;
        String sql = "select * from Usuarios where cuenta= '%s' AND password = '%s'";
        sql = String.format(sql, cuenta, pass);
        ResultSet rs = db.executeQuery(sql);
        if (rs.next()) {
            return getUsuarioH(rs);
        } else {
            return null;
        }
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
    public Dependencia getDependencia(int id) throws SQLException, Exception {
        Dependencia dependencia = null;
        String sql = "select * from Dependencias where ID = %d";
        sql = String.format(sql, id);
        ResultSet rs = db.executeQuery(sql);
        if (rs.next()) {
            return getDependenciaH(rs);
        } else {
            return null;
        }

    }

    public Dependencia getDependencia_fromFuncionario(int id) throws SQLException, Exception {
        Dependencia dependencia = null;
        String sql = "select * from Dependencias where administrador = %d";
        sql = String.format(sql, id);
        ResultSet rs = db.executeQuery(sql);
        if (rs.next()) {
            return getDependenciaH(rs);
        } else {
            return null;
        }
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
    public Bien getBien(int id) throws SQLException, Exception {
        Bien bien = null;
        String sql = "select * from Bienes where ID = %d";
        sql = String.format(sql, id);
        ResultSet rs = db.executeQuery(sql);
        if (rs.next()) {
            return this.getBienH(rs);
        } else {
            return null;
        }

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
    public Solicitud getSolicitud(int id) throws SQLException, Exception {
        Solicitud solicitud = null;
        String sql = "Select * from Solicitudes where ID = %d";
        sql = String.format(sql, id);
        ResultSet rs = db.executeQuery(sql);
        if (rs.next()) {
            return this.getSolicitudH(rs);
        } else {
            return null;
        }
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

    public List<Solicitud> getSolicitudes(Dependencia depe) throws SQLException, Exception {
        List<Solicitud> solicitudes = new ArrayList<>();
        String sql = "select * from Solicitudes where dependencia = %d";
        sql = String.format(sql, depe.getID());
        ResultSet rs = db.executeQuery(sql);
        Solicitud solicitud = null;
        while (rs.next()) {
            solicitudes.add(getSolicitudH(rs));
        }
        return solicitudes;
    }

    public void eliminarSolicitud(int solicitud) throws SQLException {
        //Borra los bienes enlazados a esa solicitud
        String sql = "delete from Bienes where solicitud = %d";
        sql = String.format(sql, solicitud);
        int count = db.executeUpdate(sql);

        //Borrar la solicitud
        String sqlS = "delete from Solicitudes where ID = %d";
        sqlS = String.format(sqlS, solicitud);
        int countB = db.executeUpdate(sqlS);
        if (countB == 0) {
            throw new SQLException("No existe registro con codigo de solicitud " + solicitud);
        }
        if (count == 0) {
            throw new SQLException("No existe el bien asociado con codigo de solicitud " + solicitud);
        }
    }

    public void eliminarBien(int bien) throws SQLException {
        String sql = "delete from Bienes where ID = %d";
        sql = String.format(sql, bien);
        int count = db.executeUpdate(sql);
        if (count == 0) {
            throw new SQLException("No existe el bien  " + bien);
        }
    }

    // SOLITUDES FILTROS
    public List<Solicitud> SolitudesTipo(int tipo) throws SQLException, Exception {
        List<Solicitud> lista = new ArrayList<>();
        String sql = "select * from Solicitudes where tipo = %d";
        sql = String.format(sql, tipo);
        ResultSet rs = db.executeQuery(sql);
        Solicitud soli = null;
        while (rs.next()) {
            lista.add(this.getSolicitudH(rs));
        }

        return lista;
    }

    public List<Solicitud> SolitudesEstado(int estado) throws SQLException, Exception {
        List<Solicitud> lista = new ArrayList<>();
        String sql = "select * from Solicitudes where estado = %d";
        sql = String.format(sql, estado);
        ResultSet rs = db.executeQuery(sql);
        while (rs.next()) {
            lista.add(this.getSolicitudH(rs));
        }

        return lista;
    }
   
   public List<Solicitud> getByComprobante(String comprobante) throws Exception{
       List<Solicitud> lista = new ArrayList<>();
       String sql = "select * from Solicitudes where comprobante LIKE '%" + comprobante + "%'";
       //sql = String.format(sql, comprobante);
       ResultSet rs = db.executeQuery(sql);
       while(rs.next()){
            lista.add(this.getSolicitudH(rs));
       }
       
       
       return lista;
   }
}
