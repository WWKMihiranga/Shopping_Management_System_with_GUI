package GUI;

import Console.Clothing;
import Console.Electronics;
import Console.Product;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Map;

// ShoppingCartTableModel class to define the table model for the shopping cart display
class ShoppingCartTableModel extends AbstractTableModel {

    // Array to hold column names for the table
    private String[] columnNames = { "Product", "Quantity", "Price" };
    // ArrayList to store the products in the shopping cart
    private ArrayList<Product> tableList = new ArrayList<>();

    ShoppingCartTableModel(ArrayList<Product> cartProductArray) {
        tableList = cartProductArray;
    }

    // Get the number of rows in the table
    @Override
    public int getRowCount() {
        return tableList.size();
    }

    // Get the number of columns in the table
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Product product = tableList.get(rowIndex);

        if (columnIndex == 0) {
            if (product instanceof Electronics){
                return product.getProductId()+", "+product.getProductName()+", "+((Electronics) product).getBrand()+", "+((Electronics) product).getWarrantyPeriod();
            }else {
                return product.getProductId()+", "+product.getProductName()+", "+((Clothing) product).getClothSize()+", "+((Clothing) product).getClothColor();
            }

        } else if (columnIndex == 1) {
            return product.getCount(); // Assuming there's a getQuantity() method in your Product class-make it

        } else if (columnIndex == 2) {
            return Double.toString(product.getPrice()); // Assuming there's a getPrice() method in your Product class
        }    //product.getPrice()
        return null;
    }

    // Get the column name for a specific column index
    public String getColumnName(int col) {
        return columnNames[col];
    }

}