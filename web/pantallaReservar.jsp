<%-- 
    Document   : pantallaReservar
    Created on : 02-ene-2019, 16:25:56
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
        <title>RentG - Reserva</title>
        <meta charset="utf-8">
        <link rel="stylesheet" href="css/cssReservar.css">
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
                <section id="reserva">
                    HOLA,
                    <% String elnombre = (String) session.getAttribute("nombrelogin");
                        String path;
                        if (elnombre == null) {
                            elnombre = "No esta logueado";
                    %>
                    <label id="nombree"><%=elnombre%></label>

                    <%

                    } else if (elnombre == "Alfonso" || elnombre == "Antonia") {
                    %>
                    <label id="nombree"><%=elnombre%></label>
                    <img id="fotolog" src=imagenes/admin.PNG </img>
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

                    <img src="imagenes/reservar.png" id="imgtitulo">
                    <h3>Recuerda que no puede delvolver el coche el mismo dia en que lo reservas</h3>
                    <form name="formreserva" id="formreserva" method="get" action="controladorReserva">
                        <p>Coche:
                            <datalist id="coches" name="elcoche">
                                <option value="abc789" label="Mercedes">
                                <option value="abc123" label="Fiat">                                
                                <option value="cba547" label="Jaguar">
                            </datalist>
                            <input name="coches" id="cochesin" list="coches" placeholder="Elige un coche..." />
                        <p><br/></p>
                        <p>Fecha de salida:<input type="date" name="fechasalida" id="fechasalida" />
                        <p><br/></p>
                        <p>Hora de salida:<input type="time" name="horasalida" id="horasalida" />
                        <p><br/></p>
                        <p>Fecha de llegada:<input type="date" name="fechallegada" id="fechallegada" />
                        <p><br/></p>
                        <p>Hora de llegada:<input type="time" name="horallegada" id="horallegada" />
                        <p><br/></p>
                        <p>Lugar:
                            <datalist id="lugar" name="lugar">
                                <option value="Vitoria-Gasteiz" label="Lugar 1">
                                <option value="Bilbao" label="Lugar 2">                                
                                <option value="Donostia" label="Lugar 3">
                            </datalist>
                            <input name="lugares" id="lugares" list="lugar" placeholder="Elige un lugar..." />
                        <p><br/></p>
                        <p><input id="botonenviar" type="submit" value="Enviar" />
                    </form>
                </section>
                <section id="imagenescoches">
                    <img src="imagenes/coche1.PNG" id="imgcoche1">
                    <img src="imagenes/coche2.PNG" id="imgcoche2">
                    <img src="imagenes/coche3.PNG" id="imgcoche3">
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
