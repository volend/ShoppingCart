/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Store;

import GUI.WelcomePage;
import Repositories.OrderRepository.IOrderRepository;
import Repositories.OrderRepository.OrderSummary;
import Repositories.ProductRepository.IProductRepository;
import Repositories.ProductRepository.Product;
import Repositories.ProductRepository.ProductInfo;
import Repositories.UserRepository.AccessPrivileges;
import Repositories.UserRepository.BillingInformation;
import Repositories.UserRepository.IUserRepository;
import Repositories.UserRepository.UserInfo;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;
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
    public static void main(String[] args) throws Exception {

        if (args != null && args.length == 1) {
            String value = args[0];
            if (value.equals("create")) {
                RepositoriesTest test = new RepositoriesTest();
                RepositoriesTest.setUpClass();
                test.usersTest();
                test.paymentsTest();
                test.orderTest();
                test.productsTest();
                RepositoriesTest.tearDownClass();
            }
        }

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

    private static class RepositoriesTest {

        private static DerbyDBRepository sMainRepository;

        private RepositoriesTest() {
        }

        public static void setUpClass() throws Exception {
            sMainRepository = new DerbyDBRepository();
        }

        public static void tearDownClass() {
            if (sMainRepository != null) {
                sMainRepository.Dispose();
                sMainRepository = null;
            }
        }

        public void usersTest() {
            UserInfo u1 = new UserInfo("Peter", "Thompson", "pthompson@fau.edu", "passkey1", "44100001", AccessPrivileges.Limited);
            UserInfo u2 = new UserInfo("Mark", "Lu", "mlu@fau.edu", "passkey2", "44100002", AccessPrivileges.Limited);
            UserInfo u3 = new UserInfo("Tom", "Chen", "tchen@fau.edu", "passkey3", "44100003", AccessPrivileges.Limited);
            UserInfo u4 = new UserInfo("Stanley", "Frank", "sfrank@fau.edu", "passkey4", "44100004", AccessPrivileges.Full);


            UserInfo adminUser = sMainRepository.login("vdimitro@fau.edu", "miguel");
            if (adminUser == null) {
                adminUser = new UserInfo("Volen", "Dimitrov", "vdimitro@fau.edu", "miguel", "(561)213-1612", AccessPrivileges.Full);
                adminUser = sMainRepository.createUser(adminUser);
            }


            u1 = sMainRepository.createUser(u1);

            u2 = sMainRepository.createUser(u2);

            sMainRepository.createUser(u3);

            sMainRepository.createUser(u4);

            sMainRepository.deleteUser(u1);
            sMainRepository.login(u1.EmailAddress, u1.AccessToken);

            u2 = sMainRepository.deauthorizeUser(adminUser, u2);

            sMainRepository.authorizeUser(null, u2);

            sMainRepository.authorizeUser(adminUser, u2);
        }

        public void paymentsTest() {
            UserInfo adminUser = sMainRepository.login("vdimitro@fau.edu", "miguel");

            BillingInformation info = new BillingInformation();
            info.setAccountHolder(adminUser.EmailAddress);
            info.setAddressLine1("My address line 1");
            info.setAddressLine2("My address line 2");
            info.setCity("Boca Raton");
            info.setState("Florida");
            info.setZipCode("33433");
            info.setCardNumber("4148411226346765");
            info.setSecurityCode(480);
            info.setExpirationMonth(5);
            info.setExpirationYear(2015);

            Set<BillingInformation> infos = sMainRepository.getBillingInfo(adminUser);
            if (infos.isEmpty()) {
                info = sMainRepository.addBillingInfo(adminUser, info);
            }

            sMainRepository.removeBillingInfo(adminUser, info);
            infos = sMainRepository.getBillingInfo(adminUser);

            info = sMainRepository.addBillingInfo(adminUser, info);
        }

        public void orderTest() {
            UserInfo adminUser = sMainRepository.login("vdimitro@fau.edu", "miguel");

            Set<OrderSummary> orders = sMainRepository.getOrdersByCustomer(adminUser.EmailAddress);
            if (orders.isEmpty()) {
                Set<BillingInformation> infos = sMainRepository.getBillingInfo(adminUser);


                String orderNumber = sMainRepository.getNextOrderNumber();
                OrderSummary summary = new OrderSummary(orderNumber, new Date(System.currentTimeMillis()),
                        adminUser.EmailAddress, infos.iterator().next(),
                        new BigDecimal(120), new BigDecimal(20), new BigDecimal(50));
                sMainRepository.completeOrder(summary);
            }

            orders = sMainRepository.getOrdersByCustomer(adminUser.EmailAddress);
            OrderSummary order = orders.iterator().next();

            Calendar c = new GregorianCalendar();
            c.roll(Calendar.MONTH, false);
            Date before = c.getTime();
            c.roll(Calendar.MONTH, true);
            c.roll(Calendar.MONTH, true);
            Date after = c.getTime();

            assert (before.before(order.OrderDate));
            assert (after.after(order.OrderDate));

            BigDecimal totalRevenue = sMainRepository.getTotalRevenue(after, before);

            totalRevenue = sMainRepository.getTotalRevenue(before, after);
        }

        public void productsTest() {
            HashSet<ProductInfo> products = initializeProducts();
            for (ProductInfo info : products) {
                sMainRepository.addNewProduct(info);
            }

            Set<Product> storedProducts = sMainRepository.getProducts();

        }

        private HashSet<ProductInfo> initializeProducts() {
            HashSet<ProductInfo> set = new HashSet<>();

            Product p1 = new Product("3590001");
            p1.setCategory("T-Shirts");
            p1.setColor("Yellow");
            p1.setSize(ProductSize.Small);
            p1.setDescription("Amazing T-Shirt, perfect replica of the original 1994 World Championship official T-Shirt.");
            p1.setTitle("Official FIFA T-Shirt");
            p1.setSalePrice(new BigDecimal(49.99f));


            set.add(getProductInfo(p1));

            p1 = new Product("3590002");
            p1.setCategory("T-Shirts");
            p1.setColor("Blue");
            p1.setSize(ProductSize.Small);
            p1.setDescription("Best T-Shirt, for little money.");
            p1.setTitle("Official UEFA T-Shirt");
            p1.setSalePrice(new BigDecimal(49.99f));

            set.add(getProductInfo(p1));

            p1 = new Product("3590003");
            p1.setCategory("T-Shirts");
            p1.setColor("Green");
            p1.setSize(ProductSize.Small);
            p1.setDescription("Nice T-Shirt, replica of the Formula 1.");
            p1.setTitle("Official F1 T-Shirt");
            p1.setSalePrice(new BigDecimal(43.89f));

            set.add(getProductInfo(p1));

            p1 = new Product("3590004");
            p1.setCategory("T-Shirts");
            p1.setColor("Yellow");
            p1.setSize(ProductSize.Large);
            p1.setDescription("Amazing T-Shirt, perfect replica of the original 1994 World Championship official T-Shirt.");
            p1.setTitle("Official FIFA T-Shirt");
            p1.setSalePrice(new BigDecimal(39.99f));

            set.add(getProductInfo(p1));

            p1 = new Product("3590005");
            p1.setCategory("T-Shirts");
            p1.setColor("Yelow");
            p1.setSize(ProductSize.ExtraLarge);
            p1.setDescription("Amazing T-Shirt, perfect replica of the original 1994 World Championship official T-Shirt.");
            p1.setTitle("Official FIFA T-Shirt");
            p1.setSalePrice(new BigDecimal(45.65f));

            set.add(getProductInfo(p1));

            p1 = new Product("3590006");
            p1.setCategory("T-Shirts");
            p1.setColor("Green");
            p1.setSize(ProductSize.Medium);
            p1.setDescription("Amazing T-Shirt, perfect replica of the original 1994 World Championship official T-Shirt.");
            p1.setTitle("Official FIFA T-Shirt");
            p1.setSalePrice(new BigDecimal(42.11f));

            set.add(getProductInfo(p1));

            p1 = new Product("3590007");
            p1.setCategory("T-Shirts");
            p1.setColor("Green");
            p1.setSize(ProductSize.Large);
            p1.setDescription("Amazing T-Shirt, perfect replica of the original 1994 World Championship official T-Shirt.");
            p1.setTitle("Official FIFA T-Shirt");
            p1.setSalePrice(new BigDecimal(47.23f));

            set.add(getProductInfo(p1));

            p1 = new Product("3590008");
            p1.setCategory("T-Shirts");
            p1.setColor("Green");
            p1.setSize(ProductSize.Medium);
            p1.setDescription("Amazing T-Shirt, perfect replica of the original 1994 World Championship official T-Shirt.");
            p1.setTitle("Official FIFA T-Shirt");
            p1.setSalePrice(new BigDecimal(44.51f));

            set.add(getProductInfo(p1));

            p1 = new Product("3590009");
            p1.setCategory("T-Shirts");
            p1.setColor("Pink");
            p1.setSize(ProductSize.Small);
            p1.setDescription("Amazing T-Shirt, perfect replica of the original 1994 World Championship official T-Shirt.");
            p1.setTitle("Official FIFA T-Shirt");
            p1.setSalePrice(new BigDecimal(50.51f));

            set.add(getProductInfo(p1));

            return set;
        }

        private ProductInfo getProductInfo(Product p) {
            ProductInfo info = new ProductInfo();
            info.setProduct(p);
            info.setInStock(15);
            info.setPurchasePrice(new BigDecimal(11.23f));
            return info;
        }
    }
}
