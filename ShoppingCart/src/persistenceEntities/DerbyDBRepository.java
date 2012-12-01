/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistenceEntities;

import Internal.CollectionTransformer;
import Repositories.OrderRepository.IOrderRepository;
import Repositories.OrderRepository.OrderSummary;
import Repositories.ProductRepository.IProductRepository;
import Repositories.ProductRepository.Product;
import Repositories.ProductRepository.ProductInfo;
import Repositories.UserRepository.AccessPrivileges;
import Repositories.UserRepository.BillingInformation;
import Repositories.UserRepository.IUserRepository;
import Repositories.UserRepository.UserInfo;
import Store.ProductSize;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.MathContext;
import java.net.InetAddress;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import org.apache.derby.drda.NetworkServerControl;

/**
 *
 * @author volen
 */
public class DerbyDBRepository implements IOrderRepository, IUserRepository, IProductRepository {

    private final EntityManager mManager;
    private final HashSet<String> mLoggedInEmails;
    private boolean mDisposed;

    public DerbyDBRepository() throws Exception {

        NetworkServerControl serverControl = new NetworkServerControl();
        serverControl.start(new PrintWriter(System.out, true));

        NetworkServerControl server = null;
        server = new NetworkServerControl(InetAddress.getLocalHost(), 1527, "Volen", "Miguel");
        server.start(new PrintWriter(System.out));

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("ShoppingCartPU");
        mManager = factory.createEntityManager();
        mLoggedInEmails = new HashSet<>();
    }

    public void Dispose() {
        if (!mDisposed) {
            mManager.close();
            mDisposed = true;
        }
    }

    @Override
    public Set<OrderSummary> getOrdersByCustomer(String emailAddress) {

        Users user = getUser(emailAddress, "", false);
        if (user == null) {
            return null;
        }

        return new CollectionTransformer<Orders, OrderSummary>() {
            @Override
            public OrderSummary transform(Orders e) {
                return convert(e);
            }
        }.transform(user.getOrdersSet());

    }

    @Override
    public OrderSummary completeOrder(OrderSummary summary) {
        Users owner = getUser(summary.CustomerEmail, "", false);
        if (owner == null) {
            return null;
        }

        Orders order = convert(summary, owner);
        mManager.getTransaction().begin();
        mManager.persist(order);
        mManager.getTransaction().commit();
        mManager.refresh(owner);
        return convert(order);
    }

    @Override
    public String getNextOrderNumber() {
        TypedQuery<Orders> query = mManager.createNamedQuery("Orders.findAll", Orders.class);
        List<Orders> results = query.getResultList();
        return results.size() + "";
    }

    @Override
    public BigDecimal getTotalRevenue(Date from, Date to) {
        List<Orders> results = getAllOrders(from, to);
        BigDecimal revenue = BigDecimal.ZERO;
        for (Orders order : results) {
            if (order.getOrderDate().after(from) && order.getOrderDate().before(to)) {
                revenue = revenue.add(order.getTotalRevenue());
            }
        }

        return revenue;
    }

    @Override
    public BigDecimal getTotalExpense(Date from, Date to) {
        List<Orders> results = getAllOrders(from, to);
        BigDecimal expense = BigDecimal.ZERO;
        for (Orders order : results) {
            if (order.getOrderDate().after(from) && order.getOrderDate().before(to)) {
                expense = expense.add(order.getTotalCost());
            }
        }

        return expense;
    }

    @Override
    public UserInfo login(String emailAddress, String password) {
        Users user = getUser(emailAddress, password, true);
        if (user == null) {
            return null;
        }

        mLoggedInEmails.add(user.getEmailAddress());
        return convert(user);
    }

    @Override
    public void logout(UserInfo user) {
        mLoggedInEmails.remove(user.EmailAddress);
    }

    @Override
    public UserInfo changePassword(String email, String currentPassword, String newPassword) {
        if (newPassword == null) {
            return null;
        }
        Users user = getUser(email, currentPassword, true);
        if (user == null) {
            return null;
        }
        user.setPasscode(newPassword);
        mManager.getTransaction().begin();
        mManager.merge(user);
        mManager.getTransaction().commit();
        return convert(user);
    }

    @Override
    public UserInfo createUser(UserInfo info) {

        boolean initialInvocation;
        // Special case where the no users exist in the database
        TypedQuery<Users> query = mManager.createNamedQuery("Users.findAll", Users.class);
        List<Users> results = query.getResultList();
        initialInvocation = results.isEmpty();

        Users user = getUser(info.EmailAddress, info.AccessToken, false);
        if (user != null) {
            return (user.getPasscode().equals(info.AccessToken)) ? convert(user) : null;
        }

        user = convert(info);
        if (initialInvocation) // Promote first user have full access
        {
            user.setAccessPrivileges(convert(initialInvocation ? AccessPrivileges.Full : AccessPrivileges.Limited));
        }


        mManager.getTransaction().begin();
        mManager.merge(user);
        mManager.getTransaction().commit();
        return convert(user);
    }

    @Override
    public void deleteUser(UserInfo info) {

        Users user = getUser(info.EmailAddress, info.AccessToken, true);
        if (user != null) {
            mManager.getTransaction().begin();
            mManager.remove(user);
            mManager.getTransaction().commit();
        }
    }

    @Override
    public Set<BillingInformation> getBillingInfo(UserInfo info) {
        Users user = getUser(info.EmailAddress, info.AccessToken, true);
        if (user == null) {
            return null;
        }

        mManager.refresh(user);

        return new CollectionTransformer<Payment, BillingInformation>() {
            @Override
            public BillingInformation transform(Payment e) {
                return convert(e);
            }
        }.transform(user.getPaymentSet());
    }

    @Override
    public BillingInformation addBillingInfo(UserInfo owner, BillingInformation info) {
        Users user = getUser(owner.EmailAddress, owner.AccessToken, true);
        if (user == null) {
            return null;
        }

        for (Payment p : user.getPaymentSet()) {
            if (p.getPaymentMethodName().equals(info.getCardNumber())) {
                return convert(p);
            }
        }

        Payment payment = convert(info, user);
        mManager.getTransaction().begin();
        mManager.persist(payment);
        mManager.getTransaction().commit();
        return convert(payment);
    }

    @Override
    public void removeBillingInfo(UserInfo owner, BillingInformation info) {
        Users user = getUser(owner.EmailAddress, owner.AccessToken, true);
        if (user == null) {
            return;
        }

        Payment toRemove = null;
        for (Payment payment : user.getPaymentSet()) {
            if (payment.getPaymentMethodName().equals(info.getCardNumber())) {
                toRemove = payment;
            }
        }

        if (toRemove != null) {
            mManager.getTransaction().begin();
            mManager.remove(toRemove);
            mManager.refresh(user);
            mManager.getTransaction().commit();
        }
    }

    @Override
    public UserInfo authorizeUser(UserInfo managerInfo, UserInfo userInfo) {
        if (managerInfo == null) {
            return null;
        }

        Users manager = getUser(managerInfo.EmailAddress, managerInfo.AccessToken, true);
        if (manager == null || manager.getAccessPrivileges() != convert(AccessPrivileges.Full)) {
            return null;
        }

        Users user = getUser(userInfo.EmailAddress, userInfo.AccessToken, true);
        if (user == null || user.getAccessPrivileges() == convert(AccessPrivileges.Full)) {
            return user == null ? null : convert(user);
        }

        user.setAccessPrivileges(convert(AccessPrivileges.Full));
        mManager.getTransaction().begin();
        mManager.merge(user);
        mManager.getTransaction().commit();
        return convert(user);
    }

    @Override
    public UserInfo deauthorizeUser(UserInfo managerInfo, UserInfo userInfo) {
        if (managerInfo == null) {
            return null;
        }

        Users manager = getUser(managerInfo.EmailAddress, managerInfo.AccessToken, true);
        if (manager == null || manager.getAccessPrivileges() != convert(AccessPrivileges.Full)) {
            return null;
        }

        Users user = getUser(userInfo.EmailAddress, userInfo.AccessToken, true);
        if (user == null || user.getAccessPrivileges() == convert(AccessPrivileges.Limited)) {
            return user == null ? null : convert(user);
        }

        user.setAccessPrivileges(convert(AccessPrivileges.Limited));
        mManager.getTransaction().begin();
        mManager.merge(user);
        mManager.getTransaction().commit();
        return convert(user);
    }

    @Override
    public Set<Product> getProducts() {
        TypedQuery<persistenceEntities.Product> query = mManager.createNamedQuery("Product.findAll", persistenceEntities.Product.class);
        List<persistenceEntities.Product> results = query.getResultList();

        return new CollectionTransformer<persistenceEntities.Product, Product>() {
            @Override
            public Product transform(persistenceEntities.Product e) {
                return convert(e);
            }
        }.transform(results);
    }

    @Override
    public ProductInfo getProductInfo(String sku) {

        persistenceEntities.Product p = getProductBySku(sku);
        Product product = convert(p);

        ProductInfo info = new ProductInfo();
        info.setProduct(product);
        info.setInStock(p.getInStock());
        info.setPurchasePrice(trim(p.getPurchasePrice()));

        return info;
    }

    @Override
    public boolean reserveProduct(String sku, int count) {
        ProductInfo info = getProductInfo(sku);
        return info.getInStock() >= count;
    }

    @Override
    public void updateProductInfo(ProductInfo info) {
        persistenceEntities.Product product = convert(info);

        mManager.getTransaction().begin();
        mManager.merge(product);
        mManager.getTransaction().commit();
    }

    @Override
    public void addNewProduct(ProductInfo info) {
        persistenceEntities.Product product = convert(info);

        mManager.getTransaction().begin();
        mManager.merge(product);
        mManager.getTransaction().commit();
    }

    @Override
    public void removeProduct(ProductInfo info) {

        assert (info != null);
        if (info == null) {
            return;
        }

        persistenceEntities.Product product = getProductBySku(info.getProduct().getSKU());
        mManager.getTransaction().begin();
        mManager.remove(product);
        mManager.getTransaction().commit();
    }

    private List<Orders> getAllOrders(Date from, Date to) {
        TypedQuery<Orders> query = mManager.createNamedQuery("Orders.findByOrderRange", Orders.class);
        query.setParameter("from", from);
        query.setParameter("to", to);
        return query.getResultList();
    }

    private Users getUser(String email, String password, boolean matchPassword) {
        TypedQuery<Users> query = mManager.createNamedQuery("Users.findByEmailAddress", Users.class);
        query.setParameter("emailAddress", email);
        List<Users> results = query.getResultList();
        assert (results.size() == 1 || results.isEmpty());
        if (results.isEmpty() || (matchPassword && !results.get(0).getPasscode().equals(password))) {
            return null;
        }

        return results.get(0);
    }

    private persistenceEntities.Product getProductBySku(String sku) {
        TypedQuery<persistenceEntities.Product> query = mManager.createNamedQuery("Product.findBySku", persistenceEntities.Product.class);
        query.setParameter("sku", sku);
        List<persistenceEntities.Product> results = query.getResultList();
        if (results.isEmpty()) {
            return null;
        }

        assert (results.size() == 1);

        persistenceEntities.Product p = results.get(0);
        return p;
    }

    private static BigDecimal trim(BigDecimal decimal) {
        return decimal.round(MathContext.DECIMAL32);
    }

    // <editor-fold defaultstate="collapsed" desc="entities convertion methods">      
    private static Product convert(persistenceEntities.Product product) {
        Product p = new Product(product.getSku());
        p.setCategory(product.getCategory());
        p.setColor(product.getColor());
        p.setSize(convert2(product.getProductSize()));
        p.setDescription(product.getDescription());
        p.setSalePrice(trim(product.getSalePrice()));
        p.setTitle(product.getTitle());
        return p;
    }

    private static persistenceEntities.Product convert(ProductInfo product) {
        persistenceEntities.Product p = new persistenceEntities.Product(product.getProduct().getSKU());
        p.setCategory(product.getProduct().getCategory());
        p.setColor(product.getProduct().getColor());
        p.setProductSize(convert(product.getProduct().getSize()));
        p.setDescription(product.getProduct().getDescription());
        p.setSalePrice(trim(product.getProduct().getSalePrice()));
        p.setTitle(product.getProduct().getTitle());
        p.setInStock(product.getInStock());
        p.setPurchasePrice(trim(product.getPurchasePrice()));
        return p;
    }

    private static int convert(ProductSize size) {
        switch (size) {
            case Small:
                return 0;
            case Medium:
                return 1;
            case Large:
                return 2;
            case ExtraLarge:
            default:
                return 3;
        }
    }

    private static ProductSize convert2(int size) {
        switch (size) {
            case 0:
                return ProductSize.Small;
            case 1:
                return ProductSize.Medium;
            case 2:
                return ProductSize.Large;
            case 3:
            default:
                return ProductSize.ExtraLarge;
        }
    }

    private static AccessPrivileges convert(int value) {
        switch (value) {
            case 1:
                return AccessPrivileges.Full;
            case 0:
            default:
                return AccessPrivileges.Limited;
        }
    }

    private static int convert(AccessPrivileges privileges) {
        switch (privileges) {
            case Full:
                return 1;
            case Limited:
            default:
                return 0;
        }
    }

    private static Users convert(UserInfo user) {
        Users result = new Users();
        result.setEmailAddress(user.EmailAddress);
        result.setAccessPrivileges(convert(user.Privileges));
        result.setFirstName(user.FirstName);
        result.setLastName(user.LastName);
        result.setPasscode(user.AccessToken);
        result.setPhoneNumber(user.PhoneNumber);

        return result;
    }

    private static UserInfo convert(Users user) {
        UserInfo result = new UserInfo(user.getFirstName(), user.getLastName(), user.getEmailAddress(), user.getPasscode(), user.getPhoneNumber(),
                convert(user.getAccessPrivileges()));
        return result;
    }

    private static OrderSummary convert(Orders order) {
        OrderSummary result = new OrderSummary(order.getOrderNumber(), order.getOrderDate(), order.getEmailAddress().getEmailAddress(),
                convert(order.getPaymentMethodName()), order.getTotalRevenue(), order.getTotalDiscount(), order.getTotalDiscount());
        return result;
    }

    private static Orders convert(OrderSummary summary, Users owner) {
        Orders result = new Orders(summary.OrderNumber);
        result.setEmailAddress(owner);
        result.setOrderDate(summary.OrderDate);
        result.setPaymentMethodName(convert(summary.PaymentMethod, owner));
        result.setTotalCost(trim(summary.CostTotal));
        result.setTotalDiscount(trim(summary.DiscountTotal));
        result.setTotalRevenue(trim(summary.RevenueTotal));
        return result;
    }

    private static BillingInformation convert(Payment payment) {
        BillingInformation result = new BillingInformation();
        result.setAccountHolder(payment.getOwner().getEmailAddress());
        result.setAddressLine1(payment.getAddressLineOne());
        result.setAddressLine2(payment.getAddressLineTwo());
        result.setCardNumber(payment.getPaymentMethodName()); //NOTE: payment method name is actually the card number
        result.setSecurityCode(payment.getSecurityCode());
        result.setCity(payment.getCity());
        result.setExpirationMonth(payment.getExpirationMonth());
        result.setExpirationYear(payment.getExpirationYear());
        result.setZipCode(payment.getZip());
        result.setState(payment.getState());
        return result;
    }

    private static Payment convert(BillingInformation payment, Users accountHolder) {

        Payment result = new Payment(payment.getCardNumber()); // NOTE: the card number is the payment method name
        result.setAddressLineOne(payment.getAddressLine1());
        result.setAddressLineTwo(payment.getAddressLine2());
        result.setCity(payment.getCity());
        result.setZip(payment.getZipCode());
        result.setSecurityCode(payment.getSecurityCode());
        result.setEmailAddress(accountHolder);
        result.setSecurityCode(payment.getSecurityCode());
        result.setCity(payment.getCity());
        result.setExpirationMonth(payment.getExpirationMonth());
        result.setExpirationYear(payment.getExpirationYear());
        result.setState(payment.getState());
        return result;
    }
    // </editor-fold>
}