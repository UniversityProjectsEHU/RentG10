/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packServlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "controladorReserva", urlPatterns = {"/controladorReserva"})
public class controladorReserva extends HttpServlet {

    private Connection con;
    private Statement set;
    private ResultSet rs;
    String cad;

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
            out.println("<title>Servlet controladorReserva</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet controladorReserva at " + request.getContextPath() + "</h1>");
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
        String cocheelegido = request.getParameter("coches");
        String fechasalida = request.getParameter("fechasalida");
        String horasalida = request.getParameter("horasalida");
        String fechallegada = request.getParameter("fechallegada");
        String horallegada = request.getParameter("horallegada");
        String ellugar = request.getParameter("lugares");
        HttpSession s = request.getSession(true);
        String email = (String) s.getAttribute("emaillogin");
        boolean correcta = false;

        con = BD.getConexion();
        System.out.println(fechasalida);
        System.out.println(horallegada);
        System.out.println(cocheelegido);
        System.out.println(ellugar);

        if (email == null) {
            request.getRequestDispatcher("pantallaavisoReserva.jsp").forward(request, response);
        }

        String[] fechaS = fechasalida.split("-");
        String[] fechaL = fechallegada.split("-");
        String fechaSal = fechaS[0] + fechaS[1] + fechaS[2];
        String fechaLleg = fechaL[0] + fechaL[1] + fechaL[2];
        int fechaSalEntero = Integer.parseInt(fechaSal);
        int fechaLlegEntero = Integer.parseInt(fechaLleg);

        Date today = new Date();
        int dd = today.getDate();
        int mm = today.getMonth() + 1; //January is 0!
        int yyyy = today.getYear() + 1900;
        String dia;
        String mes;
        if (dd < 10) {
            dia = "0" + Integer.toString(dd);
        } else {
            dia = Integer.toString(dd);
        }
        if (mm < 10) {
            mes = "0" + Integer.toString(mm);
        } else {
            mes = Integer.toString(mm);
        }

        String año = Integer.toString(yyyy);
        String hoy = año + mes + dia;
        int hoy2 = Integer.parseInt(hoy);

        System.out.println(fechaSalEntero);
        System.out.println(fechaLlegEntero);
        System.out.println(hoy2);
        System.out.println(dia);
        System.out.println(mes);
        System.out.println(año);

        int hora = today.getHours();
        int min = today.getMinutes();
        String hora2 = Integer.toString(hora);
        String min2 = Integer.toString(min);
        String hoy3 = hora2 + min2;
        int horahoy = Integer.parseInt(hoy3);

        String[] horaS = horasalida.split(":");
        String[] horaL = horallegada.split(":");
        String horaSal = horaS[0] + horaS[1];
        String horaLleg = horaL[0] + horaL[1];
        int horaSalEntero = Integer.parseInt(horaSal);
        int horaLlegEntero = Integer.parseInt(horaLleg);

        if (hoy2 < fechaSalEntero && fechaSalEntero < fechaLlegEntero) {
            correcta = true;
        } else if (hoy2 == fechaSalEntero && fechaSalEntero < fechaLlegEntero) {
            if (horahoy <= horaSalEntero) {
                correcta = true;
            }
        }

        if (correcta == true) {
            try {
                set = con.createStatement();
                set.executeUpdate("INSERT INTO reservar (email,matricula,horaI,fechaI,horaF,fechaF,lugar) VALUES  ('" + email + "','" + cocheelegido + "','" + horasalida + "','" + fechasalida + "','" + horallegada + "','" + fechallegada + "','" + ellugar + "'" + ")");
            } catch (SQLException ex) {
                Logger.getLogger(controladorReserva.class.getName()).log(Level.SEVERE, null, ex);
            }

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
                        + "                    <img src=\"imagenes/reservacon.PNG\" id=\"imgtitulo\">\n"
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
        } else {
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
                        + "                    <img src=\"imagenes/reservasin.PNG\" id=\"imgtitulo\">\n"
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
        processRequest(request, response);
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
