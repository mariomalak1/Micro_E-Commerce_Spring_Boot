package Services;

import Models.Customer;
import Models.Order;

import java.util.List;

public interface IOrderServices {
    Order addOrder(Order o);
    Boolean deleteOrder(Order o);
    Order getOrder (int id);
    List<Order> getAllOrdersForCustomer(Customer c);
}
