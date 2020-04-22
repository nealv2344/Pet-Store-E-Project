package businessObjs;

import Data.Access;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Order extends Cart{
    
    //Properties
    int orderNo;
    
    //Constructors
    //
    public Order() {
        super();
        orderNo = 0;
            
    } 
    //generates its orderNo from DB
    public Order(String custID, ItemList items){
        
        //calls super constructor and initiates that
        super(custID, items);
        //gets total from ItemList and fills that property
        this.total = items.getTotal();
        //fills orderNo from DB
        this.orderNo = generateOrderNumber();
        
    }
    //
    public Order(int orderNo, String custID, ItemList items) {
        
        //calls super constructor and initiates that
        super(custID, items);
        //gets total from ItemList and fills that property
        this.total = items.getTotal();       
        //fills orderNo from parameters
        this.orderNo = orderNo;
    }        

    //Getters and Setters
    //
    public int getOrderNo() {
        return orderNo;
    }
    //
    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
    }    

    //DB methods  - Needs to be finished. SelectDB and InsertDB 
    //
    //Select Order from database and populate object with it
    public void selectDB(int orderNo) {
        try {
            Access databaseAccess = new Access();
            
            //setup statement and execute it
            String sql = "select * from Orders WHERE OrderNumber = '" + orderNo + "'";
            ResultSet rs = databaseAccess.getStatement().executeQuery(sql);
            
            rs.next();
            
            //set properties
            setOrderNo(rs.getInt(1));
            setCustID(rs.getString(2));
            
            
            
            
            //debug some info to console
            System.out.println("Order " + orderNo + " Successfully selected" + System.lineSeparator());
            
            //close connection
            databaseAccess.close();                        
        } 
        catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Exception caught - " + ex + System.lineSeparator());
            ex.printStackTrace();
        }
    }
    //Insert populated Order Object into database
    public void insertDB() {
        try {
             Access databaseAccess = new Access();
            
            //setup statment
            String sql = "INSERT INTO Orders " +
                         "VALUES ('" + getOrderNo()+ "', '" + getCustID()+ "', '" + false + "')";             
            
            //execute insertion                         
            int num = databaseAccess.getStatement().executeUpdate(sql);
            
            //deal with result
            if (num == 1){
                
                //debug to console
                System.out.println("Insert successful!" + System.lineSeparator());
                
            }else {
                //debug to console
                System.out.println("Insert failed!" + System.lineSeparator());
            }
            
            //insert order contents into OrderContents DB
            insertOrderContentsDB();
            
            databaseAccess.close();

        }

        catch (ClassNotFoundException | SQLException e){
            System.out.println("Exception caught - " + e + System.lineSeparator());
        }
    }
    //Delete populated Order Object into database
    public void deleteDB() {
        try{
            Access databaseAccess = new Access();
            
            //setup statment
            String sql = "DELETE FROM Orders WHERE OrderNo = " + getOrderNo();          
            
            //execute Deletion                                               
            int num = databaseAccess.getStatement().executeUpdate(sql);
            
            //deal with result
            if (num == 1){
                //debug to console
                System.out.println("Deletion successful!" + System.lineSeparator());
                //reset properties to blank values
                this.setOrderNo(0);
                this.setCustID("");
                this.setItems(new ItemList());
                this.setTotal(0);
   
            }else {
                //debug to console
                System.out.println("Deletion failed!" + System.lineSeparator());
            }                        
        } 
        catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Exception caught - " + ex + System.lineSeparator());
        }
    }
    //Inserts Order contents into OrderContents Table
    public void insertOrderContentsDB(){
        
        try{
            Access databaseAccess = new Access();
            
            for(int i = 0; i < items.count; i++){
                //set up string
                String sql = "INSERT INTO OrderContents " +
                         "VALUES ('" + items.get(i).getSku() + "', '" + getOrderNo()+ "', '" + items.get(i).getQuantity()+ "')";
                //execute insertion                         
                int num = databaseAccess.getStatement().executeUpdate(sql);

                //deal with result
                if (num == 1){

                    //debug to console
                    System.out.println("Insert successful!" + System.lineSeparator());

                }else {
                    //debug to console
                    System.out.println("Insert failed!" + System.lineSeparator());
                }
            }                        
            
            //debug some info to console
            System.out.println("Order contents for " + getOrderNo() + " Successfully Inserted" + System.lineSeparator());
            
            //close connection
            databaseAccess.close();
            
        }
        catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Exception caught - " + ex + System.lineSeparator());
        }
        
        
    }
    //retrieves order contents from OrderContents Table
    public void retrieveOrderContentsDB(){
        
        try{
            Access databaseAccess = new Access();
            
            //setup statement and execute it
            String sql = "select * from OrderContents WHERE OrderNumber = '" + getOrderNo() + "'";
            ResultSet rs = databaseAccess.getStatement().executeQuery(sql);
            
            //deal with results
            while(rs.next()){
                //create new Item object to get product information from product table
                Item i = new Item();      
                i.selectDB(rs.getInt(1));
                //set quantity from results
                i.setSku(rs.getInt(1));
                i.setQuantity(rs.getInt(3));
                
                //add item to ItemList
                items.add(i);
            }                     
            
            //debug some info to console
            System.out.println("Order contents for " + getOrderNo() + " Successfully retrieved" + System.lineSeparator());
            
            //close connection
            databaseAccess.close();
            
        }
        catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Exception caught - " + ex + System.lineSeparator());
        }
        
        
    }
    //
    
    //Utility Methods
    //
    //Generates Order Number from DB (Checks max order number and adds 1 to it), 5 digit number beginning with 1
    private int generateOrderNumber(){
        
        //return value
        int resultValue = 0;
        
        //access DB
        try {
            Access databaseAccess = new Access();
            
            //setup statement and execute it
            String sql = "select MAX(OrderNo) from Orders";
            ResultSet rs = databaseAccess.getStatement().executeQuery(sql);
            
            rs.next();
            
            //use result to generate return OrderNo
            resultValue = rs.getInt(1) + 1;
            
            //debug some info to console
            System.out.println("Orders Successfully selected and OrderNo generated" + System.lineSeparator());
                        
            //close connection
            databaseAccess.close();                        
        } 
        catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Exception caught - " + ex + System.lineSeparator());
        }
        
        return resultValue;
        
    }
    //
    @Override
    public void display(){
        System.out.println("   Order Information   " + System.lineSeparator() +
                           "=========================" + System.lineSeparator() +
                           "Order Number: " + getOrderNo()+ System.lineSeparator() + 
                           "Customer ID: " + getCustID() + System.lineSeparator() +                            
                           "Order Total: " + getTotal()+ System.lineSeparator());
    
    }
    
    public static void main(String []args){
        
        
        Order o1 = new Order();
        o1.selectDB(10001);
        o1.display();
        o1.retrieveOrderContentsDB();
        
        OrderHistory oH = new OrderHistory();    
        oH.retrieveOrdersDB(o1.getCustID());
        
        oH.orders.forEach((o)-> {
            System.out.println(o.getCustID());
            System.out.println(o.getOrderNo());
            
        });
        System.out.println("=============");
        o1.items.items.forEach((item)-> {            
            System.out.println("sku: "+ item.getSku());
            System.out.println("quantity: "+ item.getQuantity());
        });

}
}