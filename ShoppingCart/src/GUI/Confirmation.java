/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package GUI;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.MouseAdapter;
import javax.swing.JFrame;

/**
 *
 * @author Miguel Silva
 */
public class Confirmation extends javax.swing.JFrame {

    /**
     * Creates new form Confirmation
     */
    public Confirmation() {
        super("Welcome to V&M Online Shop");
        initComponents();
        //Set frame size and resizable
        setSize(900, 500);
        setResizable(true);   
        //Place frame in the middle of screen        
        Toolkit toolkit = getToolkit();
        Dimension size = toolkit.getScreenSize();
        setLocation(size.width/2 - getWidth()/2, size.height/2 - getHeight()/2);
    }

        public void close(){

        WindowEvent winCLosingEvent = new WindowEvent(this,WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winCLosingEvent);

        }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlConfirmation = new javax.swing.JPanel();
        btnDone = new javax.swing.JButton();
        btnRegurnToProducts = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPurchaseSummary = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        pnlConfirmation.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dynamic Title", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14), java.awt.Color.black)); // NOI18N

        btnDone.setText("DONE");
        btnDone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDoneActionPerformed(evt);
            }
        });

        btnRegurnToProducts.setText("RETURN TO PRODUCTS");
        btnRegurnToProducts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegurnToProductsActionPerformed(evt);
            }
        });

        jLabel1.setText("jLabel1");

        jLabel2.setText("jLabel2");

        tblPurchaseSummary.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "DESCRIPTION", "SIZE", "COLOR", "QUANTITY", "PRICE"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblPurchaseSummary);

        javax.swing.GroupLayout pnlConfirmationLayout = new javax.swing.GroupLayout(pnlConfirmation);
        pnlConfirmation.setLayout(pnlConfirmationLayout);
        pnlConfirmationLayout.setHorizontalGroup(
            pnlConfirmationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlConfirmationLayout.createSequentialGroup()
                .addComponent(btnRegurnToProducts)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnDone))
            .addGroup(pnlConfirmationLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlConfirmationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlConfirmationLayout.createSequentialGroup()
                        .addGroup(pnlConfirmationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 501, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 620, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlConfirmationLayout.setVerticalGroup(
            pnlConfirmationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlConfirmationLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 85, Short.MAX_VALUE)
                .addGroup(pnlConfirmationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDone)
                    .addComponent(btnRegurnToProducts)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlConfirmation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlConfirmation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
       public void Events()
    {
        /*
        TableColumn qtyColumn = jTable1.getColumnModel().getColumn(4);
        JComboBox comboBox = new JComboBox();
        comboBox.addItem("1");
        comboBox.addItem("2");
        comboBox.addItem("3");
        comboBox.addItem("4");
        comboBox.addItem("5");
        comboBox.addItem("6");
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        qtyColumn.setCellEditor(new DefaultCellEditor(comboBox));
        qtyColumn.setCellRenderer(renderer);
        */
        
         tblPurchaseSummary.setModel(new javax.swing.table.DefaultTableModel(
            getselectedProducts(),
            new String [] {
                "DESCRIPTION", "SIZE", "COLOR", "QUANTITY", "PRICE"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        
    }
    
    
    private void btnDoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDoneActionPerformed
        close();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }//GEN-LAST:event_btnDoneActionPerformed

    private void btnRegurnToProductsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegurnToProductsActionPerformed
        close();
        WelcomePage w = new WelcomePage();
        w.setVisible(true);
    }//GEN-LAST:event_btnRegurnToProductsActionPerformed

    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Confirmation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Confirmation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Confirmation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Confirmation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Confirmation().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDone;
    private javax.swing.JButton btnRegurnToProducts;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pnlConfirmation;
    private javax.swing.JTable tblPurchaseSummary;
    // End of variables declaration//GEN-END:variables

    private Object [][] products = new Object [][] {        
        {new Boolean(false), "Beach T-Shirt", "L", "Purple", "1", new Double(10.5), 1},
        {new Boolean(false),"Beach T-Shirt", "L", "Blue", "1", new Double(11.5), 2},
        {new Boolean(false),"Beach T-Shirt", "L", "White", "1", new Double(12.5), 3},
        {new Boolean(false),"Beach T-Shirt", "XL", "Purple", "1", new Double(13.5), 4},
        {new Boolean(false),"Beach T-Shirt", "XL", "Blue", "1", new Double(11.5), 5},
        {new Boolean(false),"Beach T-Shirt", "XL", "White", "1", new Double(12.5), 6},
        {new Boolean(false),"T-Shirt", "L", "Purple", "1", new Double(10.5), 7},
        {new Boolean(false),"T-Shirt", "L", "Blue", "1", new Double(11.5), 8},
        {new Boolean(false),"T-Shirt", "L", "White", "1", new Double(12.5), 9},
        {new Boolean(false),"T-Shirt", "XL", "Purple", "1", new Double(13.5), 10},
        {new Boolean(false),"T-Shirt", "XL", "Blue", "1", new Double(11.5), 11},
        {new Boolean(false),"T-Shirt", "XL", "White", "1", new Double(12.5), 12}                
    };

    
    private String [][] selectedProducts;
    public void setselectedProducts(String [][] p){
        selectedProducts = p;                     
    }
    
    private  Object [][] getselectedProducts()
    {
        Object [][] p = new Object [selectedProducts.length][7];
        
        for (int i = 0; i < selectedProducts.length; i++)
        {
            for (int j = 0; j < products.length; j++)
            {
                if (Integer.parseInt(selectedProducts[i][1]) == (Integer)products[j][6])
                {
                    p[i][0] = products[j][0];
                    p[i][1] = products[j][1];
                    p[i][2] = products[j][2];
                    p[i][3] = products[j][3];
                    p[i][4] = products[j][4];
                    p[i][5] = products[j][5];
                    p[i][6] = products[j][6];
                }
            }
        }
        
        return p;
    }

    
    
}