package businessObjs;

import Data.Access;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderHistory {
    
    //Properties
    int count;
    public ArrayList<Order> orders;

    //Constructors
    public OrderHistory() {
        this.orders = new ArrayList<>();
        this.count = 0;
    }                 	

    //Getters and Setters
    public int getCount() {
        return count;
    }
    //
    public void setCount(int count) {
        this.count = count;
    }
    //
    public ArrayList<Order> getOrders() {
        return orders;
    }
    //
    public void setOrders(ArrayList<Order> O) {
            orders = O;
    }

    //Utility
    public void addOrder(Order order) {
        orders.add(order);
        count++;
    }
    //
    public void removeOrder(Order order){
        orders.remove(order);
        count--;
    }
    //
    public void display() {
        
        System.out.println("   Order History   " + System.lineSeparator() +
                           "=========================" + System.lineSeparator() +
                           "=========================" + System.lineSeparator());
        if (count == 0){
            System.out.println("No Orders Found");
        }
        else {
            orders.forEach((O) -> {
                O.display();
            });
        }
        
    }

    //DB methods
    public void retrieveOrdersDB (String custID){
        try {
            Access databaseAccess = new Access();
            
            //setup statement and execute it
            String sql = "select * from Orders WHERE CustomerID = '" + custID + "'";
            ResultSet rs = databaseAccess.getStatement().executeQuery(sql);
            
             //geal with results
            while(rs.next()){
                //create new order object to manipulate data
                Order o = new Order();       
                
                //set properties
                o.setCustID(rs.getString(2));
                o.setOrderNo(rs.getInt(1));
                o.retrieveOrderContentsDB();
                o.setTotal(o.items.total);
                
                //add order object to orders list
                orders.add(o);
            }
            
            //debug some info to console
            System.out.println("Orders for " + custID + " Successfully selected" + System.lineSeparator());
            
            //close connection
            databaseAccess.close();                        
        } 
        catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Exception caught - " + ex + System.lineSeparator());
        }
    }

	
}