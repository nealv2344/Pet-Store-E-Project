package Business;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Customer extends PersonObj {
    
    //Properties
    String cardInfo;
    
    //Constructors
    public Customer() {
        
        //call overloaded constructor
        this(0, "", "", "", "", "", "");
    }    
    
    public Customer(int Id, String fname, String lname, String email, String password, String address, String cardInfo) {
        
        //call super constructor
        super(Id, fname, lname, email, password, address);
        
        this.cardInfo = cardInfo;
        
    }     

    //Getters and Setters
    public String getCardInfo() {
        return cardInfo;
    }

    public void setCardInfo(String cardInfo) {
        this.cardInfo = cardInfo;
    }
           
    //Database access methods
    //
    //Select Customer from database and populate object with it
    @Override
    public void selectDB(int ID) {
        try{
            Access databaseAccess = new Access();
            
            //execute statment
            String sql = "select * from Customers WHERE CustID = '" + ID + "'";             
            ResultSet result = databaseAccess.getStatement().executeQuery(sql);            

            result.next();
            
            //set properties
            setId(result.getInt(1));
            setPw(result.getString(2));
            setFname(result.getString(3));
            setLname(result.getString(4));
            setAddress(result.getString(5));
            setEmail(result.getString(6));                        
            
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
            String sql = "INSERT INTO Customers " +
                         "VALUES ('" + getId()+ "', '" + getPw()+ "', '" + getFname()+ "', '" + getLname()+ "', '" + getAddress()+ "', '" + getEmail()+ "')";             
            
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
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Exception caught - " + ex + System.lineSeparator());
        }
    }

    @Override
    public void deleteDB() {
       try{
            Access databaseAccess = new Access();
            
            //setup statment
            String sql = "DELETE FROM Customers WHERE CustID = " + getId(); 
                     
            //execute Deletion                         
            int num = databaseAccess.getStatement().executeUpdate(sql);
            
            //deal with result
            if (num == 1){
                //debug to console
                System.out.println("Deletion successful!" + System.lineSeparator());
                
                //reset properties
                this.setId(0);
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
        }
    }
    


}
