/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Activos.Controller;

import Activos.Logic.Model;
import Activos.Logic.Usuario;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author wizzard
 */
@WebServlet(name = "Controller_Login", urlPatterns = {"/UserLogin/PrepareLogin", "/UserLogin/Login", "/UserLogin/Logout"})
public class Controller_Login extends HttpServlet {

    Model dao = new Model();
    public static final int INDEFINIDO = 0;
    public static final int ADMINISTRADOR_DEPENDENCIA = 1;
    public static final int SECRETARIA_OCCB = 2;
    public static final int JEFE_OCCB = 3;
    public static final int REGISTRADOR_BIENES = 4;
    public static final int JEFE_RRH = 5;
    public static final int JEFE_OCBB_RHH = 7;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        switch (request.getServletPath()) {
            case "/UserLogin/PrepareLogin":
                this.prepareLogin(request, response);
                break;
            case "/UserLogin/Login":
                this.Login(request, response);
                break;
            case "/UserLogin/Logout":
                this.Logout(request, response);
                break;
            default:
                break;
        }
    }

    void updateModel(Usuario user, HttpServletRequest request) {
        user.setCuenta(request.getParameter("cuenta"));
        user.setPassword(request.getParameter("password"));
    }

    protected void prepareLogin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Usuario user = new Usuario();
        request.setAttribute("user", user);
        request.getRequestDispatcher("/UserLogin/Login_View.jsp").forward(request, response);
    }

    private void Login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Usuario user_tmp = new Usuario();
        updateModel(user_tmp, request);
        // Busca el usuario en la DB, si no lo encuentra se regresa al login
        try {
            Usuario actual = dao.getUsuario(request.getParameter("cuenta"), request.getParameter("password"));
//        actual = new Usuario(1, "admin_info", "aaa", 1, new Funcionario(1, "403013010", "Oscar Campos"));
            if (actual == null) {
                request.getRequestDispatcher("/UserLogin/Login_View.jsp").forward(request, response);
            } else {
                request.getSession(true).setAttribute("user", actual);
                request.setAttribute("usuario", actual); //Se le puede quitar por que ya esta guardado en la sesion
                seleccionador(request, response, actual.getRol());
            }

            request.getRequestDispatcher("/UserLogin/Login_View.jsp").forward(request, response);
        } catch (Exception ex) {
        }
    }

    protected void Logout(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        session.invalidate();
        request.getRequestDispatcher("/UserLogin/Login_View.jsp").forward(request, response);
    }

    private void seleccionador(HttpServletRequest request, HttpServletResponse response, int tipo) throws ServletException, IOException {
        switch (tipo) {
            case ADMINISTRADOR_DEPENDENCIA:
                request.getRequestDispatcher("/Solicitud/Solicitud_Listado.jsp").forward(request, response);
                break;
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
