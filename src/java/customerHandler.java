/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Data.Access;
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
public class customerHandler extends HttpServlet {

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
        
            String success = "Success.jsp";
            String homePage = "index.jsp";
            
            String redirect = "";
            
        try (PrintWriter out = response.getWriter()) {
            
            String action = request.getParameter("action");
            
            if(action.equalsIgnoreCase("customerSignUp")){
                //get inputs from html form
                String id = "C007";
                String newId = String.valueOf(Integer.parseInt(id)+1);

                System.out.println(newId);
                String fname = request.getParameter("FirstName");
                String lname = request.getParameter("LastName");
                String email = request.getParameter("Email");
                String address = request.getParameter("Address");
                String password = request.getParameter("Password");

                //get objects from session if needed

                //create new objects
                Customer c1 = new Customer(newId,fname,lname,email,address,password,0);
                c1.insertDB();
                //make any decisions

                //put objects back into session
                HttpSession ses1;
                ses1 = request.getSession();
                ses1.setAttribute("c1", c1);
                System.out.println("Customer addded to session");

                redirect = success;
            }else if(action.equalsIgnoreCase("customerLogin")){
                //#1-get id/password from html file
                String Id = request.getParameter("inputId");
                String pw = request.getParameter("inputPassword");
                boolean result = checkCust(Id,pw);
                //#2-get objects from session if needed


                if(result == true){
                    
                    //#3-create new object
                    Customer c1 = new Customer();
                    c1.selectDB(Id);

                    //#4-make any decisions

                    //#5-put object into session
                    HttpSession ses2;
                    ses2 = request.getSession();
                    ses2.setAttribute("c1",c1);
                    System.out.println("Customer added to session");
                    //#6-use requestDispatcher to forward onto next page  
                    redirect = homePage;
                }else{
                    RequestDispatcher rd = request.getRequestDispatcher("loginError.html");
                    rd.forward(request, response);
                }
            }
            
            RequestDispatcher rd = request.getRequestDispatcher(redirect);
            rd.forward(request, response);
        }
    }
    
    protected boolean checkCust(String Id,String pass){
        String validate = null;
        
        try{
            Access dbAccess = new Access();
            
            System.out.println(Id+" "+pass);
            //execute statment
            String sql = "SELECT * FROM Customers WHERE CustomerID='"+Id+"' and Password='"+pass+"'";             
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
