/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Store;

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

    }
}
