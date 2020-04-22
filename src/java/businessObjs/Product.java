package businessObjs;

import Data.Access;
import java.sql.*;

public class Product {
    
    //Properties
    protected int sku;
    protected String name;
    protected double price;
    protected int stock;
    
    
    //Constructors
    public Product() {
        sku = 0;
        name = "";
        price = 0.00;
        stock = 0;
    }
    //
    public Product(int s, String n, double p, int st) {
        sku = s;
        name = n;
        price = p;
        stock = st;
    }
        
    //Getters and Setters
    public int getSku() {
        return sku;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public void setSku(int s) {
        sku = s;
    }

    public void setName(String n) {
        name = n;
    }

    public void setPrice(double p) {
        price = p;
    }

    public void setStock(int s) {
        stock = s;
    }

    //Database access methods
    public void selectDB(int sku) {
        //SKUs start at 8000001
        try {
            Access DA = new Access();
            String sql = "Select * from Products where SKU=" + sku + ";";
            
            ResultSet rs = DA.getStatement().executeQuery(sql);
            System.out.println("Statement executed");
            while (rs.next()) {
                setSku(rs.getInt(1));
                setPrice(rs.getDouble(2));
                setStock(rs.getInt(3));
                setName(rs.getString(4));
            }
            
            DA.close();
        }
        catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex + ": thrown by Business.Product.selectDB(" + sku + ")");
        }
    }
    //
    public void insertDB(int s, String n, double p, int st) {
        try {
            Access DA = new Access();
            String sql = "Insert into Products values(" + s + ", " + p + ", " + st + ", \"" + n + "\");";
            
            DA.getStatement().executeUpdate(sql);
            System.out.println("Statement executed");
            
            DA.close();
        }
        catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex + ": thrown by Business.Product.insertDB(" + s + ", \"" + n + "\", " + p + ", " + st + ")");
        }
    }
    //
    public void updateDB(int s, String n, double p, int st) {
        try {
            Access DA = new Access();
            String sql = "Update Products set ProductName = \"" + n + "\", Price = " + p + ", LeftInStock = " + st + " where SKU = " + s + ";";
            
            DA.getStatement().executeUpdate(sql);
            System.out.println("Statement executed");
            
            DA.close();
        }
        catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex+ ": thrown by Business.Product.updateDB(" + s + ", \"" + n + "\", " + p + ", " + st + ")");
        }
    }
    //
    public void deleteDB() {
        try {
            Access DA = new Access();
            String sql = "Delete from Products where SKU = " + sku + ";";
            
            DA.getStatement().executeUpdate(sql);
            System.out.println("Statement executed");
            
            DA.close();
        }
        catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex + ": thrown by Business.Product.deleteDB()");
        }
    }

        
    //Utility
    public void addProduct(int s, int i) {
        stock += i;
        //updateDB(), uses s for sku
    }
    //
    public void removeProduct(int s, int i) {
        stock -= i;
        if (stock<0) {
        System.out.println("Cannot have negative inventory");
        }
        else {
        //updateDB(), uses s for sku
        }
    }
    //
    public void display() {
        System.out.println(sku + " | " + name + ": $" + price + " x" + stock);
    }
    
    

}