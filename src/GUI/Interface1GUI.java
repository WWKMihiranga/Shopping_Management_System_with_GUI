package GUI;

import Console.Clothing;
import Console.Electronics;
import Console.Product;
import Main.WestminsterShoppingManager;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.util.ArrayList;

// GUI class representing the main interface of the application
public class Interface1GUI extends JFrame {   //remove action listener
    private static ArrayList<Product> guiProductList;
    private int selectedRow;
    private int selectedItem;
    JButton shoppingCartButton, addCartButton, sortButton;
    static JTextArea area;
    JPanel northPanel, centerPanel, southPanel, extraPanel0, extraPanel1, extraPanel2;
    JLabel label,label1;
    static JTable table;
    JScrollPane pane;
    JComboBox<String> comboBox;
    Interface1TableModel tableModel;

    // Constructor for Interface1GUI
    public Interface1GUI(ArrayList<Product> productList, ArrayList<Product> shoppingCartList, ArrayList<User> userList) {

        // Initialization and setup of GUI components
        Interface1GUI.guiProductList = productList;

        this.setResizable(false);
        northPanel = new JPanel();
        this.add(northPanel, BorderLayout.NORTH);
        northPanel.setBackground(Color.WHITE);
        northPanel.setLayout(new FlowLayout());
        northPanel.setPreferredSize(new Dimension(736, 40));

        centerPanel = new JPanel();
        centerPanel.setBackground(Color.WHITE);
        this.add(centerPanel, BorderLayout.CENTER);
        centerPanel.setPreferredSize(new Dimension(736, 300));

        southPanel = new JPanel();
        this.add(southPanel, BorderLayout.SOUTH);
        southPanel.setBackground(Color.WHITE);
        southPanel.setPreferredSize(new Dimension(735, 130));
        southPanel.setLayout(new BorderLayout()); //

        extraPanel0 = new JPanel();
        extraPanel0.setBackground(Color.white);
        //extraPanel0.setLayout(new FlowLayout());
        northPanel.add(extraPanel0);

        label = new JLabel("Select Product Category");
        label1 = new JLabel("                                                                                    ");
        comboBox = new JComboBox<>(new String[]{"All", "Electronics", "Clothing"});
        extraPanel0.add(label);
        extraPanel0.add(comboBox);
        extraPanel0.add(label1);

        extraPanel1 = new JPanel();
        extraPanel1.setBackground(Color.white);
        extraPanel1.setLayout(new FlowLayout());
        northPanel.add(extraPanel1);

        sortButton = new JButton("Sort");
        shoppingCartButton = new JButton("Shopping Cart");
        extraPanel1.add(sortButton);
        extraPanel1.add(shoppingCartButton);
        shoppingCartButton.setBounds(100,40,30,30);
        sortButton.setBackground(new Color(208,233,233));
        shoppingCartButton.setBackground(new Color(208,233,233));

        tableModel = new Interface1TableModel(WestminsterShoppingManager.consoleProductList);
        table = new JTable(tableModel);
        pane = new JScrollPane(table);
        centerPanel.add(pane);
        pane.setPreferredSize(new Dimension(735, 280));

        extraPanel2 = new JPanel();
        extraPanel2.setBackground(Color.white);
        extraPanel2.setLayout(new FlowLayout());
        southPanel.add(extraPanel2);

        area = new JTextArea(5, 30);
        extraPanel2.add(area);
        area.setPreferredSize(new Dimension(300, 100));
        area.setBackground(new Color(208,233,233));

        addCartButton = new JButton("Add to Cart");
        extraPanel2.add(addCartButton);
        addCartButton.setPreferredSize(new Dimension(120, 30));
        addCartButton.setBackground(new Color(208,233,233));
        //addCartButton.setBorder(new LineBorder(new Color(115, 92, 156), 2));

        // Set up row colors based on product availability
        rowColor(WestminsterShoppingManager.consoleProductList);

        //ItemListener handler = new ItemListener();     //add by c
        table.addMouseListener(new DemoEventHandleClass());
        addCartButton.addActionListener(new DemoEventHandleClass());
        shoppingCartButton.addActionListener(new DemoEventHandleClass());
        comboBox.addItemListener(new ItemListener());
        sortButton.addActionListener(new DemoEventHandleClass());

    }

    // Inner class handling button clicks and mouse events
    private class DemoEventHandleClass extends MouseAdapter implements ActionListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            selectedRow = table.getSelectedRow();
            if(selectedItem ==0){
                area.setText(WestminsterShoppingManager.consoleProductList.get(selectedRow).displayProductInfo());
            }
            if(selectedItem == 1){
                area.setText(WestminsterShoppingManager.electronicsArrayListHold.get(selectedRow).displayProductInfo());
            }
            if (selectedItem == 2){
                area.setText(WestminsterShoppingManager.clothingArrayListHold.get(selectedRow).displayProductInfo());
            }
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == shoppingCartButton) {
                ShoppingCartGUI cartFrame = new ShoppingCartGUI();
                cartFrame.setTitle("Shopping Cart");
                cartFrame.setVisible(true);
                cartFrame.setResizable(false);

                cartFrame.setSize(550, 330);

            }
            if (e.getSource() == addCartButton) {
                if(selectedItem==0){
                    Product selectedProduct = WestminsterShoppingManager.consoleProductList.get(selectedRow);
                    ShoppingCart cartObject = new ShoppingCart();
                    if (selectedProduct.getNoItems()>0){
                        cartObject.addProduct(selectedProduct);
                        int itemCount = selectedProduct.getNoItems();
                        itemCount--;
                        selectedProduct.setNoItems(itemCount);
                        table.setModel(new Interface1TableModel(WestminsterShoppingManager.consoleProductList));
                        rowColor(WestminsterShoppingManager.consoleProductList);
                        area.setText(WestminsterShoppingManager.consoleProductList.get(selectedRow).displayProductInfo());
                    }
                    else JOptionPane.showMessageDialog(null,"Products are Over","Error",JOptionPane.ERROR_MESSAGE);
                }

                if(selectedItem==1){
                    Product selectedProduct = WestminsterShoppingManager.electronicsArrayListHold.get(selectedRow);
                    ShoppingCart cartObject = new ShoppingCart();
                    if (selectedProduct.getNoItems()>0){
                        cartObject.addProduct(selectedProduct);
                        int itemCount = selectedProduct.getNoItems();
                        itemCount--;
                        table.setModel(new Interface1TableModel(WestminsterShoppingManager.electronicsArrayListHold));
                        rowColor(WestminsterShoppingManager.electronicsArrayListHold);
                        selectedProduct.setNoItems(itemCount);
                    }
                    else JOptionPane.showMessageDialog(null,"Products are Over","Error",JOptionPane.ERROR_MESSAGE);
                }

                if(selectedItem==2){
                    Product selectedProduct = WestminsterShoppingManager.clothingArrayListHold.get(selectedRow);
//                GUI.ShoppingCart.cartProductArray.add(selectedProduct);
                    ShoppingCart cartObject = new ShoppingCart();
                    if (selectedProduct.getNoItems()>0){
                        cartObject.addProduct(selectedProduct);
                        int itemCount = selectedProduct.getNoItems();
                        itemCount--;
                        table.setModel(new Interface1TableModel(WestminsterShoppingManager.clothingArrayListHold));
                        rowColor(WestminsterShoppingManager.clothingArrayListHold);
                        selectedProduct.setNoItems(itemCount);
                    }
                    else JOptionPane.showMessageDialog(null,"Products are Over","Error",JOptionPane.ERROR_MESSAGE);

                }


            }
            if (e.getSource() == sortButton){
                if(selectedItem==0){
                    WestminsterShoppingManager.selectionSort(WestminsterShoppingManager.consoleProductList);
                    Interface1TableModel model = new Interface1TableModel(WestminsterShoppingManager.consoleProductList);
                    table.setModel(model);
                    table.setModel(new Interface1TableModel(WestminsterShoppingManager.consoleProductList));
                    rowColor(WestminsterShoppingManager.consoleProductList);
                }

            }
        }
    }

    // Inner class handling item state changes for the combo box
    private class ItemListener implements java.awt.event.ItemListener {

        @Override
        public void itemStateChanged(ItemEvent e) {

            if (e.getStateChange() == ItemEvent.SELECTED) {
                int number = comboBox.getSelectedIndex();

                switch (number) {
                    case 0:
                        sortButton.setVisible(true);
                        table.setModel(new Interface1TableModel(WestminsterShoppingManager.consoleProductList));
                        rowColor(WestminsterShoppingManager.consoleProductList);
                        selectedItem=0;
                        break;

                    case 1:
                        sortButton.setVisible(false);
                        area.setText("");
                        ArrayList<Product> electronicsArrayList = new ArrayList<Product>();
                        for (Product product : WestminsterShoppingManager.consoleProductList){
                            if(product instanceof Electronics){
                                electronicsArrayList.add(product);
                                WestminsterShoppingManager.electronicsArrayListHold.add(product);
                                selectedItem=1;
                            }
                        }
                        table.setModel(new Interface1TableModel(electronicsArrayList));
                        rowColor(WestminsterShoppingManager.electronicsArrayListHold);
                        break;

                    case 2:
                        sortButton.setVisible(false);
                        area.setText("");
                        ArrayList<Product> clothingArrayList = new ArrayList<Product>();
                        for (Product product : WestminsterShoppingManager.consoleProductList){
                            if(product instanceof Clothing){
                                clothingArrayList.add(product);
                                WestminsterShoppingManager.clothingArrayListHold.add(product);
                                selectedItem=2;
                            }
                        }
                        table.setModel(new Interface1TableModel(clothingArrayList));
                        rowColor(clothingArrayList);
                        break;

                }
            }
        }
    }

    private void rowColor(ArrayList<Product> productList) {
        for (int i = 0; i < productList.size(); i++) {// for (Product product : productList) {
            int row = i + 1;
            Product product = productList.get(i);
            if (product.getNoItems() < 3) {
                table.getColumnModel().getColumn(0).setCellRenderer((TableCellRenderer) new CustomRenderer());
                table.getColumnModel().getColumn(1).setCellRenderer((TableCellRenderer) new CustomRenderer());
                table.getColumnModel().getColumn(2).setCellRenderer((TableCellRenderer) new CustomRenderer());
                table.getColumnModel().getColumn(3).setCellRenderer((TableCellRenderer) new CustomRenderer());
                table.getColumnModel().getColumn(4).setCellRenderer((TableCellRenderer) new CustomRenderer());
            }
        }
    }

    // Inner class handling custom rendering for table cells
    private class CustomRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus, int row, int column) {
            Component rendererComp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row,
                    column);

            // Check if the product's noItems is less than 3
            if(selectedItem==0){
                if (WestminsterShoppingManager.consoleProductList.get(row).getNoItems() < 3) {
                rendererComp.setBackground(new Color(242,170,174));

                } else {
                    // Reset the background color for other rows
                    rendererComp.setBackground(table.getBackground());
                    rendererComp.setForeground(Color.BLACK);
                }
            }

            if(selectedItem==1){
                if (WestminsterShoppingManager.electronicsArrayListHold.get(row).getNoItems() < 3) {
                    rendererComp.setBackground(new Color(242,170,174));

                } else {
                    // Reset the background color for other rows
                    rendererComp.setBackground(table.getBackground());
                    rendererComp.setForeground(Color.BLACK);
                }
            }

            if(selectedItem==2){
                if (WestminsterShoppingManager.clothingArrayListHold.get(row).getNoItems() < 3) {
                    rendererComp.setBackground(new Color(242,170,174));

                } else {
                    // Reset the background color for other rows
                    rendererComp.setBackground(table.getBackground());
                    rendererComp.setForeground(Color.BLACK);
                }
            }

            return rendererComp;
        }
    }

}


