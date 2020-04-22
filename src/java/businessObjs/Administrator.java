package businessObjs;

import Data.Access;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Administrator extends PersonObj {
    
    //Constructors
    public Administrator() {
        //call overloaded constructor
        this("", "", "", "", "", "");
    }    
    //    
    public Administrator(String Id, String fname, String lname, String address,  String password, String email) {
       
        //call super constructor
        super(Id, fname, lname, address, password, email);
    }

    //Database access methods
    //
    //Select Customer from database and populate object with it
    @Override
    public void selectDB(String ID) {
        
        try {
            Access databaseAccess = new Access();
            
            //setup statement and execute it
            String sql = "select * from Administrators WHERE ID = '" + ID + "'";
            ResultSet rs = databaseAccess.getStatement().executeQuery(sql);
            
            rs.next();
            
            //set properties
            setId(rs.getString(1));
            setFname(rs.getString(2));
            setLname(rs.getString(3));
            setAddress(rs.getString(4));
            setPw(rs.getString(5));
            setEmail(rs.getString(6));
            
            //debug some info to console
            System.out.println("Administrator " + ID + " Successfully selected" + System.lineSeparator());
            
            //close connection
            databaseAccess.close();                        
        } 
        catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Exception caught - " + ex + System.lineSeparator());
            ex.printStackTrace();
        }
    }
    //
    //Insert populated Customer Object into database
    @Override
    public void insertDB() {
        
        try {
             Access databaseAccess = new Access();
            
            //setup statment
            String sql = "INSERT INTO Administrators " +
                         "VALUES ('" + getId()+ "', '" + getFname()+ "', '" + getLname()+ "', '" + getAddress()+ "', '" + getPw()+ "', '" + getEmail()+ "')";             
            
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
            
            databaseAccess.close();

        }

        catch (ClassNotFoundException | SQLException e){
            System.out.println("Exception caught - " + e + System.lineSeparator());
        }
        
    }
    //
    //Delete populated Customer Object from database
    @Override
    public void deleteDB() {
        
        try{
            Access databaseAccess = new Access();
            
            //setup statment
            String sql = "DELETE FROM Administrators WHERE ID = " + "'"+getId()+"'";          
            System.out.println(sql);
            //execute Deletion                                               
            int num = databaseAccess.getStatement().executeUpdate(sql);
            
            //deal with result
            if (num == 1){
                //debug to console
                System.out.println("Deletion successful!" + System.lineSeparator());
                //reset properties to blank values
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
        } 
        catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Exception caught - " + ex + System.lineSeparator());
        }
        
    }
    
    public void display(){
       System.out.println("Id: "+getId()+"\t Fname: "+getFname()+"\t Lname: "+getLname()+"\t Address: "+getAddress()+"\t Password: "+getPw()+"\t Email: "+getEmail());
    }
    
    public static void main(String []args){
        Administrator a1 = new Administrator("B003","new","admin","123 address dr", "iii222","boo@yahoo.com");
        a1.insertDB();
        a1.display();
        
        Administrator a2 = new Administrator();
        a2.selectDB("B003");
        System.out.println(a2.getId());
        a2.deleteDB();
    }
  

}
