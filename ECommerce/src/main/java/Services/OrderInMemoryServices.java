package Services;

import Models.Customer;
import Models.Order;
import Services.DataBaseInMemory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderInMemoryServices implements IOrderServices{
    @Override
    public Order addOrder(Order o) {
        return null;
    }

    @Override
    public Order deleteOrder(Order o) {
        return null;
    }

    @Override
    public Order getOrder(int id) {
        return null;
    }

    @Override
    public List<Order> getAllOrdersForCustomer(Customer c) {
        return null;
    }
}
