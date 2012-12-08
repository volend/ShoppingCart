/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Store.ProductSize;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author volen
 */
public class ProductDataTable extends DefaultTableModel {
    
    private ArrayList<ProductWrapper> mProducts;
    private final static int REMOVE = 0;
    private final static int DESCRIPTION = 1;
    private final static int SIZE = 2;
    private final static int COLOR = 3;
    private final static int QUANTITY = 4;
    private final static int PRICE = 5;
    private final static int[] columns = new int[]{REMOVE, DESCRIPTION, SIZE, COLOR, QUANTITY, PRICE};
    private final static String[] columnsStringsR = new String[]{"REMOVE", "DESCRIPTION", "SIZE", "COLOR", "QUANTITY", "PRICE"};
    private final static String[] columnsStringsA = new String[]{"SELECTED", "DESCRIPTION", "SIZE", "COLOR", "QUANTITY", "PRICE"};
    private final static boolean[] typesEditable = new boolean[]{true, false, false, false, true, false};
    private final static Class[] types = new Class[]{java.lang.Boolean.class, java.lang.String.class, ProductSize.class, java.lang.String.class, java.lang.Integer.class, BigDecimal.class};
    private int mUpdating;
    
    public ProductDataTable(boolean add) {
        super(add ? columnsStringsA : columnsStringsR, 0);
    }
    
    @Override
    public int getRowCount() {
        return getProducts().size();
    }
    
    @Override
    public int getColumnCount() {
        return columns.length;
    }
    
    @Override
    public Object getValueAt(int row, int column) {
        
        ProductWrapper wrapper = getProducts().get(row);
        
        switch (column) {
            case REMOVE:
                return wrapper.isSelected();
            case DESCRIPTION:
                return wrapper.getDescription();
            case SIZE:
                return wrapper.getSize();
            case COLOR:
                return wrapper.getColor();
            case QUANTITY:
                return wrapper.getQuantity();
            case PRICE:
                return wrapper.getPrice();
            default:
                assert (false);
                return null;
        }
    }
    
    @Override
    public void setValueAt(Object value, int row, int column) {
        
        ProductWrapper wrapper = getProducts().get(row);
        
        switch (column) {
            case REMOVE:
                wrapper.setSelected((boolean) value);
                return;
            case QUANTITY:
                wrapper.setQUantity((int) value);
                return;
            case PRICE:
            case DESCRIPTION:
            case SIZE:
            case COLOR:
            default:
                assert (false);
        }
    }
    
    public void addSelectedProduct(ProductWrapper wrapper) {
        assert (wrapper != null);
        getProducts().add(wrapper);
    }
    
    @Override
    public Class getColumnClass(int columnIndex) {
        return types[columnIndex];
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return typesEditable[columnIndex];
    }
    boolean[] canEdit = new boolean[]{
        true, false, false, false, true, false
    };
    
    public Set<ProductWrapper> getSelectedProducts() {
        return getProducts(true);
    }
    
    public Set<ProductWrapper> getAllProducts() {
        return getProducts(false);
    }
    
    public void BeginUpdate() {
        mUpdating++;
    }
    
    public void EndUpdate() {
        if (--mUpdating == 0) {
            super.fireTableRowsInserted(0, getProducts().size() - 1);
        }
    }
    
    public void addProduct(ProductWrapper product) {
        if (getProducts().contains(product)) {
            return;
        }
        getProducts().add(product);
        if (mUpdating == 0) {
            super.fireTableRowsInserted(getProducts().size() - 1, getProducts().size() - 1);
        }
    }
    
    public void removeSelectedProducts() {
        for (int index = getProducts().size() - 1; index >= 0; index--) {
            if (getProducts().get(index).isSelected()) {
                getProducts().remove(index);
            }
        }
        
        super.fireTableDataChanged();
    }
    
    private Set<ProductWrapper> getProducts(boolean onlySelected) {
        Set<ProductWrapper> selectedProducts = new HashSet<>();
        for (ProductWrapper wrapper : getProducts()) {
            if (!onlySelected || wrapper.isSelected()) {
                selectedProducts.add(wrapper);
            }
        }
        
        return selectedProducts;
    }
    
    private ArrayList<ProductWrapper> getProducts() {
        if (mProducts == null) {
            mProducts = new ArrayList<>();
        }
        return mProducts;
    }
    
    public void unselectAll() {
        for (ProductWrapper wrapper : getProducts()) {
            wrapper.setSelected(false);
        }
    }

    void clearItems() {
        mProducts.clear();
        super.fireTableDataChanged();
    }
}