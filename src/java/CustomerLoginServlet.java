/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Data.Access;
import businessObjs.Administrator;
import businessObjs.Customer;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Neal Valdez
 */
public class CustomerLoginServlet extends HttpServlet {

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
            
            //#1-get id/password from html file
            String email = request.getParameter("inputEmail");
            String pw = request.getParameter("inputPassword");
            boolean result = checkCust(email,pw);
            //#2-get objects from session if needed
            
            
            if(result == true){
                //#3-create new object
                Customer c1 = new Customer();
                c1.selectDB("A001");
                
                //#4-make any decisions

                //#5-put object into session
                HttpSession ses2;
                ses2 = request.getSession();
                ses2.setAttribute("c1",c1);
                System.out.println("Customer added to session");
                //#6-use requestDispatcher to forward onto next page  
                RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
                rd.forward(request, response);
            }else{
                RequestDispatcher rd = request.getRequestDispatcher("loginError.html");
                rd.include(request, response);
            }            
        }
    }
    
    protected boolean checkCust(String email,String pass){
        String validate = null;
        
        try{
            Access dbAccess = new Access();
            
            System.out.println(email+" "+pass);
            //execute statment
            String sql = "SELECT * FROM Customers WHERE Email='"+email+"' and Password='"+pass+"'";             
            System.out.println(sql);
            ResultSet rs = dbAccess.getStatement().executeQuery(sql);
            
            if(rs.next()){
                validate=rs.getString(6);
            }
            dbAccess.close();
            if(validate.equals(pass)){
                return true;
            }
            else{
                return false;
            }

            
        }catch(Exception e){
            e.printStackTrace();
            return false;
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
