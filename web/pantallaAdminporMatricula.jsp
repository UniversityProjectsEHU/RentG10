<%-- 
    Document   : pantallaAdminFecha
    Created on : 03-ene-2019, 11:40:45
    Author     : serna
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="utils.BD"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.Statement"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <title>RentG - Consulta Reservas Admin</title>
        <meta charset="utf-8">
        <link rel="stylesheet" href="css/cssConsultaUsuario.css">
    </head>
    <body>
        <header id="cabecera">
            <div>
                <h1>RentG</h1>
                <h2>La plataforma de alquiler de coches lider en el Pais Vasco</h2>
                <a href="pantallaInicial.jsp"><img src="imagenes/logpng.png" id="imglogo"></a>
                <img src="imagenes/silueta.png" id="imgsilueta">
            </div>
        </header>
        <nav id="menuprincipal">
            <div>
                <ul>
                    <li><a href="pantallaLogin.jsp">Login</a></li>
                    <li><a href="pantallaReservar.jsp">Reservar</a></li>
                    <li><a href="pantallaConsultaUsuario.jsp">Consultar Reservas</a></li>
                    <li><a href="pantallaCancelarReserva.jsp">Cancelar Reservas</a></li>
                    <li><a href="">Contacto</a></li>
                </ul>
            </div>
        </nav>
        <main>
            <div>
                <section id="consulta">
                HOLA,
                <% String elnombre = (String) session.getAttribute("nombrelogin");
                    String path;
                    System.out.println(elnombre);
                    if (elnombre == null) {
                        elnombre = "No esta logueado";
                %>
                <label id="nombree"><%=elnombre%></label>

                <%

                } else if (elnombre.equals("Alfonso")) {
                %>
                <label id="nombree"><%=elnombre%></label>
                <img id="fotolog" src=imagenes/adminfoto.png </img>
                <%
                } else if (elnombre.equals("Antonia")) {
                %>
                <label id="nombree"><%=elnombre%></label>
                <img id="fotolog" src=imagenes/adminmujfoto.jpg</img>
                <%
                } else {
                    System.out.println(elnombre);
                    Connection con = BD.getConexion();
                    Statement st = con.createStatement();
                    ResultSet rs = st.executeQuery("select path from clientes where nombre='" + elnombre + "'");
                    rs.next();
                    path = "imagenes/" + rs.getString(1);

                %>
                <label id="nombree"><%=elnombre%></label>
                <img id="fotolog" src=<%=path%> </img>


                <% }%>
                <form name="formcerrarsesion" action="cerrarsesion" id="formcerrarsesion" method="get">
                    <input id="cerrarsesion" type="submit" value="Cerrar Sesion" /> 
                </form> 
                    <img src="imagenes/consulta.png" id="imgtitulo">
                    <form id="formconsulta" method="get" action="controladorConsultaAdminCliente">
                        <p>Matricula: <input type="text" name="matriculacliente" id="matriculacliente" />
                        <p><br/></p>
                        <p><input id="botonenviar" type="submit" value="Enviar" />
                    </form>
                </section> 
                <h1>Estas son tus reservas a partir de la fecha indicada</h1>
                <table border=1>
                    <tr><td><b>ID</b></td><td><b>Email</b></td><td><b>Matricula</b></td><td><b>HoraInicio</b></td><td><b>FechaInicio</b></td><td><b>HoraFinal</b></td><td><b>FechaFinal</b></td><td><b>Lugar</b></td><td><b>Estado</b></td></tr>
                    <%
                        Statement set;
                        ResultSet rs;
                        Connection con = BD.getConexion();
                        try {
                            String mat = (String) session.getAttribute("matriculaclienteconsult");
                            set = con.createStatement();
                            rs = set.executeQuery("SELECT * FROM reservar where matricula=" + "'" + mat + "'");
                            while (rs.next()) {
                                int id = rs.getInt("id");
                                String matri = rs.getString("matricula");
                                String email = rs.getString("email");
                                String lugar = rs.getString("lugar");
                                String estado = rs.getString("estado");
                                String fechaI = rs.getString("fechaI");
                                String fechaF = rs.getString("fechaF");
                                String horaI = rs.getString("horaI");
                                String horaF = rs.getString("horaF");


                                if (1==1) {
                    %>       
                    <tr><td><%=id%></td>
                        <td><%=email%></td>
                        <td><%=matri%></td>
                        <td><%=horaI%></td>
                        <td><%=fechaI%></td>
                        <td><%=horaF%></td>
                        <td><%=fechaF%></td>
                        <td><%=lugar%></td>
                        <td><%=estado%></td></tr> 
                        <%
                                    }
                                }
                            } catch (Exception e) {
                                System.out.println("Error en acceso a BD rentg10 tabla reservar");
                            }
                        %>
                </table> 
                <img src="imagenes/banner.PNG" id="banner">
            </div>
        </main>
        <footer>
            <section class="direccion">
                <address>Vitoria, País Vasco</address>
                <small>&copy; Derechos Reservados 2018</small>
            </section>
        </footer>
    </body>
</html>




