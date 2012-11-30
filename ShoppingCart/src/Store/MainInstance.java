/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Store;

import GUI.WelcomePage;
import Repositories.OrderRepository.IOrderRepository;
import Repositories.ProductRepository.IProductRepository;
import Repositories.UserRepository.IUserRepository;
import persistenceEntities.DerbyDBRepository;

/**
 *
 * @author volen
 */
// Administrator account: UserInfo("Volen", "Dimitrov", "vdimitro@fau.edu", "Miguel", "44100001", AccessPrivileges.Full);
public class MainInstance {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        DerbyDBRepository mainRepository = new DerbyDBRepository();
        
        
        Store.initializeStore((IProductRepository) mainRepository, (IUserRepository) mainRepository, (IOrderRepository) mainRepository);


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
