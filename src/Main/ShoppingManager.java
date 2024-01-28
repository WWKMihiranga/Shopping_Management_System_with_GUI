package Main;

import Console.Product;

import java.util.ArrayList;

public interface ShoppingManager {

    void menuDisplay();
    void addProductToSystem();
    void removeProductFromSystem();
    void displayProductInfo(Product product);
    void saveProductsToFile();
    ArrayList<Product> loadProductsFromFile();
    void openGUI();
}
