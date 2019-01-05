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
@WebServlet(name = "controladorAdminReservas", urlPatterns = {"/controladorAdminReservas"})
public class controladorAdminReservas extends HttpServlet {

    private Connection con;
    private Statement set;
    private Statement set2;
    private Statement set3;
    private ResultSet rs;

    @Override
    public void init(ServletConfig cfg) throws ServletException {
        con = BD.getConexion();
    }

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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet controladorAdminReservas</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet controladorAdminReservas at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        con = BD.getConexion();
        HttpSession s = request.getSession(true);
        String id = request.getParameter("elid");
        String boton = request.getParameter("botones");
        String emaillog = (String) s.getAttribute("emaillogin");
        boolean sepuede = false;
        System.out.println(boton);
        try {
            set = con.createStatement();
            rs = set.executeQuery("Select * from reservar where id='" + id + "'");
            rs.next();
            String cad = rs.getString("estado");
            System.out.println(cad);
            if (boton.equals("Llaves entregadas") && cad.equals("activa")) {
                sepuede = true;
                set2 = con.createStatement();
                set2.executeUpdate("Update reservar set estado='llavesEntregadas' where id='" + id + "'");
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
                            + "                    <img src=\"imagenes/adminreservasSI.PNG\" id=\"imgtitulo\">\n"
                            + "                    <a href=\"pantallaAdminReservas.jsp\">Volver atras</a>\n"
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
            } else if (boton.equals("Reserva finalizada") && cad.equals("cancelada")==false) {
                sepuede = true;
                set3 = con.createStatement();
                set3.executeUpdate("Update reservar set estado='finalizada' where id='" + id + "'");
                //AQUI VA EL CALCULO DEL RECARGO
                response.sendRedirect("pantallaRecargo.jsp"); 
            } 
            else if(sepuede==false){
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
                            + "                    <img src=\"imagenes/adminreservasNO.PNG\" id=\"imgtitulo\">\n"
                            + "                    <a href=\"pantallaAdminReservas.jsp\">Volver atras</a>\n"
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

        } catch (SQLException ex1) {
            System.out.println("No lee de la tabla reservar. " + ex1);
            response.setContentType("text/html;charset=UTF-8");

            processRequest(request, response);
        }

    } 
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */

    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
