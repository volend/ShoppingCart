/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UnitTests;

import Repositories.OrderRepository.OrderSummary;
import Repositories.ProductRepository.Product;
import Repositories.ProductRepository.ProductInfo;
import Repositories.UserRepository.AccessPrivileges;
import Repositories.UserRepository.BillingInformation;
import Repositories.UserRepository.UserInfo;
import Store.ProductSize;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import persistenceEntities.DerbyDBRepository;

/**
 *
 * @author volen
 */
public class RepositoriesTest {

    private static DerbyDBRepository sMainRepository;

    public RepositoriesTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        sMainRepository = new DerbyDBRepository();
    }

    @AfterClass
    public static void tearDownClass() {
        if (sMainRepository != null) {
            sMainRepository.Dispose();
            sMainRepository = null;
        }
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
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

        Assert.assertTrue("Admin user not found", adminUser != null);
        Assert.assertTrue("Admin user does not have full privileges", adminUser.Privileges == AccessPrivileges.Full);

        u1 = sMainRepository.createUser(u1);
        Assert.assertTrue(u1 != null);

        u2 = sMainRepository.createUser(u2);
        Assert.assertTrue(u2 != null);

        u3 = sMainRepository.createUser(u3);
        Assert.assertTrue(u3 != null);

        u4 = sMainRepository.createUser(u4);
        Assert.assertTrue(u4 != null);

        sMainRepository.deleteUser(u1);
        u1 = sMainRepository.login(u1.EmailAddress, u1.AccessToken);
        Assert.assertTrue(u1 == null);

        u2 = sMainRepository.deauthorizeUser(adminUser, u2);
        Assert.assertTrue(u2.Privileges == AccessPrivileges.Limited);

        sMainRepository.authorizeUser(null, u2);
        Assert.assertTrue("User should not be authorized without a valid manager", u2.Privileges == AccessPrivileges.Limited);

        u2 = sMainRepository.authorizeUser(adminUser, u2);
        Assert.assertTrue("User should be authorized since manager accound is provided", u2.Privileges == AccessPrivileges.Full);
    }

    @Test
    public void paymentsTest() {
        UserInfo adminUser = sMainRepository.login("vdimitro@fau.edu", "miguel");
        Assert.assertTrue("Admin user not found", adminUser != null);

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
        Assert.assertTrue("Billing info not found", info != null);

        sMainRepository.removeBillingInfo(adminUser, info);
        infos = sMainRepository.getBillingInfo(adminUser);
        Assert.assertTrue("Billing info was not deleted", infos.isEmpty());

        info = sMainRepository.addBillingInfo(adminUser, info);
        Assert.assertTrue("Billing info was not added", info != null);
    }

    @Test
    public void orderTest() {
        UserInfo adminUser = sMainRepository.login("vdimitro@fau.edu", "miguel");
        Assert.assertTrue("Admin user not found", adminUser != null);

        Set<OrderSummary> orders = sMainRepository.getOrdersByCustomer(adminUser.EmailAddress);
        if (orders.isEmpty()) {
            Set<BillingInformation> infos = sMainRepository.getBillingInfo(adminUser);
            Assert.assertTrue("No billing info found", !infos.isEmpty());


            String orderNumber = sMainRepository.getNextOrderNumber();
            OrderSummary summary = new OrderSummary(orderNumber, new Date(System.currentTimeMillis()),
                    adminUser.EmailAddress, infos.iterator().next(),
                    new BigDecimal(120), new BigDecimal(20), new BigDecimal(50));
            sMainRepository.completeOrder(summary);
        }

        orders = sMainRepository.getOrdersByCustomer(adminUser.EmailAddress);
        OrderSummary order = orders.iterator().next();
        Assert.assertTrue("Order not found", order != null);

        Calendar c = new GregorianCalendar();
        c.roll(Calendar.MONTH, false);
        Date before = c.getTime();
        c.roll(Calendar.MONTH, true);
        c.roll(Calendar.MONTH, true);
        Date after = c.getTime();

        assert (before.before(order.OrderDate));
        assert (after.after(order.OrderDate));

        BigDecimal totalRevenue = sMainRepository.getTotalRevenue(after, before);
        Assert.assertTrue("Expected zero revenue", totalRevenue == BigDecimal.ZERO);

        totalRevenue = sMainRepository.getTotalRevenue(before, after);
        Assert.assertTrue(String.format("Expected positive revenue. received = %s", totalRevenue), totalRevenue.longValue() > BigDecimal.ZERO.longValue());
    }

    @Test
    public void productsTest() {
        HashSet<ProductInfo> products = initializeProducts();
        for (ProductInfo info : products) {
            sMainRepository.addNewProduct(info);
        }

        Set<Product> storedProducts = sMainRepository.getProducts();
        Assert.assertTrue("Count of stored items is different", products.size() == storedProducts.size());

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
        p1.setDescription("Amazing T-Shirt, perfect replica of the original 1994 World Championship official T-Shirt.");
        p1.setTitle("Official FIFA T-Shirt");
        p1.setSalePrice(new BigDecimal(49.99f));

        set.add(getProductInfo(p1));

        p1 = new Product("3590003");
        p1.setCategory("T-Shirts");
        p1.setColor("Green");
        p1.setSize(ProductSize.Small);
        p1.setDescription("Amazing T-Shirt, perfect replica of the original 1994 World Championship official T-Shirt.");
        p1.setTitle("Official FIFA T-Shirt");
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