package businessObjs;

import Data.Access;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Customer extends PersonObj {
    
    //Properties
    int cardInfo;
    OrderHistory orderHistory = new OrderHistory();
    //Constructors
    public Customer() {
        
        //call overloaded constructor
        this("", "", "", "", "", "", 0);
    }    
    
    public Customer(String Id, String fname, String lname, String email, String address, String password,  int cardInfo) {
        
        //call super constructor
        super(Id, fname, lname, email, address,password);       
        this.cardInfo = cardInfo;
        this.orderHistory.retrieveOrdersDB(Id);
        
    }     

    //Getters and Setters
    public int getCardInfo() {
        return cardInfo;
    }

    public void setCardInfo(int cardInfo) {
        this.cardInfo = cardInfo;
    }
    
    public OrderHistory getHistory(){
        return orderHistory;
    }
    
    public void setHistory(OrderHistory orderHistory){
        this.orderHistory = orderHistory;
    }
           
    //Database access methods
    //
    //Select Customer from database and populate object with it
    @Override
    public void selectDB(String ID) {
        try{
            Access databaseAccess = new Access();
            
            //execute statment
            String sql = "select * from Customers WHERE CustomerID = '" + ID + "'"; 
            System.out.println(sql);            
            ResultSet result = databaseAccess.getStatement().executeQuery(sql);            

            result.next();
            
            //set properties
            setId(result.getString(2));
            setFname(result.getString(1));
            setLname(result.getString(3));
            setEmail(result.getString(7));
            setAddress(result.getString(4));
            setPw(result.getString(6));
            setCardInfo(result.getInt(5));   
            
            
            this.orderHistory.retrieveOrdersDB(ID);
            
            System.out.println("Customer " + ID + " Successfully selected" + System.lineSeparator());
            
            databaseAccess.close();            
        } 
        catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Exception caught - " + ex + System.lineSeparator());
        }
    }

    @Override
    public void insertDB() {
        try{
            Access databaseAccess = new Access();
            
            //setup statment
            String sql = "INSERT INTO Customers (CustomerID,FirstName,LastName,Address,Password,PaymentCard,Email)" +
                         "VALUES ('" + getId()+ "', '" + getFname()+ "', '" + getLname()+ "', '" +
                                    getAddress()+ "', '" + getPw()+ "', '" + getCardInfo()+ "', '" +getEmail() + "')";             
            
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
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void deleteDB() {
       try{
            Access databaseAccess = new Access();
            
            //setup statment
            String sql = "DELETE FROM Customers WHERE CustomerID = '" + getId() + "'"; 
            System.out.println(sql);         
            //execute Deletion                         
            int num = databaseAccess.getStatement().executeUpdate(sql);
            
            //deal with result
            if (num == 1){
                //debug to console
                System.out.println("Deletion successful!" + System.lineSeparator());
                
                //reset properties
                this.setId("");
                this.setPw("");
                this.setFname("");
                this.setLname("");
                this.setAddress("");
                this.setEmail("");
            }else {
                //debug to console
                System.out.println("Deletion failed!" + System.lineSeparator());
            }
            
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Exception caught - " + ex + System.lineSeparator());
            ex.printStackTrace();
        }
    }
    
    //autogenerates id number
    public static int getIdNum(){
       
        String test="";
        try{
                Access access = new Access();
                String sql = "Select CustomerID From Customers Order By CustomerID desc";
                
                ResultSet rs = access.getStatement().executeQuery(sql);
                
                rs.next();
                test = rs.getString(1);
                
                test = test.substring(1);
                
                int idNum = Integer.parseInt(test)+1;
                
        return idNum;        
            
        }catch(ClassNotFoundException | NumberFormatException | SQLException e){
            return 1;
        }
    }
    
    public void display(){
        System.out.println("ID: "+getId());
        System.out.println("FirstName: "+getFname());
        System.out.println("LastName: "+getLname());
        System.out.println("Email: "+getEmail());
        System.out.println("Address: "+getAddress());
        System.out.println("Password: "+getPw());
        System.out.println("CardInfo: "+getCardInfo());
        this.orderHistory.display();
    }
    
    public static void main (String [] args){
        
//        Customer c1 = new Customer();
//        c1.selectDB("C003");
//        c1.orderHistory.orders.forEach((o)->{
//            System.out.println(o.getOrderNo());
//            
//            o.items.items.forEach((item)->{
//                System.out.println(item.getName());
//                System.out.println(item.getQuantity());
//                System.out.println(item.getPrice());
//            });
//            System.out.println(o.getTotal());
//        });
        

//            String an = "";
//            int count = getIdNum();
//
//            String idNum = Integer.toString(count);
//
//            if(count<10){
//                an="C00";
//            }else if(10<=count){
//                an="C0";
//            }
//            String id = an+idNum;
//            System.out.println(id);
    }


}
