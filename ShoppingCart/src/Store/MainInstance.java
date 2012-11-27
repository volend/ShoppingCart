/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Store;

import GUI.WelcomePage;
import Repositories.OrderRepository.IOrderRepository;
import Repositories.OrderRepository.OrderRepository;
import Repositories.ProductRepository.IProductRepository;
import Repositories.ProductRepository.ProductInfo;
import Repositories.ProductRepository.ProductRepository;
import Repositories.UserRepository.IUserRepository;
import Repositories.UserRepository.UserRepository;
import java.awt.Color;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author volen
 */
public class MainInstance {

    private static IOrderRepository mOrderRepository = new OrderRepository();
    private static IProductRepository mProductRepository = new ProductRepository();
    private static IUserRepository mUserRepository = new UserRepository();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Store.initializeStore(mProductRepository, mUserRepository, mOrderRepository);


        // Put your code for starting the main JForm here.
        // You can access the main instance like this: Store.getInstance()
        
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
            java.util.logging.Logger.getLogger(WelcomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(WelcomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(WelcomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(WelcomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new WelcomePage().setVisible(true);
         

            }
        });

    }
}
