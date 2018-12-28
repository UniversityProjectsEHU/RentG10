<%-- 
    Document   : pantallaConsultaAdmin
    Created on : 22-dic-2018, 15:30:24
    Author     : serna
--%>

<%@page import="utils.BD"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <title>RentG - Consulta Reservas</title>
        <meta charset="utf-8">
        <link rel="stylesheet" href="css/cssConsultaAdmin.css">
        <script src="javascript/jsConsultaAdmin.js"></script>
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
                    <li><a href="">Contacto</a></li>
                </ul>
            </div>
        </nav>
        <main>
            <div> 
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
                <section id="consulta">
                    <img src="imagenes/consulAdmin.PNG" id="imgtitulo">
                    <a href="pantallaAdminporFecha.jsp"><img src="imagenes/porfecha.PNG" id="imgfecha"></a>
                    <a href="pantallaAdminporCliente.jsp"><img src="imagenes/poremail.PNG" id="imgemail"></a>
                    <a href="pantallaAdminporMatricula.jsp"><img src="imagenes/pormatricula.PNG" id="imgmatricula"></a>
                    <form id="formconsulta" method="get" action="procesar.php">

                    </form>
                </section> 
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

