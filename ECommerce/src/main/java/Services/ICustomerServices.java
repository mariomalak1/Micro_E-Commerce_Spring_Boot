package Services;

import Models.Customer;
import Models.Order;

import java.util.List;

public interface ICustomerServices {
    Customer addCustomer(Customer c);
    Boolean deleteCustomer(Customer c);
    Customer getCustomer (int id);
    List<Customer> getAllCustomers();
}
