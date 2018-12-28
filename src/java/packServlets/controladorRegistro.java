/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packServlets;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;
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
    private File file;
    boolean hayFoto;
    boolean hayDNI;
    boolean hayTele;

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
        if (tele == "") {
            try {
                set = con.createStatement();
                set.executeUpdate("INSERT INTO clientes (email,contrasena,nombre,dni)VALUES ('" + email + "','" + contra + "','" + nombre + "','" + dni + "'" + ")");

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
        } else {
            int telefono2 = new Integer(tele);
            try {
                set = con.createStatement();
                set.executeUpdate("INSERT INTO clientes VALUES ('" + email + "','" + contra + "','" + nombre + "','" + dni + "'," + telefono2 + ")");

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
        hayFoto = false;
        hayDNI = false;
        hayTele = false;
        con = BD.getConexion();

//        String email =request.getParameter("correo");
//        String contra = request.getParameter("contrasena");
//        String nombre = request.getParameter("nombre");
//        String dni = request.getParameter("dni");
//        String tele = request.getParameter("telefono");
//        System.out.println(email);
        //int telefono2=new Integer(tele);
        //Part filePart=request.getPart("archivos");
// Se recorren todos los items, que son de tipo FileItem
        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);

// req es la HttpServletRequest que recibimos del formulario.
// Los items obtenidos serán cada uno de los campos del formulario,
// tanto campos normales como ficheros subidos.
        List items;
        try {
            set = con.createStatement();
            rs = set.executeQuery("select count(email)+1 from clientes");
            rs.next();
            int id = rs.getInt(1);
            items = upload.parseRequest(new ServletRequestContext(request));
            Iterator itr = items.iterator();
            LinkedList a = new LinkedList();
            while (itr.hasNext()) {
                FileItem uploaded = (FileItem) itr.next();
                if (!uploaded.isFormField()) {

                    hayFoto = true;
                    //Funciona con direcciones absolutas
                    file = new File("C:\\Users\\Sergio\\Documents\\NetBeansProjects\\Sprint2\\RentG10\\web\\imagenes\\" + id + ".png");
                    //No funciona con relativas
                    //file = new File("imagenes/"+id+".png");

                    uploaded.write(file);
                    System.out.println(file);
                } else {
                    String correo = uploaded.getString();
                    String field = uploaded.getFieldName();

                    System.out.println(correo);
                    a.add(correo);

                }
            }
            hayDNI = false;
            hayTele = false;
            System.out.println(a.get(3).toString());
            System.out.println(a.get(4).toString());

            if (!"".equals(a.get(3).toString())) {
                hayDNI = true;
            }
            if (!"".equals(a.get(4).toString())) {
                hayTele = true;
            }
            if (hayDNI && hayTele) {
                System.out.println("1");
                set.executeUpdate("INSERT INTO clientes VALUES ('" + a.get(0) + "','" + a.get(1) + "','" + a.get(2) + "','" + a.get(3) + "','" + a.get(4) + "','" + id + ".png');");
            } else if (!hayDNI && hayTele) {
                System.out.println("2");
                set.executeUpdate("INSERT INTO clientes(email,contrasena,nombre,movil,path) VALUES ('" + a.get(0) + "','" + a.get(1) + "','" + a.get(2) + "','" + a.get(4) + "','" + id + ".png');");
            } else if (hayDNI && !hayTele) {
                System.out.println("3");
                set.executeUpdate("INSERT INTO clientes(email,contrasena,nombre,dni,path) VALUES ('" + a.get(0) + "','" + a.get(1) + "','" + a.get(2) + "','" + a.get(3) + "','" + id + ".png');");
            } else if (!hayDNI && !hayTele) {
                System.out.println("4");
                set.executeUpdate("INSERT INTO clientes(email,contrasena,nombre,path) VALUES ('" + a.get(0) + "','" + a.get(1) + "','" + a.get(2) + "','" + id + ".png');");
            }

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
        } catch (FileUploadException ex) {
            Logger.getLogger(controladorRegistro.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(controladorRegistro.class.getName()).log(Level.SEVERE, null, ex);
        }

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
