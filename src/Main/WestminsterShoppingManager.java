package Main;

import GUI.Interface1GUI;
import GUI.LoginFrame;
import GUI.User;
import Console.Product;
import Console.Electronics;
import Console.Clothing;
import GUI.UserData;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.*;

public class WestminsterShoppingManager implements ShoppingManager {
    Scanner input = new Scanner(System.in);
    public static ArrayList<Product> consoleProductList = new ArrayList<Product>();
    public static ArrayList<Product> electronicsArrayListHold = new ArrayList<Product>();
    public static ArrayList<Product> clothingArrayListHold = new ArrayList<Product>();
    static String electronics;
    static String clothes;
    double count = 0.0;
    static ArrayList<User> userList=new ArrayList<>();
    static ArrayList<Product> saveproducts = new ArrayList<Product>();

    public WestminsterShoppingManager(){

    }

    // Display the main menu options
    @Override
    public void menuDisplay() {
        System.out.println("************************************");
        System.out.println("    Westminster Shopping Manager    ");
        System.out.println("               Menu                 ");
        System.out.println("************************************");
        System.out.println("1. Add a new product                ");
        System.out.println("2. Delete a product                 ");
        System.out.println("3. Print the list of products       ");
        System.out.println("4. Save products to a file          ");
        System.out.println("5. GUI_1 Part                       ");
        System.out.println("6. Register customers               ");
        System.out.println("7. Exit                             ");

        System.out.print("\nEnter your choice: ");
    }

    // Process the user's choice from the main menu
    public void menuChoice() throws IOException {
        loadProductsFromFile();
        while (true)  {
            menuDisplay();
            try {
                int choice = input.nextInt();
                System.out.println();

                switch (choice) {
                    case 1:
                        addProductToSystem();
                        break;
                    case 2:
                        removeProductFromSystem();
                        break;
                    case 3:
                        printProductList(WestminsterShoppingManager.consoleProductList);
                        updateVaribles();
                        break;
                    case 4:
                        saveProductsToFile();
                        break;
                    case 5:
                        run();
                        break;
                    case 6:
                        registrationConsole();
                        break;
                    case 7:
                        System.out.println("Exiting the system. Goodbye!");
                        System.exit(0);

                    default:
                        System.out.println("Invalid choice. Please enter a valid option.\n");
                }
            }
            catch (Exception e){
                System.out.println("Entered input is not a number.\n");
                String junk = input.nextLine();
            }
        }
    }

    // Add a new product to the system
    @Override
    public void addProductToSystem() {
        if(consoleProductList.size()<50){
            while (true) {
                if(consoleProductList.size()<50){
                    try {
                        System.out.println("Choose product type:");
                        System.out.println("1-Electronics");
                        System.out.println("2-Clothing");
                        System.out.print("Enter your option: ");
                        int option = input.nextInt();
                        System.out.println("\n");

                        Product addProduct;

                        if (option == 1) {
                            addElectronicProduct();
                        } else if (option == 2) {
                            addClothingProduct();
                        } else {
                            System.out.println("Something went wrong\n");
                        }

                        System.out.print("Do you want to add another Item (Yes/No): ");
                        String answer = input.next();
                        System.out.println();

                        if (answer.equalsIgnoreCase("No")) {
                            break;
                        } else if (answer.equalsIgnoreCase("Yes")) {
                            continue;
                        } else {
                            System.out.println("Entered input is invalid\nDirecting to the MAIN MENU\n");
                            break;
                        }
                    } catch (Exception e) {
                        System.out.println("Invalid input is entered\n");
                        String junk = input.nextLine();
                    }
                } else {
                    break;
                }

            }
        } else {
            System.out.println("Maximum number of Products reached\n");
        }

    }
    private void addElectronicProduct() {

        while (true) {
            if(consoleProductList.size()<50){
                try {
                    System.out.println("ADD ELECTRONIC ITEM\n");
                    System.out.print("Enter Item ID: ");
                    String productId = input.next();
                    if(validate(productId)){
                        System.out.print("Enter Item name: ");
                        input.nextLine();
                        String productName = input.nextLine();

                        int noItems;
                        while (true){try {
                            System.out.print("Enter number of Items: ");
                            noItems = input.nextInt();
                            break;
                        }catch (Exception e){
                            System.out.println("Invalid");
                            String junk = input.nextLine();
                        }
                        }

                        double price;
                        while (true){try {
                            System.out.print("Enter price of the item: ");
                            price = input.nextDouble();
                            break;
                        }catch (Exception e){
                            System.out.println("Invalid");
                            String junk = input.nextLine();
                        }
                        }

                        System.out.print("Enter Brand of the item: ");
                        String brand = input.next();

                        int warrantyPeriod;
                        while (true){try {
                            System.out.print("Item warranty period (Weeks): ");
                            warrantyPeriod = input.nextInt();
                            break;
                        }catch (Exception e){
                            System.out.println("Invalid");
                            String junk = input.next();
                        }
                        }

                        Electronics newItem = new Electronics(productId, productName, noItems, price, brand, warrantyPeriod);
                        WestminsterShoppingManager.saveproducts.add(newItem);
                        WestminsterShoppingManager.consoleProductList.add(newItem);
                        System.out.println();
                        System.out.println("Product details\n");
                        displayProductInfo(newItem);
                        System.out.println("\nProduct added successfully.\n");
                    }


                } catch (Exception e) {
                    System.out.println("Enter a valid input\n");
                    String junk = input.nextLine();
                }

                try {
                    System.out.print("Do you want to add another Electronic Item (Yes/No): ");
                    String answer = input.next();;

                    if (answer.equalsIgnoreCase("No")) {
                        break;
                    } else if (answer.equalsIgnoreCase("Yes")) {
                        continue;
                    } else {
                        System.out.println("Entered input is invalid\n");
                        break;
                    }
                } catch (Exception e) {
                    System.out.println("Invalid Input\n");
                    String junk = input.nextLine();
                }
            } else {
                System.out.println("Maximum number of Products reached\n");
                String junk = input.nextLine();
                break;
            }
        }
    }
    private void addClothingProduct() throws IOException {

        while (true) {
            if(consoleProductList.size()<50){
                try {
                    System.out.println("ADD CLOTHING ITEM\n");
                    System.out.print("Enter Item ID: ");
                    String productId = input.next();;
                    if(validate(productId)){
                        System.out.print("Enter Item name: ");
                        input.nextLine();
                        String productName = input.nextLine();;

                        int noItems;
                        while (true){try {
                            System.out.print("Enter number of Items: ");
                            noItems = input.nextInt();
                            break;
                        }catch (Exception e){
                            System.out.println("Invalid");
                            String junk = input.nextLine();
                        }
                        }
                        double price;
                        while (true){try {
                            System.out.print("Enter price of the item: ");
                            price = input.nextDouble();
                            break;
                        }catch (Exception e){
                            System.out.println("Invalid");
                            String junk = input.nextLine();
                        }
                        }

                        System.out.print("Enter colour of item: ");
                        input.nextLine();
                        String clothColor = input.nextLine();

                        int clothSize;
                        while (true){try {
                            System.out.print("Item size of item: ");
                            clothSize = input.nextInt();
                            break;
                        }catch (Exception e){
                            System.out.println("Invalid");
                            String junk = input.nextLine();
                        }
                        }
                        Clothing newItem = new Clothing(productId, productName, noItems, price, clothColor, clothSize);
                        WestminsterShoppingManager.saveproducts.add(newItem);
                        WestminsterShoppingManager.consoleProductList.add(newItem);
                        System.out.println();
                        System.out.println("Product details-\n");
                        displayProductInfo(newItem);
                        System.out.println("Product added successfully.\n");
                    }

                } catch (Exception e) {
                    System.out.println("Enter a valid input(like numbers)\n");
                    String junk = input.nextLine();
                }

                System.out.print("Do you want to add another Clothing Item (Yes/No): ");
                String answer = input.next();
                System.out.println();

                if (answer.equalsIgnoreCase("No")) {
                    break;
                } else if (answer.equalsIgnoreCase("Yes")) {
                    continue;
                } else {
                    System.out.println("Entered input is invalid\n");
                    break;
                }
            }else {
                System.out.println("Maximum number of Products reached\n");
                String junk = input.nextLine();
                break;
            }
        }
    }

    // Validate the product ID format
    private boolean validate(String pID) {
        boolean check = false;
        boolean foundDuplicate = false;

        for (Product proID : consoleProductList) {
            if (Objects.equals(pID, proID.getProductId())) {
                System.out.println("Product is already added");
                foundDuplicate = true;
                break;
            }
        }

        if (!foundDuplicate) {
            if ((pID.charAt(0) == 'A' || pID.charAt(0) == 'B') && (pID.length() == 4)) {
                check = true;
            } else {
                System.out.println("Try to use the correct pattern\n");
            }
        }

        return check;
    }

    // Perform user registration in the console
    public void registrationConsole(){

        UserData valid = new UserData();

        System.out.println("=== User Registration ===");
        System.out.print("Enter username: ");
        input.nextLine();
        String username = input.nextLine();

        System.out.print("Enter password: ");
        String password = input.nextLine();

        if (valid.registrationValidation(username)){
            double count = 0.0;
            User newUser = new User(username, password, count);
            UserData.addUser(newUser);
            System.out.println("Registration successful!\n");

        }else System.out.println("Already registered\n");

    }

    // Run the graphical user interface (GUI)
    public void run() {
        SwingUtilities.invokeLater(() -> {
            LoginFrame loginFrame = new LoginFrame(this);
            loginFrame.setVisible(true);
        });
    }

    // Display the main window of the GUI
    public void showMainWindow() {
        openGUI();
    }

    // Remove a product from the system
    @Override
    public void removeProductFromSystem() {
        System.out.println("REMOVE ELECTRONIC ITEM\n");

        updateVaribles();
        int count = 0;
        while (true) {
            count++;
            boolean foundProduct = false;
            System.out.print("Enter id of the product: ");
            if (count==1){input.nextLine();}
            String deleteProductId = input.nextLine().trim();

            for (Product product : WestminsterShoppingManager.consoleProductList) {

                if (product.getProductId().equals(deleteProductId)) {
                    System.out.println("\nProduct details-\n------------------------------");

                    if (product instanceof Electronics) {
                        System.out.println("Category: Electronics");
                    } else {
                        System.out.println("Category Clothing");
                    }
                    displayProductInfo(product);
                    System.out.println(" ");

                    // ask whether the user wants to remove it
                    WestminsterShoppingManager.consoleProductList.remove(product); // remove the found product
                    System.out.println("Product removed successfully.");
                    saveProductsToFile();
                    System.out.println("\nTotal number of products left: " + WestminsterShoppingManager.consoleProductList.size()+"\n");
                    foundProduct = true;
                    break; // break the loop since the product is found
                }
            }

            if (!foundProduct) {
                System.out.println("No product under the entered ID\n");
            }

            try {
                System.out.print("Do you want to remove another Item (Yes/No): ");
                String answer = input.nextLine();
                System.out.println(" ");
                if (answer.equalsIgnoreCase("No")) {
                    break;
                } else if (answer.equalsIgnoreCase("Yes")) {
                    continue;
                } else {
                    System.out.println("Entered input is invalid\n");
                }
            } catch (Exception e) {
                System.out.println("Enter a valid input (like numbers)\n");
                String junk = input.nextLine();
            }
        }

    }

    // Display product information
    public void displayProductInfo(Product product) {
        System.out.println("Product ID: " + product.getProductId());
        System.out.println("Product Name: " + product.getProductName());
        System.out.println("Available Items: " + product.getNoItems());
        System.out.println("Price: " + product.getPrice());
        // Display additional attributes based on the type of product

        if (product instanceof Electronics) {
            Electronics electronics = (Electronics) product;
            System.out.println("Brand: " + electronics.getBrand());
            System.out.println("Warranty Period: " + electronics.getWarrantyPeriod() + " Weeks");
        } else if (product instanceof Clothing) {
            Clothing clothing = (Clothing) product;
            System.out.println("Size: " + clothing.getClothSize());
            System.out.println("Color: " + clothing.getClothColor());
        }
    }

    // Print the list of products in the system
    private void printProductList(ArrayList<Product> consoleProductList) {

        System.out.println("List of Products in the System:");
        System.out.println("\n------------------------------");
        if (WestminsterShoppingManager.consoleProductList.isEmpty()) {
            System.out.println("No products in the system.\n");
        } else {
            ArrayList<Product> sortedProducts = new ArrayList<>(WestminsterShoppingManager.consoleProductList);
            selectionSort(sortedProducts);
            for(Product product : sortedProducts){
                if(product instanceof Electronics){
                    electronics = "Category : Electronics"+ "\nProduct ID : " + product.getProductId()+ "\nProduct Name : "+ product.getProductName()+ "\nAvailable Items : "
                            + ((Electronics) product).getNoItems()+ "\nPrice : "+((Electronics) product).getPrice()+ "\nBrand : "+
                            ((Electronics) product).getBrand()+ "\nWarranty Period : "+ ((Electronics) product).getWarrantyPeriod()+"\n-------------------------";
                    System.out.println(electronics);
                } else if (product instanceof  Clothing) {
                    clothes ="Category : Clothing"+ "\nProduct ID : " + product.getProductId()+ "\nProduct Name : "+ product.getProductName()+ "\nAvailable Items : "+
                            ((Clothing) product).getNoItems()+ "\nPrice : "+ ((Clothing) product).getPrice()+ "\nSize : "+
                            ((Clothing) product).getClothSize()+ "\nColour : "+ ((Clothing) product).getClothColor()+"\n-------------------------";
                    System.out.println(clothes);
                }
            }
        }
    }

    // Sort the products using selection sort
    public static void selectionSort(ArrayList<Product> products) {
        int n = products.size();

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;

            for (int j = i + 1; j < n; j++) {
                // Compare product IDs for sorting
                if (products.get(j).getProductId().compareTo(products.get(minIndex).getProductId()) < 0) {
                    minIndex = j;
                }
            }
            // Swap the found minimum element with the first element
            Product temp = products.get(minIndex);
            products.set(minIndex, products.get(i));
            products.set(i, temp);
        }
    }

    // Load products from a file
    @Override
    public ArrayList<Product> loadProductsFromFile() {
        ArrayList<Product> productArrayList = new ArrayList<>();
        File file = new File("sampletext.txt");
        try(BufferedReader reader = new BufferedReader(new FileReader(file))){
            String str;
            while ((str = reader.readLine()) != null){
                String[] words = str.split(",");
                if(words[0].equals("Electronics : ")){
                    Electronics product = new Electronics(words[1], words[2], Integer.parseInt(words[3]), Double.parseDouble(words[4]), words[5], Integer.parseInt(words[6]));
                    consoleProductList.add(product);
                } else if (words[0].equals("Clothing : ")) {
                    Clothing product = new Clothing(words[1], words[2], Integer.parseInt(words[3]), Double.parseDouble(words[4]), words[6], Float.parseFloat(words[5]));
                    consoleProductList.add(product);
                }

            }
        } catch (IOException e) {
            System.out.println("Error saving products to file: " + e.getMessage());
        }
        return consoleProductList;
    }

    // Save products to a file
    @Override
    public void saveProductsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("sampletext.txt"))) {

            updateVaribles();
            for (Product product : consoleProductList) {
                if(product instanceof Electronics){
                    electronics = "Electronics : "+ ','+ product.getProductId()+ ','+ product.getProductName()+ ','
                            + ((Electronics) product).getNoItems()+ ','+((Electronics) product).getPrice()+ ','+
                            ((Electronics) product).getBrand()+ ','+ ((Electronics) product).getWarrantyPeriod();
                    writer.append(electronics);
                    writer.newLine();
                }else if (product instanceof  Clothing){
                    clothes ="Clothing : "+','+ product.getProductId()+ ','+ product.getProductName()+ ','+
                            ((Clothing) product).getNoItems()+ ','+ ((Clothing) product).getPrice()+ ','+
                            ((Clothing) product).getClothSize()+ ','+ ((Clothing) product).getClothColor();
                    writer.append(clothes);
                    writer.newLine();
                }
            }
            System.out.println("Saved Successfully");
        } catch (IOException e) {
            System.out.println("Error saving products to file: " + e.getMessage());
        }
    }

    // Update variables for saving to file
    public void updateVaribles() {

        for(Product product : consoleProductList){
            if(product instanceof Electronics){
                electronics = "Electronics : "+ ' '+ product.getProductId()+ ' '+ product.getProductName()+ ' '+ ((Electronics) product).getNoItems()+ ' '+((Electronics) product).getPrice()+ ' '+ ((Electronics) product).getBrand()+ ' '+ ((Electronics) product).getWarrantyPeriod();
            } else if (product instanceof  Clothing) {
                clothes ="Clothing : "+ product.getProductId()+ ' '+ product.getProductName()+ ' '+ ((Clothing) product).getNoItems()+ ' '+ ((Clothing) product).getPrice()+ ' '+ ((Clothing) product).getClothSize()+ ' '+ ((Clothing) product).getClothColor();
            }
        }
    }

    // Open the graphical user interface (GUI)
    @Override
    public void openGUI() {
        Interface1GUI frame = new Interface1GUI(WestminsterShoppingManager.consoleProductList, consoleProductList,WestminsterShoppingManager.userList);
        frame.setTitle("Westminster Shopping Centre");
        frame.setVisible(true);
        frame.setSize(750, 500); // resize this
    }
}