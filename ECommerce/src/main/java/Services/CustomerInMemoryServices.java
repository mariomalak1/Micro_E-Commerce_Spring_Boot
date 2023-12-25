package Services;

import Models.Customer;
import Models.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CustomerInMemoryServices implements ICustomerServices{
    @Override
    public Customer addCustomer(Customer c) {
        // check that the customer is newly added
        if (DataBaseInMemory.customerList.get(c.getCustomerID()) == null){
            return DataBaseInMemory.customerList.put(c.getCustomerID(), c);
        }
        return null;
    }

    @Override
    public Customer deleteCustomer(Customer c) {
        return DataBaseInMemory.customerList.remove(c.getCustomerID());
    }

    @Override
    public Customer getCustomer(int id) {
        return DataBaseInMemory.customerList.get(id);
    }

    @Override
    public List<Customer> getAllCustomers() {
        Set<Integer> customersIDs = DataBaseInMemory.customerList.keySet();
        List<Customer> customerList = new ArrayList<>();
        for (Integer id: customersIDs){
            customerList.add(DataBaseInMemory.customerList.get(id));
        }
        return customerList;
    }
}
