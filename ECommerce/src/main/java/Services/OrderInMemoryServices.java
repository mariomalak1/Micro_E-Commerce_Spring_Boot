package Services;

import Models.Customer;
import Models.Order;
import Services.DataBaseInMemory;

import java.util.HashMap;
import java.util.List;

public class OrderInMemoryServices implements IOrderServices{
    @Override
    public Order addOrder(Order o) {
//        DataBaseInMemory.orderList.entrySet(Order);
        return null;
    }

    @Override
    public Boolean deleteOrder(Order o) {
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
