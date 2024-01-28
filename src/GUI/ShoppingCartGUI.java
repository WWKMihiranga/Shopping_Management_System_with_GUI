package GUI;
import Console.Product;
import Main.ShoppingManager;
import Main.WestminsterShoppingManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

// ShoppingCartGUI class to manage the shopping cart user interface
public class ShoppingCartGUI extends JFrame {
    JPanel panel1, panel2, topPanel, bottomPanel, buttonLine, description;
    JTextArea discountArea;
    JTable table1;
    JScrollPane pane;
    JButton btn1, btn2, btn3, btn4;

    // Constructor for ShoppingCartGUI
    ShoppingCartGUI() {

        setLayout(new BorderLayout());
        panel1 = new JPanel();
        panel1.setLayout(new FlowLayout());
        ShoppingCartTableModel model1 = new ShoppingCartTableModel(ShoppingCart.cartProductArray);
        table1 = new JTable(model1);
        pane = new JScrollPane(table1);

        panel1.add(pane);
        pane.setPreferredSize(new Dimension(500, 150));
        panel1.setLayout(new FlowLayout());

        buttonLine = new JPanel();
        buttonLine.setLayout(new FlowLayout());
        buttonLine.setPreferredSize(new Dimension(500, 25));

        btn1 = new JButton("Remove Product");
        btn1.setBackground(new Color(208,233,233));
        btn2 = new JButton("Cancel the Order");
        btn2.setBackground(new Color(208,233,233));
        btn3 = new JButton("Place Order");
        btn3.setBackground(new Color(208,233,233));
        btn4 = new JButton("Back to Shopping");
        btn4.setBackground(new Color(208,233,233));

        buttonLine.add(btn1);
        buttonLine.add(btn2);
        buttonLine.add(btn3);
        buttonLine.add(btn4);


        discountArea = new JTextArea();
        panel2 = new JPanel();
        panel2.setLayout(new GridLayout(4,2));

        description = new JPanel();
        description.setPreferredSize(new Dimension(500, 90));
        description.setLayout(new FlowLayout());


        JLabel total = new JLabel("Total");
        JLabel discount10 = new JLabel("First Purchase Discount (10%)");
        JLabel  discount20 = new JLabel("Three items in save category discount (20%)");
        JLabel finalTotal = new JLabel("Final Total");


        // Calculate discount values using ShoppingCart methods
        ShoppingCart cart = new ShoppingCart();
        double tatalF = cart.calculateTotal();
        double firstDiscount = cart.firstPurchaseDiscount(tatalF);
        double categoryDiscount = cart.categoryDiscount(tatalF, ShoppingCart.cartProductArray);
        double fTotal = cart.getFinalTotal(tatalF,firstDiscount,categoryDiscount);


        JLabel l = new JLabel("          "+tatalF+" €");
        JLabel l2 = new JLabel("        - "+firstDiscount+" €");
        JLabel l3 = new JLabel("        - "+categoryDiscount+" €");
        JLabel l4 = new JLabel("          "+fTotal+" €");

//        panel2.add(discountArea);
        description.add(panel2);
        panel2.add(total,0);
        panel2.add(l,1);
        panel2.add(discount10,2);
        panel2.add(l2,3);
        panel2.add(discount20,4);
        panel2.add(l3,5);
        panel2.add(finalTotal,6);
        panel2.add(l4,7);


        add(panel1,BorderLayout.NORTH);
        add(buttonLine,BorderLayout.CENTER);
        add(description,BorderLayout.SOUTH);

        btn1.addActionListener(new EventListenerClass() );
        btn2.addActionListener(new EventListenerClass() );
        btn3.addActionListener(new EventListenerClass() );
        btn4.addActionListener(new EventListenerClass() );

        // Move the line below to after initializing table1
        //table1.setModel(model1);
    }

    // EventListenerClass to handle button actions
    private class EventListenerClass implements ActionListener{
        int selectedRow;

        @Override
        public void actionPerformed(ActionEvent e) {
            // Handle button actions based on the source
            if (e.getSource()==btn1){
                selectedRow=table1.getSelectedRow();
                if (selectedRow!=-1){
                    Product removeProduct = ShoppingCart.cartProductArray.get(selectedRow);
                    int removeCount = removeProduct.getCount();
                    removeCount= removeProduct.getNoItems() + removeCount;
                    removeProduct.setNoItems(removeCount);
                    Interface1GUI.table.setModel(new Interface1TableModel(WestminsterShoppingManager.consoleProductList));
                    ShoppingCart.cartProductArray.remove(selectedRow);
                    Interface1GUI.area.setText(WestminsterShoppingManager.consoleProductList.get(selectedRow).displayProductInfo());
                    ShoppingCart.calculateTotal();
                    ShoppingCartGUI cartFrame = new ShoppingCartGUI();
                    cartFrame.setTitle("Shopping Cart");
                    cartFrame.setVisible(true);
                    cartFrame.setSize(550, 330);
                    dispose();
                    ShoppingCartTableModel newUserCartModel = new ShoppingCartTableModel(ShoppingCart.cartProductArray);
                    table1.setModel(newUserCartModel);
                    JOptionPane.showMessageDialog(null,"Product Remove Successfully","Success",JOptionPane.INFORMATION_MESSAGE);
                }else {JOptionPane.showMessageDialog(null,"Select product that you want to remove","Error",JOptionPane.ERROR_MESSAGE);
                }
            }

            if (e.getSource()==btn2){
                for (Product p : ShoppingCart.cartProductArray){
                    int cartCount = p.getCount();
                    int count = p.getNoItems();
                    int returnCount = cartCount+count;
                    p.setNoItems(returnCount);
                }
                ShoppingCart.cartProductArray = new ArrayList<>();
                Interface1GUI.table.setModel(new Interface1TableModel(WestminsterShoppingManager.consoleProductList));
                JOptionPane.showMessageDialog(null,"Order canceled Successfully","Error",JOptionPane.ERROR_MESSAGE);
                //shoppingApp.showMainWindow();
                dispose();

            }

            if (e.getSource()==btn3){
                if (ShoppingCart.cartProductArray.size()>0){
                    ShoppingCart.cartProductArray = new ArrayList<>();
                    User user =UserData.getUser(LoginFrame.getUserName1(),LoginFrame.getPassword1());
                    user.setUserCount(1);
                    UserData.saveUsersToFile();
                    WestminsterShoppingManager manager = new WestminsterShoppingManager();
                    manager.saveProductsToFile();

                    JOptionPane.showMessageDialog(null,"Oder Placed Successfully","Success",JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                    //shoppingApp.openGUI();
                }else {
                    JOptionPane.showMessageDialog(null,"Place Your Order first","Error",JOptionPane.ERROR_MESSAGE);
                }

            }

            if (e.getSource()==btn4){
                dispose();
            }
        }
    }

}
