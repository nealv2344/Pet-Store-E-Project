package businessObjs;

public class Item extends Product {
	
    //Properties
    int quantity;
   
    //Constuctors
    public Item() {
        this(0, "", 0.00, 0, 1);
    }
    //
    public Item(int SKU, String name, double price, int stock, int quantity) {
        
        super(SKU, name, price, stock);
        this.quantity = quantity;
    }
    
    //Getters and Setters    
    public int getQuantity() {
        return quantity;
    }
    //
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    //DB Methods go here
    
    
            
    
}