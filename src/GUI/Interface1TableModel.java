package GUI;

import Console.Clothing;
import Console.Electronics;
import Console.Product;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

// Custom TableModel for the main interface table
class Interface1TableModel extends AbstractTableModel {

    // Column names for the table
    private String[] columnNames = { "Product ID", "Name", "Category", "Price (€)", "Info" };
    private ArrayList<Product> tableList = new ArrayList<>();

    // Constructor for the Interface1TableModel
    Interface1TableModel(ArrayList<Product> tableList) {
        this.tableList = tableList;
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

    // Get the value at a specific row and column in the table
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object temp = null;

        if (columnIndex == 0) {
            temp = tableList.get(rowIndex).getProductId();
        } else if (columnIndex == 1) {
            temp = tableList.get(rowIndex).getProductName();
        } else if (columnIndex == 2) {
            if (tableList.get(rowIndex) instanceof Electronics) {
                temp = "Electronics";
            } else {
                temp = "Clothing";
            }
        } else if (columnIndex == 3) {
            temp = tableList.get(rowIndex).getPrice();
        }

        else if (columnIndex == 4) {
            if (tableList.get(rowIndex) instanceof Electronics) {
                Electronics electronics = (Electronics) tableList.get(rowIndex);
                temp = electronics.getBrand() + " , "+ electronics.getWarrantyPeriod() + "Weeks" ;
            } else if (tableList.get(rowIndex) instanceof Clothing) {
                Clothing clothing=(Clothing) tableList.get(rowIndex);
                temp = clothing.getClothSize() + " , " + clothing.getClothColor();
            }
        }
        return temp;

    }

    // Needed to show column names in JTable
    public String getColumnName(int col) {
        return columnNames[col];
    }
}