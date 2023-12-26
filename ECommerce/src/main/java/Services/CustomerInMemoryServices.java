package Services;

import Models.Customer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class CustomerInMemoryServices implements ICustomerServices{
    @Override
    public Customer addCustomer(Customer c) {
        // check that the customer is newly added
        if (DataBaseInMemory.customerList.get(c.getEmail()) == null){
            return DataBaseInMemory.customerList.put(c.getEmail(), c);
        }
        return null;
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
}
