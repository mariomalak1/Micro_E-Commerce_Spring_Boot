package EcommerceSystem.ECommerce.Services;

import EcommerceSystem.ECommerce.Models.Customer;
import EcommerceSystem.ECommerce.Models.Order;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class CustomerInMemoryServices implements ICustomerServices{
    @Override
    public Customer addCustomer(Customer c) {
        // check that the customer is newly added
        Customer customer = DataBaseInMemory.customerList.get(c.getEmail());
        if (customer == null){
            DataBaseInMemory.customerList.put(c.getEmail(), c);
            return c;
        }
        return customer;
    }

    @Override
    public Customer deleteCustomer(Customer c) {
        return DataBaseInMemory.customerList.remove(c.getEmail());
    }

    @Override
    public Customer getCustomer(String email) {
        return DataBaseInMemory.customerList.get(email);
    }

    @Override
    public List<Customer> getAllCustomers() {
        Set<String> customersEmails = DataBaseInMemory.customerList.keySet();
        List<Customer> customerList = new ArrayList<>();
        for (String email: customersEmails){
            customerList.add(DataBaseInMemory.customerList.get(email));
        }
        return customerList;
    }

    public Customer getCustomerIsLogged(String email){
        Customer customer = getCustomer(email);
        if (customer != null) {
            if (customer.isLogged()) {
                return customer;
            }
        }
        return null;
    }
}

