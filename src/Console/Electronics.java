package Console;

import java.io.Serializable;

public class Electronics extends Product implements Serializable {

    // Additional attributes for Electronics
    private String brand;
    private int warrantyPeriod;

    // Constructor for Electronics class
    public Electronics(String productId, String productName, int noItems, double price, String brand, int warrantyPeriod) {
        super(productId, productName, noItems, price);
        this.brand = brand;
        this.warrantyPeriod = warrantyPeriod;
    }

    // Getter methods and setter methods
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getWarrantyPeriod() {
        return warrantyPeriod;
    }

    public void setWarrantyDays(int warrantyDays) {
        this.warrantyPeriod = warrantyDays;
    }

    @Override
    public void displayProductDetails() {

    }

    @Override
    public String displayProductInfo() {
        return "   Category : Electronics"+ "\n" +
                "   Product ID : " + getProductId() + "\n" +
                "   Name : " + getProductName() + "\n" +
                "   Price : " + getPrice()+"\n"+
                "   Warranty Period : " + warrantyPeriod+"\n"+
                "   No of Items : " + getNoItems()

                ;

    }

}
