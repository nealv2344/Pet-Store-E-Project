package businessObjs;

import Data.Access;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Customer extends PersonObj {
    
    //Properties
    int cardInfo;
    
    //Constructors
    public Customer() {
        
        //call overloaded constructor
        this("", "", "", "", "", "", 0);
    }    
    
    public Customer(String Id, String fname, String lname, String email, String address, String password,  int cardInfo) {
        
        //call super constructor
        super(Id, fname, lname, email, address,password);
        
        this.cardInfo = cardInfo;
        
    }     

    //Getters and Setters
    public int getCardInfo() {
        return cardInfo;
    }

    public void setCardInfo(int cardInfo) {
        this.cardInfo = cardInfo;
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
    
    public void display(){
        System.out.println("ID: "+getId());
        System.out.println("FirstName: "+getFname());
        System.out.println("LastName: "+getLname());
        System.out.println("Email: "+getEmail());
        System.out.println("Address: "+getAddress());
        System.out.println("Password: "+getPw());
        System.out.println("CardInfo: "+getCardInfo());
    }
    
    public static void main (String [] args){
        
//        Customer c1 = new Customer();
//        c1.selectDB("C002");
//        c1.display();
//        Customer c2 = new Customer("C200","Nela","Supp","nelaSup@gmail.com","123 william dr","pass123", 10276);
//        c2.display();
//        c2.insertDB();

          Customer c3 = new Customer();
          c3.selectDB("C006");
          c3.deleteDB();
          
    }


}
