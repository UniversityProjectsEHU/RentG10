<%-- 
    Document   : pantallaInicial
    Created on : 22-dic-2018, 12:52:15
    Author     : serna
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <title>RentG</title>
        <meta charset="utf-8">
        <link rel="stylesheet" type="text/css" href="css/cssPantallaPrincipal.css"/>
        <script src="javascript/jsPantallaInicial.js"></script>
    </head>
    <body>
        <header id="cabecera">
            <div>
                <h1>RentG</h1>
                <h2>La plataforma de alquiler de coches lider en el Pais Vasco</h2>
                <a href="pantallaInicial.jsp"><img src="imagenes\logpng.png" id="imglogo"></a>
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
                <section id="busqueda">
                    HOLA,
                    <% String elnombre=(String)session.getAttribute("nombrelogin"); 
                        if(elnombre==null){
                            elnombre="No esta logueado";
                        }
                        %>
                        <label id="nombree"><%=elnombre%></label>
                    <form name="formcerrarsesion" action="cerrarsesion" id="formcerrarsesion" method="get">
                        <input id="cerrarsesion" type="submit" value="Cerrar Sesion" /> 
                    </form> 
                    <img src="imagenes/buscar.png" id="imgtitulo">
                    <form id="miformulario" method="get" action="procesar.php">
                        <p>Lugar:
                            <datalist id="lugar">
                                <option value="Vitoria-Gasteiz" label="Lugar 1">
                                <option value="Bilbao" label="Lugar 2">                                
                                <option value="Donostia" label="Lugar 3">
                            </datalist>
                            <input name="lugares" id="lugares" list="lugar" placeholder="Elige un lugar..." />
                        <p><br/></p>
                        <p>Fecha de salida:<input type="date" name="fechasalida" id="fechasalida" />
                        <p><br/></p>
                        <p>Hora de salida:<input type="time" name="horasalida" id="horasalida" />
                        <p><br/></p>
                        <p>Fecha de llegada:<input type="date" name="fechallegada" id="fechallegada" />
                        <p><br/></p>
                        <p>Hora de llegada:<input type="time" name="horallegada" id="horallegada" />
                        <p><br/></p>
                        <p><input id="botonenviar" type="submit" value="Enviar" />
                    </form>
                </section>
                <p><br/></p>
                <a href="pantallaLoginAdmin.jsp"><img src="imagenes/admin.PNG" id="imgadmin"></a>
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
