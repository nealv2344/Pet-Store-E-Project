package businessObjs;

public class Cart {
    
    //Properties
    String custID;
    public ItemList items;
    double total = 0;
    
    //Constructors
    public Cart() {
            this("", new ItemList());
    }
    //
    public Cart(String custID, ItemList items) {
            this.custID = custID;
            this.items = items;
    }

    //Getters and Setters
    public String getCustID() {
        return custID;
    }
    //
    public void setCustID(String custID) {
        this.custID = custID;
    }
    //
    public ItemList getItems() {
        return items;
    }
    //  
    public void setItems(ItemList items) {
        this.items = items;
    }
    //
    public double getTotal() {
        return total;
    }
    //
    public void setTotal(double total) {
        this.total = total;
    }
    
    //Utility
    //
    //Adds Item object to cart's ItemList and updates total
    public void addItem(Item item){
        items.add(item);
        total = items.getTotal();        
    }
    //Creates Order object from cart and inserts to db i.e. checkout
    public void placeOrder() {
            //creates Order object based on current cart
            Order order = new Order(getCustID(), getItems());
            //inserts new order into DB
            order.insertDB();
    }
    //Displays Cart information, including ItemList information, to console
    public void display() {
        System.out.println("   Cart Information   " + System.lineSeparator() +
                           "=========================" + System.lineSeparator() +
                           "Customer ID: " + getCustID() + System.lineSeparator() + 
                           "Cart Total: " + getTotal()+ System.lineSeparator());
        items.display();
    }
    

    
}