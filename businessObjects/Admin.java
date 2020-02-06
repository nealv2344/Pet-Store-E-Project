/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Neal Valdez
 */
public class Admin extends PersonObj{
    
    public Admin(int Id, String fname, String lname, String email,
                        String password, String address){
        
        
        super(Id,fname,lname,email,password,address);
    }

    public void display(){
        System.out.println(getID()+"\t"+getFname()+"\t"+getLname()+"\t"+getEmail()+"\t"+getPassword()+"\t"+getAddress());
    }
    
    public static void main (String [] args){
        Admin a1 = new Admin(9999,"Admin","istration","admin1234@gmail.com","secret3432","1234 willow dr");       
        a1.display();
    }    
}
