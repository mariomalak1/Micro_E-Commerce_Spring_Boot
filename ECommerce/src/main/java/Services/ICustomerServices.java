package Services;

import Models.Customer;
import Models.Order;

import java.util.List;

public interface ICustomerServices {
    Customer addCustomer(Customer c);
    Customer deleteCustomer(Customer c);
    Customer getCustomer (String Email);
    List<Customer> getAllCustomers();
}
