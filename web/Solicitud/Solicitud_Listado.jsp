<%-- 
    Document   : solicitud
    Created on : Mar 23, 2019, 7:50:41 PM
    Author     : Jose
--%>

<%@page import="Activos.Logic.Solicitud"%>
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
                <h2>Solicitudes - (Departamento)</h2>
            </center>
            <br>
            <div class="input-group">
                <div class="col-xs-12">
                    <div class="input-group-btn">
                        <h4>Buscar Comprobante</h4>
                        <input type="text" class="form-control" placeholder="No. Comprobante" name="search">
                        <button class="btn btn-default" type="submit"><i class="glyphicon glyphicon-search"></i></button>
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
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td><a href="#">1</a></td>
                        <td>C001</td>
                        <td>01/12/2019</td>
                        <td>Compra</td>
                        <td>Pendiente</td>
                    </tr>
                    <tr>
                        <td><a href="#">2</a></td>
                        <td>C002</td>
                        <td>02/12/2019</td>
                        <td>Compra</td>
                        <td>Aprobado</td>
                    </tr>
                    <tr>
                        <td><a href="#">3</a></td>
                        <td>C003</td>
                        <td>03/12/2019</td>
                        <td>Donacion</td>
                        <td>Denegada</td>
                    </tr>
                </tbody>
            </table>
        </div>
    </body>
</html>
