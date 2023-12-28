package EcommerceSystem.ECommerce.Repositories;

import EcommerceSystem.ECommerce.Models.Customer;

public class CustomersTable {
    private Customer customers[];

    public Customer[] getCustomers() {
        return customers;
    }

    public void setCustomers(Customer[] customers) {
        this.customers = customers;
    }
}
