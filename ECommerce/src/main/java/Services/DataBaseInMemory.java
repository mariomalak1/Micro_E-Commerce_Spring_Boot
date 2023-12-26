package Services;

import Models.Customer;
import Models.Order;
import Models.Product;

import java.util.HashMap;

public class DataBaseInMemory {
    static HashMap<Integer, Order> orderList = new HashMap<Integer, Order>();
    static HashMap<String, Customer> customerList = new HashMap<String, Customer>();

    // is unique with his serial number
    static HashMap<String, Product> productList = new HashMap<String, Product>();
}
