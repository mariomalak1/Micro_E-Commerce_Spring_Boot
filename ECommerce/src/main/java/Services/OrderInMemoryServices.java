package Services;

import Models.Customer;
import Models.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

@Service
public class OrderInMemoryServices implements IOrderServices{
    @Override
    public Order addOrder(Order o) {
        return DataBaseInMemory.orderList.put(o.getOrderID(), o);
    }

    @Override
    public Order deleteOrder(Order o) {
        return DataBaseInMemory.orderList.remove(o.getOrderID());
    }

    @Override
    public Order getOrder(int id) {
        return DataBaseInMemory.orderList.get(id);
    }

    @Override
    public List<Order> getAllOrdersForCustomer(Customer c) {
        Set<Integer> orderIDs = DataBaseInMemory.orderList.keySet();
        List<Order> orders = new ArrayList<>();
        for (Integer id: orderIDs) {
            Order order = DataBaseInMemory.orderList.get(id);
            if (order.getCustomer() == c){
                orders.add(order);
            }
        }
        return orders;
    }
}
