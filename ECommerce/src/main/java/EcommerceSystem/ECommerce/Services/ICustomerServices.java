package EcommerceSystem.ECommerce.Services;

import EcommerceSystem.ECommerce.Models.Customer;
import EcommerceSystem.ECommerce.Models.Order;

import java.util.List;

public interface ICustomerServices {
    Customer addCustomer(Customer c);
    Customer deleteCustomer(Customer c);
    Customer getCustomer (String Email);
    List<Customer> getAllCustomers();
    Customer getCustomerIsLogged(String emails);
}

