/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packServlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utils.BD;

/**
 *
 * @author serna
 */
@WebServlet(name = "controladorRegistro", urlPatterns = {"/controladorRegistro"})
public class controladorRegistro extends HttpServlet {

    private Connection con;
    private Statement set;
    private ResultSet rs;
    String cad;

    @Override
    public void init(ServletConfig cfg) throws ServletException {
        con = BD.getConexion();
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
        con = BD.getConexion();
        HttpSession s = request.getSession(true);
        String email = request.getParameter("email");
        String contra = request.getParameter("contrasena");
        String nombre = request.getParameter("nombre");
        String dni = request.getParameter("dni");
        String tele = request.getParameter("telefono");
        int telefono2=new Integer(tele);
        try {
            set = con.createStatement();
            //int i = set.executeUpdate("INSERT INTO clientes"
                        //+ "VALUES ('" + email + "','" + contra + "','" + nombre + "','" + dni + "'," + telefono2 + ")"); 
            int o = set.executeUpdate("INSERT INTO clientes VALUES ("+ email + "," + contra + "," + nombre + "," + dni + "," + telefono2 + ")");
            
            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */
                out.println("<!DOCTYPE html>");
                out.println("<html lang=\"es\">\n"
                        + "    <head>\n"
                        + "        <title>RentG</title>\n"
                        + "        <meta charset=\"utf-8\">\n"
                        + "        <link rel=\"stylesheet\" type=\"text/css\" href=\"css/cssPantallaPrincipal.css\"/>\n"
                        + "        <script src=\"javascript/jsPantallaInicial.js\"></script>\n"
                        + "    </head>\n"
                        + "    <body>\n"
                        + "        <header id=\"cabecera\">\n"
                        + "            <div>\n"
                        + "                <h1>RentG</h1>\n"
                        + "                <h2>La plataforma de alquiler de coches lider en el Pais Vasco</h2>\n"
                        + "                <a href=\"pantallaInicial.jsp\"><img src=\"imagenes\\logpng.png\" id=\"imglogo\"></a>\n"
                        + "                <img src=\"imagenes/silueta.png\" id=\"imgsilueta\">\n"
                        + "            </div>\n"
                        + "        </header>\n"
                        + "        <nav id=\"menuprincipal\">\n"
                        + "            <div>\n"
                        + "                <ul>\n"
                        + "                    <li><a href=\"pantallaLogin.jsp\">Login</a></li>\n"
                        + "                    <li><a href=\"pantallaReservar.jsp\">Reservar</a></li>\n"
                        + "                    <li><a href=\"pantallaConsultaUsuario.jsp\">Consultar Reservas</a></li>\n"
                        + "                    <li><a href=\"\">Contacto</a></li>\n"
                        + "                </ul>\n"
                        + "            </div>\n"
                        + "        </nav>\n"
                        + "        <main>\n"
                        + "                    <img src=\"imagenes/registroOK.PNG\" id=\"imgtitulo\">\n"
                        + "                    <a href=\"pantallaInicial.jsp\">Volver Inicio</a>\n"
                        + "        </main>\n"
                        + "        <footer>\n"
                        + "            <section class=\"direccion\">\n"
                        + "                <address>Vitoria, País Vasco</address>\n"
                        + "                <small>&copy; Derechos Reservados 2018</small>\n"
                        + "            </section>\n"
                        + "        </footer>\n"
                        + "    </body>\n"
                        + "</html>");

            }

        } catch (SQLException ex1) {
            System.out.println("No lee de la tabla Jugadores. " + ex1);
            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */
                out.println("<!DOCTYPE html>");
                out.println("<html lang=\"es\">\n"
                        + "    <head>\n"
                        + "        <title>RentG</title>\n"
                        + "        <meta charset=\"utf-8\">\n"
                        + "        <link rel=\"stylesheet\" type=\"text/css\" href=\"css/cssPantallaPrincipal.css\"/>\n"
                        + "        <script src=\"javascript/jsPantallaInicial.js\"></script>\n"
                        + "    </head>\n"
                        + "    <body>\n"
                        + "        <header id=\"cabecera\">\n"
                        + "            <div>\n"
                        + "                <h1>RentG</h1>\n"
                        + "                <h2>La plataforma de alquiler de coches lider en el Pais Vasco</h2>\n"
                        + "                <a href=\"pantallaInicial.jsp\"><img src=\"imagenes\\logpng.png\" id=\"imglogo\"></a>\n"
                        + "                <img src=\"imagenes/silueta.png\" id=\"imgsilueta\">\n"
                        + "            </div>\n"
                        + "        </header>\n"
                        + "        <nav id=\"menuprincipal\">\n"
                        + "            <div>\n"
                        + "                <ul>\n"
                        + "                    <li><a href=\"pantallaLogin.jsp\">Login</a></li>\n"
                        + "                    <li><a href=\"pantallaReservar.jsp\">Reservar</a></li>\n"
                        + "                    <li><a href=\"pantallaConsultaUsuario.jsp\">Consultar Reservas</a></li>\n"
                        + "                    <li><a href=\"\">Contacto</a></li>\n"
                        + "                </ul>\n"
                        + "            </div>\n"
                        + "        </nav>\n"
                        + "        <main>\n"
                        + "                    <img src=\"imagenes/registroError.PNG\" id=\"imgtitulo\">\n"
                        + "                    <a href=\"pantallaRegistro.jsp\">Reintentar</a>\n"
                        + "        </main>\n"
                        + "        <footer>\n"
                        + "            <section class=\"direccion\">\n"
                        + "                <address>Vitoria, País Vasco</address>\n"
                        + "                <small>&copy; Derechos Reservados 2018</small>\n"
                        + "            </section>\n"
                        + "        </footer>\n"
                        + "    </body>\n"
                        + "</html>");

            }
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
