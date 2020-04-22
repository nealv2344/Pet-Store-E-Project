package businessObjs;

/*********************************************** 
 * Instructor: Ron Enz
 * Description: 'Address' Business Object
 * @author Neal Valdez
 * @version 1.0
 *
 * By turning in this code, I Pledge:
 *  1. That I have completed the programming assignment independently.
 *  2. I have not copied the code from a student or any source.
 *  3. I have not given my code to any student.
 *
 ************************************************/

/**
 *
 * @author Neal Valdez
 */
public class Address{
    //street,city,state,zip,country   
    protected String street;
    protected String city;
    protected String state;
    protected int zip;
    protected String country;
    
    
    protected Address(){
        street = "";
        city = "";
        state = "";
        zip = 0;
        country = "";
    }
    
    protected Address(String street, String city, String state,int zip, String country){
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.country = country;        
    }
    
    public String getStreet(){
        return street;
    }
    public void setStreet(String Street){
        street = Street;
    }
    
    public String getCity(){
        return city;
    }
    public void setCity(String City){
        city = City;
    }
    
    public String getState(){
        return state;
    }
    public void setState(String State){
        state = State;
    }
    
    public int getZip(){
        return zip;
    }
    public void setZip(int Zip){
        zip = Zip;
    }
    
    public String getCountry(){
        return country;
    }
    public void setCountry(String Country){
        country = Country;
    }
    
    
    protected String fullAddress(){
        String address;       
        String zip = Integer.toString(getZip());       
        address = street+" "+city+" "+state+","+zip;       
        return address;
    }
    
    protected String formalAddress(){
        String formalAddress;
        
        String zip = Integer.toString(getZip());
        formalAddress = street+"\n"+city+" "+state+" "+zip+"\n"+country;        
        return formalAddress;
    }
    
    public static void main (String [] args){
        
        Address a1 = new Address("123 main", "Marietta", "Ga", 30066, "USA");        
        System.out.println(a1.formalAddress());
        System.out.println("\n");
        System.out.println(a1.fullAddress());
    }
    
    
}
