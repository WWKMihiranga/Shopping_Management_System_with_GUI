package GUI;
import Console.Clothing;
import Console.Electronics;
import Console.Product;
import java.util.ArrayList;
public class ShoppingCart {

    // ArrayList to store products in the shopping cart
    public static ArrayList<Product> cartProductArray = new ArrayList<>();

    // Constructor for ShoppingCart
    ShoppingCart() {
    }

    // Method to add a product to the shopping cart
    public void addProduct(Product product) {
        boolean alreadyAdded = false;

        if (cartProductArray.size()<8){
            for (Product p:cartProductArray){
                if (p.getProductId().equals(product.getProductId())){
                    int newCount = product.getCount();
                    newCount++;
                    product.setCount(newCount);
                    alreadyAdded = true;
                }
            }
            if (!alreadyAdded){
                cartProductArray.add(product);
                product.setCount(1);
            }
        }else {
            cartProductArray.add(product);
            product.setCount(1);
        }

        calculateTotal();
    }

    // Method to remove a product from the shopping cart
    public void removeProduct(Product product) {
        cartProductArray.remove(product);
    }

    // Method to calculate the total cost of items in the shopping cart
    public static double calculateTotal() {
        double total = 0;

        for (int i = 0; i < cartProductArray.size(); i++) {
            Product product = cartProductArray.get(i); // give the value of object
            total += product.getPrice()*product.getCount();
        }
        System.out.println(total);
        return total;
    }

    // Method to apply a discount for the user's first purchase
    public double firstPurchaseDiscount(double total){
        double firstPurchaseDiscount;

        User user =UserData.getUser(LoginFrame.getUserName1(),LoginFrame.getPassword1());

        if (user.getUserCount() ==0){
            firstPurchaseDiscount = (total)*0.1;
            return firstPurchaseDiscount;
        }
        return 0;
    }

    // Method to apply a discount based on the product category
    public double categoryDiscount(double total, ArrayList<Product> userCart){

        double categoryDiscount;
        int electronicsCount = 0;
        int clothingCount = 0;

        for (Product product:userCart){
            if (product instanceof Electronics){
                electronicsCount+=product.getCount();
            }
            if (product instanceof Clothing){
                clothingCount+=product.getCount();
            }
        }

        if (electronicsCount>=3){
            categoryDiscount=total*0.2;
            return categoryDiscount;

        } else if (clothingCount >= 3) {
            categoryDiscount=total*0.2;
            return categoryDiscount;
        }

        return 0;
    }

    // Method to calculate the final total after applying discounts
    public double getFinalTotal(double total, double firstPurchaseDiscount, double categoryDiscount) {
        double finalTotal;
        finalTotal = total - (firstPurchaseDiscount + categoryDiscount);
        return finalTotal;
    }

}
