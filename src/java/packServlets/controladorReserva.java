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
        String email= (String)s.getAttribute("emaillogin"); 
        
        
        con = BD.getConexion();


        try {
            set = con.createStatement(); 
            set.executeUpdate("INSERT INTO reservar (email,matricula,horaI,fechaI,horaF,fechaF,lugar) VALUES  ('" + email + "','" + cocheelegido + "','" + horasalida + "','" + fechasalida + "','" + horallegada + "','" + fechallegada + "','" + ellugar + "'"  + ")");
        } catch (SQLException ex) {
            Logger.getLogger(controladorReserva.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        System.out.println(fechasalida);
        System.out.println(horallegada);
        System.out.println(cocheelegido); 
        System.out.println(ellugar);
        
        processRequest(request, response); 
        
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