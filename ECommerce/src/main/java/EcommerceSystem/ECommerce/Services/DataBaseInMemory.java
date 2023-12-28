package EcommerceSystem.ECommerce.Services;

import EcommerceSystem.ECommerce.Models.*;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class DataBaseInMemory {
    static HashMap<Integer, Order> orderList = new HashMap<Integer, Order>();

    static HashMap<Integer, OrderItem> orderItemList = new HashMap<Integer, OrderItem>();

    static HashMap<String, Customer> customerList = new HashMap<String, Customer>();

    // email of customer and list of orders needed to confirm
    static Map<Customer, List<CompoundOrder>> orderNeededToConfirm = new HashMap<>();

    // is unique with his serial number
    static HashMap<String, Product> productList = new HashMap<String, Product>();
}
