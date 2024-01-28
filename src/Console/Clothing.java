package Console;

import java.io.Serializable;

public class Clothing extends Product implements Serializable {

    // Additional attributes for Clothing
    private String clothColor;
    private float clothSize;

    // Constructor for Clothing class
    public Clothing(String productId, String productName, int noItems, double price, String clothColor, float clothSize) {
        super(productId,productName,noItems,price);
        this.clothColor = clothColor;
        this.clothSize = clothSize;
    }

    // Getter methods and setter methods
    public String getClothColor() {
        return clothColor;
    }

    public void setClothColor(String clothColor) {
        this.clothColor = clothColor;
    }

    public float getClothSize() {
        return clothSize;
    }

    public void setClothSize(float clothSize) {
        this.clothSize = clothSize;
    }

    @Override
    public void displayProductDetails() {
        System.out.println("Size: " + getClothSize());
        System.out.println("Color: " + getClothColor());
    }

    @Override
    public String displayProductInfo() {
        return "   Category : Clothing"+ "\n" +
                "   Product ID : " + getProductId() + "\n" +
                "   Name : " + getProductName() + "\n" +
                "   Price : " + getPrice()+"\n"+
                "   Color : " + clothColor+"\n"+
                "   No of Items : " + getNoItems();

    }
}
