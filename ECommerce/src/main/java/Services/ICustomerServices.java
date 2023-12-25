package Services;

import Models.Customer;
import Models.Order;

import java.util.List;

public interface ICustomerServices {
    Customer addCustomer(Order o);
    Boolean deleteCustomer(Order o);
    Customer getCustomer (int id);
    List<Customer> getAllCustomers();
}
