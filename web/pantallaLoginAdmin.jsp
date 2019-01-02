<%-- 
    Document   : pantallaLogin
    Created on : 22-dic-2018, 12:54:26
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
        <title>RentG - Login</title>
        <meta charset="utf-8">
        <link rel="stylesheet" href="css/cssLogin.css"/>
        <script src="javascript/jsLogin.js"></script>
        <!--a-->

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
                <img src="imagenes/loginAdmin.PNG" id="imgtitulo">
                <form name="formLogin" action="controladorLoginAdmin" id="formLogin" method="get">
                    <p>Email: <input type="email" name="email" id="email" required="" />
                    <p><br/></p>
                    <p>Contraseña: <input type="password" name="contrasena" id="contrasena" required="" />
                    <p><br/></p>
                    <p><input id="botonlogin" type="submit" value="Login" />
                </form> 

            </div>
            <p><br/></p>
        </main>
        <footer>
            <section class="direccion">
                <address>Vitoria, País Vasco</address>
                <small>&copy; Derechos Reservados 2018</small>
            </section>
        </footer>
    </body>
</html>
