<%-- 
    Document   : solicitud
    Created on : Mar 23, 2019, 7:50:41 PM
    Author     : Jose
--%>

<%@page import="java.util.List"%>
<%@page import="Activos.Logic.Solicitud"%>
<%@page import="Activos.Logic.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Solicitudes Listado</title>
        <%@ include file="/Head.jsp" %>
    </head>
    <body>
        <%@ include file="/Header.jsp" %>
        <div class="container">
            <center>
                <%
                    Usuario use = (Usuario) session.getAttribute("user");

                    Dependencia dep=(Dependencia)request.getAttribute("depe");
                    if (dep != null) {
                        List<Solicitud> solicitudes= (List<Solicitud>)request.getAttribute("soli");

                %> 
                <h2>Solicitudes - (<%=dep.getNombre()%>)</h2>
               
            </center>
            <br>
            <div class="input-group">
                <div class="col-xs-12">
                    <div class="input-group-btn">
                        <h4>Buscar Comprobante</h4>
                        <input name="Filtro_Comprobante" type="text" class="form-control" placeholder="No. Comprobante" name="search">
                        <button action="Solicitud/Filtro_Comprobante" class="btn btn-default" type="submit"><i class="glyphicon glyphicon-search"></i></button>
                    </div>
                </div>
            </div>
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Comprobante</th>
                        <th>Fecha</th>
                        <th>Tipo</th>
                        <th>Estado</th>
                        <th>Eliminar</th>
                    </tr>
                </thead>
                <tbody>
                    <%  
                        for (Solicitud sol : solicitudes) {
                    %>
                    <tr>
                        <td><a href="#"></a><%=sol.getID()%>  </td>
                        <td><%=sol.getComprobante()%></td>
                        <td><%=sol.getFecha()%></td>
                        <td><%=sol.getDescripcionTipo()%></td>
                        <td><%=sol.getDescripcionEstado()%></td>
                        <td><a href="Solicitud/Solicitud_eliminar?ID=<%=sol.getID()%>"><img width=30px" src="Images/delete.png"/></a></td>
                    </tr>
                    <%}}%>
                </tbody>
            </table>
        </div>
    </body>
</html>
