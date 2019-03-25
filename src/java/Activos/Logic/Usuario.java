/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Activos.Logic;

import java.sql.SQLException;

/**
 *
 * @author jorac
 */
public class Usuario {

    // Usuarios por tipo
    public static final int INDEFINIDO = 0;
    public static final int ADMINISTRADOR_DEPENDENCIA = 1;
    public static final int SECRETARIA_OCCB = 2;
    public static final int JEFE_OCCB = 3;
    public static final int REGISTRADOR_BIENES = 4;
    public static final int JEFE_RRH = 5;
    public static final int JEFE_OCBB_RHH = 7;

    int ID;
    String cuenta;
    String password;
    int rol;
    Funcionario funcionario;

    public Usuario() {
    }

    public Usuario(int ID, String cuenta, String password, int rol, Funcionario funcionario) {
        this.ID = ID;
        this.cuenta = cuenta;
        this.password = password;
        this.rol = rol;
        this.funcionario = funcionario;
    }

    public Usuario(String cuenta, String password, int rol) {
        this.cuenta = cuenta;
        this.password = password;
        this.rol = rol;
    }

    public Usuario(String cuenta, String password, int rol, Funcionario funcionario) {
        this.cuenta = cuenta;
        this.password = password;
        this.rol = rol;
        this.funcionario = funcionario;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRol() {
        return rol;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

}
