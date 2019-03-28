/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Activos.Controllers;

import Activos.Logic.Dependencia;
import Activos.Logic.Model;
import Activos.Logic.Solicitud;
import Activos.Logic.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jorac
 */
@WebServlet(name = "Controller_Solicitud", urlPatterns = {"/Solicitud/Solicitud_listar", "/Solicitud/Solicitud_crear", "/Solicitud/Solicitud_mostrar", "/Solicitud/Solicitud_eliminar", "/Solicitud/Solicitud_editar",
    "/Solicitud/Filtro_Comprobante"})
public class Controller_Solicitud extends HttpServlet {

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
            throws ServletException, IOException, Exception {

        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            switch (request.getServletPath()) {
                case "/Solicitud/Solicitud_listar":
                    this.listarSolicitudes(request, response);
                    break;
                case "/Solicitud/Solicitud_crear":
                    this.crearSolicitud(request, response);
                    break;
                case "/Solicitud/Solicitud_mostrar":
                    this.mostrarSolicitud(request, response);
                    break;
                case "/Solicitud/Solicitud_eliminar":
                    this.eliminarSolicitud(request, response);
                    break;
                case "/Solicitud/Solicitud_editar":
                    this.editarSolicitud(request, response);
                    break;
            }

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

        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(Controller_Solicitud.class.getName()).log(Level.SEVERE, null, ex);
        }

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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(Controller_Solicitud.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    private void crearSolicitud(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void mostrarSolicitud(HttpServletRequest request, HttpServletResponse response) {
            Solicitud model = new Solicitud();
            Solicitud modelConsultar = null;
            try {
                modelConsultar = Model.instance().getSolicitud(Integer.parseInt(request.getParameter("ID")));
                request.setAttribute("model", modelConsultar);
                request.getRequestDispatcher("/Solicitud/Solicitud_Mostrar.jsp").
                        forward(request, response);
            } catch (Exception ex) {
            }
        
    }

    void updateModelId(Solicitud model, HttpServletRequest request) {
        model.setID(Integer.parseInt(request.getParameter("nombre")));
    }

    private void editarSolicitud(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void eliminarSolicitud(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int id_solicitud = Integer.parseInt(request.getParameter("ID"));
        try {
            Model.instance().eliminarSolicitud(id_solicitud);
            request.setAttribute("mensaje", "correcto");
        } catch (SQLException error) {
            request.setAttribute("mensaje", error.getMessage());
        }
        request.getRequestDispatcher("/Solicitud/Solicitud_listar").forward(request, response);
    }

    private void listarSolicitudes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Usuario use = (Usuario) request.getSession().getAttribute("user");
            Dependencia dep = Model.instance().getDependencia_fromFuncionario(use.getFuncionario().getID());
            request.setAttribute("depe", dep);
            List<Solicitud> solicitudes = Model.instance().solicitudesPorDependencia(dep);
            request.setAttribute("soli", solicitudes);
            request.getRequestDispatcher("/Solicitud/Solicitud_Listado.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(Controller_Solicitud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
