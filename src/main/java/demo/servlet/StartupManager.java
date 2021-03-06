package demo.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Venkateswara VP
 */
public class StartupManager extends HttpServlet {
   
   /**
    * Auto Generated
    */
   private static final long serialVersionUID = -3613260145840216958L;
   /** The log. */
   private static Log LOG = LogFactory.getLog(StartupManager.class.getName());
   
   /**
    * Load on startup.
    */
   @Override
   public void init() {
      LOG.trace("init() called.");
   }
   
   /**
    * Processes requests for both HTTP
    * <code>GET</code> and
    * <code>POST</code> methods.
    *
    * @param request servlet request
    * @param response servlet response
    * @throws ServletException if a servlet-specific error occurs
    * @throws IOException if an I/O error occurs
    */
   protected void processRequest(final HttpServletRequest request, final HttpServletResponse response)
         throws ServletException, IOException {
      response.setContentType("text/html;charset=UTF-8");
      LOG.debug("processRequest() called method " + request.getMethod());
      final PrintWriter out = response.getWriter();
      try {
         /* TODO output your page here. You may use following sample code. */
         out.println("<!DOCTYPE html>");
         out.println("<html>");
         out.println("<head>");
         out.println("<title>Servlet StartupManager</title>");
         out.println("</head>");
         out.println("<body>");
         out.println("<h1>Servlet StartupManager at " + request.getContextPath() + "</h1>");
         out.println("</body>");
         out.println("</html>");
      } finally {
         out.close();
      }
   }
   
   // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
   /**
    * Handles the HTTP
    * <code>GET</code> method.
    *
    * @param request servlet request
    * @param response servlet response
    * @throws ServletException if a servlet-specific error occurs
    * @throws IOException if an I/O error occurs
    */
   @Override
   protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
         throws ServletException, IOException {
      processRequest(request, response);
   }
   
   /**
    * Handles the HTTP
    * <code>POST</code> method.
    *
    * @param request servlet request
    * @param response servlet response
    * @throws ServletException if a servlet-specific error occurs
    * @throws IOException if an I/O error occurs
    */
   @Override
   protected void doPost(final HttpServletRequest request, final HttpServletResponse response)
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
