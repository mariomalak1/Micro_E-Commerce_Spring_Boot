package Services;

import Models.Customer;
import Models.Order;
import Models.Product;

import java.util.HashMap;

public class DataBaseInMemory {
    static HashMap<Integer, Order> orderList = new HashMap<Integer, Order>();
    static HashMap<Integer, Customer> customerList = new HashMap<Integer, Customer>();
    static HashMap<Integer, Product> productList = new HashMap<Integer, Product>();
}
