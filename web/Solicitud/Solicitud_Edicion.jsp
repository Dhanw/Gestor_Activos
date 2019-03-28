<%-- 
    Document   : Solicitud_Edicion
    Created on : 23/03/2019, 08:16:02 PM
    Author     : wizzard
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Solicitudes Edicion</title>
        <%@ include file="/Head.jsp" %>
    </head>
    <body>
        <%@ include file="/Header.jsp" %>
        <div class="container">
            <center>
                <h2>Formulario Comprobante</h2>
            </center>
            <br> <br>
            <form class="form-inline" action="/Registrar_Comprobante">
                <div class="form-group">
                    <label for="Comprobante">Comprobante:</label>
                    <input type="text" class="form-control" id="comprobante" placeholder="No.Comprobante" name="comprobante">
                </div>
                <div class="form-group">
                    <label for="Fecha">Fecha:</label>
                    <input type="text" class="form-control" id="fecha" placeholder="01/01/2019" name="fecha">
                </div>
                <label for="Tipo_Comprobante">Tipo:</label>
                <select class="form-control" id="tipo">
                    <option>Compra</option>
                    <option>Venta</option>
                </select>
                <button type="submit" class="btn btn-default">Agregar</button>
            </form>
            <br> <br> <br>
            <form class="form-inline" action="/Registrar_Comprobante">
                <table class="table table-hover">
                    <thead>
                        
                        <tr>
                            <th>Descripcion</th>
                            <th>Marca</th>
                            <th>Modelo</th>
                            <th>Precio Unitario</th>
                            <th>Cantidad</th>
                        </tr>
                        
                    </thead>
                    <tbody>
                        <tr>
                            <td>Computadora</td>
                            <td>Dell</td>
                            <td>Inspirion5960</td>
                            <td>500</td>
                            <td>1</td>
                        </tr>
                        <tr>
                            <td>Sillas</td>
                            <td>ACME</td>
                            <td>2011</td>
                            <td>250</td>
                            <td>10</td>
                        </tr>
                        <tr>
                            <td>Escritorio</td>
                            <td>Acme</td>
                            <td>2019</td>
                            <td>100</td>
                            <td>10</td>
                        </tr>
                    </tbody>
                </table>
                <center>
                    <button type="submit" class="btn btn-default">Agregrar</button>
                    <button type="submit" class="btn btn-default">Eliminar</button>
                </center>
            </form>
        </div>
    </body>

</html>
