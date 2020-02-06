/*********************************************** 
 * Instructor: Ron Enz
 * Description: 'Customers' Business Object
 * @author Neal Valdez
 * @version 1.0
 *
 * By turning in this code, I Pledge:
 *  1. That I have completed the programming assignment independently.
 *  2. I have not copied the code from a student or any source.
 *  3. I have not given my code to any student.
 *
 ************************************************/
public class Customers extends PersonObj{
    
    String cardInfo;
    
    protected Customers(){
        
        cardInfo = "";        
    }
    
    protected Customers(int Id, String fname, String lname, String email,
                        String password, String address, String cardInfo){
        
        super(Id,fname,lname,email,password,address);
        this.cardInfo = cardInfo;          
    }
    
    
    public String getCardInfo(){
        return cardInfo;
    }
    public void setCardInfo(String CardInfo){
        cardInfo = CardInfo;
    } 
    
    public void selectDB(int i){
        Id = i;
        try {
            //load DB
            
            //get connection
            
            //create sql stmt
            
            //Execute SQL stmt
            
            //process results
            
            //close connection
            
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void display(){
        System.out.println(getID()+"\t"+getFname()+"\t"+getLname()+"\t"+getEmail()+"\t"+getPassword()+"\t"+getAddress()+"\t"+getCardInfo());
    }
    
    
    public static void main (String [] args){
        
        Customers c1 = new Customers(444,"jim","williams","jimWilliams@gmail.com", "password123", "chat tech drive","2836528236272");       
        c1.display();
        
    
    
    }
    
    
    
    
}
