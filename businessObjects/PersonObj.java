/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Neal Valdez
 */
public class PersonObj {
    
    protected int Id;
    protected String fname;
    protected String lname;
    protected String email;
    protected String pw;
    protected String address;    


protected PersonObj() {
    
        Id = 0;
        fname = "";
        lname = "";
        email = "";
        pw = "";
        address = "";   
}

protected PersonObj(int Id, String fname, String lname, String email,
                        String password, String address){
    
    this.Id = Id;
    this.fname = fname;
    this.lname = lname;
    this.email = email;
    this.pw = password;
    this.address = address;    
}

public int getID(){
        return Id;
    }
    public void setId(int id){
        Id = id;
    }
    
    public String getFname(){
        return fname;
    }
    public void setFname(String Fname){
        fname = Fname;
    }
    
    public String getLname(){
        return lname;
    }
    public void setLname(String Lname){
        lname = Lname;
    }
    
    public String getEmail(){
        return email;
    }
    public void setEmail(String Email){
        email = Email;
    }
    
    public String getPassword(){
        return pw;
    }
    public void setPassword(String Password){
        pw = Password;
    }
    
    public String getAddress(){
        return address;
    }
    public void setAddress(String Address){
        address = Address;
    }
    
    public void display(){
    
        System.out.println(getID()+"\t"+getFname()+"\t"+getLname()+"\t"+getEmail()+"\t"+getPassword()+"\t"+getAddress());
        
}
    
    public static void main(String [] args){        
        PersonObj p1 = new PersonObj(123, "Bob", "Jones", "bobjones123@gmail.com", "secret123", "123 asdfgh Dr");        
        p1.display();
    }
    
}