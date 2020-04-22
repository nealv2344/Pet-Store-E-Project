/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Data.Access;
import businessObjs.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public class adminHandler extends HttpServlet {

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
        
        //url for admin page
        String adminPage = "AdminPage.jsp";
        //urls for form handling
        String orderContents = "aFormResults/orderLookup.jsp";
        String cancelOrder = "aFormResults/cancelOrder.jsp";
        String productInfo = "aFormResults/productInfo.jsp";
        String addProduct = "aFormResults/addProduct.jsp";
        String deleteProduct = "aFormResults/deleteProduct.jsp";
        String deleteCustomer = "aFormResults/deleteCustomer.jsp";
        String addAdmin = "aFormResults/addAdmin.jsp";
        String deleteAdmin = "aFormResults/deleteAdmin.jsp";
        
        String redirect = "";
        
        try (PrintWriter out = response.getWriter()) {
                       
            
            //gets action attribute from adminPage
            String action = request.getParameter("action");
            
            if(action.equalsIgnoreCase("adminLogin")){
                //#1-get id/password from html file
                String id = request.getParameter("inputId");
                String pw = request.getParameter("inputPassword");
                boolean result = checkAdmin(id,pw);
                
                if(result == true){
                    //#3-create new object
                    Administrator a1 = new Administrator();
                    a1.selectDB(id);

                    //#4-make any decisions

                    //#5-put object into session
                    request.setAttribute("a1",a1);
                    System.out.println("Administrator added to session");
                    //#6-use requestDispatcher to forward onto next page  
                    redirect = adminPage;
                }else{
                    
                }
                
                
            }else if(action.equalsIgnoreCase("orderLookup")){
                //gets order number from adminPage 
                String oNum = request.getParameter("orderNum"); 
                int orderNum = Integer.parseInt(oNum);
                boolean result = checkOrderNum(orderNum);                
                
                if(result ==true){
                Order o1 = new Order();
                o1.selectDB(orderNum);
                o1.retrieveOrderContentsDB();

                OrderHistory oH = new OrderHistory();    
                oH.retrieveOrdersDB(o1.getCustID());
                
                request.setAttribute("o1",o1.items.items);
                request.setAttribute("oH",oH.orders);
                
                redirect = orderContents;
            }else{
                System.out.println("ordernumber does not exit");
            }
            } else if(action.equalsIgnoreCase("cancelOrder")){
                
                
                
                
                //cancel order goes here
                //if validated, not allowed to cancel
                //otherwise, proceed to cancel
                
                
                
                
                
            } else if(action.equalsIgnoreCase("productInfo")){
                //gets product sku from adminPage
                String productSku = request.getParameter("productSku");
                int pSku = Integer.parseInt(productSku);
                
                Product p1 = new Product();
                p1.selectDB(pSku);
                
                request.setAttribute("p1",p1);
                System.out.println("Displaying product info for sku"+p1.getName());
                
                redirect= productInfo;
                
            } else if(action.equalsIgnoreCase("addProduct")){
                //string to int conversion
                String productSku = request.getParameter("productSku");
                int pSku = Integer.parseInt(productSku);
                
                String pName = request.getParameter("productName");
                //string to double conversiion
                String tempPrice = request.getParameter("productPrice");
                double pPrice = Double.parseDouble(tempPrice);
                //string to integer conversion
                String tempStock = request.getParameter("productStock");
                int pStock = Integer.parseInt(tempStock);
                //db access
                Product p1 = new Product();
                p1.insertDB(pSku, pName, pPrice, pStock);
                request.setAttribute("p1", p1);
                System.out.println("Product"+p1.getName()+"added successfully");
                //sets url path
                redirect = addProduct;
                
            } else if(action.equalsIgnoreCase("deleteProduct")){
                //gets product sku from adminPage
                String productSku = request.getParameter("productSku");
                int pSku = Integer.parseInt(productSku);
                //db access
                Product p1 = new Product();
                p1.selectDB(pSku);
                p1.deleteDB();
                //sets url path
                redirect = deleteProduct;
                
            } else if(action.equalsIgnoreCase("deleteCustomer")){
                //gets customer id from adminPage
                String custId = request.getParameter("customerId");
                
                Customer c1 = new Customer();
                c1.selectDB(custId);
                c1.deleteDB();
                
                redirect = deleteCustomer;
            } else if(action.equalsIgnoreCase("customerHistory")){
                //gets customer id from adminPage
                String custId = request.getParameter("customerId");
                
                
                
                //customer history
                //should display table
                //--orders--orderContents--Total--
                
                
                
                
                
            } else if(action.equalsIgnoreCase("addAdmin")){
                String adminId = request.getParameter("adminId");
                String fName = request.getParameter("fName");
                String lName = request.getParameter("lName");
                String address = request.getParameter("address");
                String password = request.getParameter("password");
                String email = "";
                
                Administrator a1 = new Administrator(adminId,fName,lName,address,password,email);
                a1.insertDB();
                a1.display();
                request.setAttribute("a1",a1);
                
                System.out.println("Admin "+a1.getFname()+" successfully Added");
                
                redirect = addAdmin;
                
            } else if(action.equalsIgnoreCase("deleteAdmin")){
                String adminId = request.getParameter("adminId");
                
                Administrator a1 = new Administrator();
                a1.selectDB(adminId);
                a1.deleteDB();
                
                redirect = deleteAdmin;
            }
            
            RequestDispatcher rd = request.getRequestDispatcher(redirect);
                rd.forward(request, response);
                        
        }
    }
    
    
    
    //validation method for order number
    protected boolean checkOrderNum(int orderNum){
        int validate = 0;
        
        try{
            Access dbAccess = new Access();
            
            String sql = "SELECT * FROM Orders WHERE OrderNumber = "+orderNum;
            System.out.println(sql);
            
            ResultSet rs = dbAccess.getStatement().executeQuery(sql);
            
            if(rs.next()){
                validate=rs.getInt(1);
            }
            dbAccess.close();
            if(validate == orderNum){
                return true;
            }else{
                return false;
            }
            
            
            
        }catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
            return false;
        }
        
    }
    
    //login validation for administrators
    protected boolean checkAdmin(String id,String pass){
        String validate = null;
        
        try{
            Access dbAccess = new Access();
            
            System.out.println(id+" "+pass);
            //execute statment
            String sql = "SELECT * FROM Administrators WHERE ID='"+id+"' and Password='"+pass+"'";             
            System.out.println(sql);
            ResultSet rs = dbAccess.getStatement().executeQuery(sql);
            
            if(rs.next()){
                validate=rs.getString(5);
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
