package EcommerceSystem.ECommerce.Services;

import EcommerceSystem.ECommerce.Models.Customer;
import EcommerceSystem.ECommerce.Models.Order;
import EcommerceSystem.ECommerce.Models.OrderItem;
import EcommerceSystem.ECommerce.Models.Product;

import java.util.HashMap;

public class DataBaseInMemory {
    static HashMap<Integer, Order> orderList = new HashMap<Integer, Order>();

    static HashMap<Integer, OrderItem> orderItemList = new HashMap<Integer, OrderItem>();

    static HashMap<String, Customer> customerList = new HashMap<String, Customer>();

    // is unique with his serial number
    static HashMap<String, Product> productList = new HashMap<String, Product>();
}
