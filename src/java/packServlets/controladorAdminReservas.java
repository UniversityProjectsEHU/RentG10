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
import java.util.Date;
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
    private Statement set4;
    private ResultSet rs;
    private ResultSet rs4;

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
            } else if (boton.equals("Reserva finalizada") && cad.equals("cancelada") == false) {
                sepuede = true;
                set3 = con.createStatement();
                set3.executeUpdate("Update reservar set estado='finalizada' where id='" + id + "'");

                set4 = con.createStatement();
                rs4 = set4.executeQuery("select * from reservar where id='" + id + "';");
                rs4.next();

                String fFi[] = rs.getString("fechaI").split("-");
                //int fechaF = Integer.parseInt(fF[0] + fF[1] + fF[2]);
                String[] horaFi = rs.getString("horaI").split(":");
                //int hora = Integer.parseInt(horaF[0] + horaF[1]);

                Date hoyi = new Date();
                int añoi = Integer.parseInt(fFi[0]);
                int mesi = Integer.parseInt(fFi[1]);
                int diai = Integer.parseInt(fFi[2]);
                int horai = Integer.parseInt(horaFi[0]);
                int mini = Integer.parseInt(horaFi[1]);

                String fF[] = rs4.getString("fechaF").split("-");
                //int fechaF = Integer.parseInt(fF[0] + fF[1] + fF[2]);
                String[] horaF = rs4.getString("horaF").split(":");
                //int hora = Integer.parseInt(horaF[0] + horaF[1]);
                Date fechadadaI = new Date(añoi - 1900, mesi - 1, diai, horai, mini);

                Date hoy = new Date();
                int año = Integer.parseInt(fF[0]);
                int mes = Integer.parseInt(fF[1]);
                int dia = Integer.parseInt(fF[2]);
                int hora = Integer.parseInt(horaF[0]);
                int min = Integer.parseInt(horaF[1]);
                System.out.println(año);
                System.out.println(Integer.parseInt(horaF[0]));
                long i = System.currentTimeMillis();
                System.out.println("i es :" + i);

                Date fechadada = new Date(año - 1900, mes - 1, dia, hora, min);
                // Date fechadada=new Date(2019, 1, 10, 4, 5);
                System.out.println(fechadada.getTime());
                System.out.println(hoy.getTime());
                System.out.println(hoy.toString());
                System.out.println(fechadada.toString());

                long josupi = i - fechadada.getTime();
                long precio = fechadada.getTime() - fechadadaI.getTime();

                if (josupi >= 0) { //SI tiene recargo
                    double recargo1 = josupi / 60000;
                    double recargo2 = recargo1 * (0.5);

                    double precio1 = (precio / 3600000) * 2;
                    s.setAttribute("elrecargo", recargo2);
                    s.setAttribute("elprecio", precio1);
                }
                if (josupi < 0) { //NO tiene recargo
                    double recargo3 = 0;
                    double recargo1 = josupi / 60000;
                    double recargo2 = recargo1 * (0.5);
                    double precio1 = (precio / 3600000) * 2;
                    s.setAttribute("elprecio", precio1);
                    s.setAttribute("elrecargo", recargo3);
                }
                //AQUI VA EL CALCULO DEL RECARGO
                response.sendRedirect("pantallaRecargo.jsp");
            } else if (sepuede == false) {
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
