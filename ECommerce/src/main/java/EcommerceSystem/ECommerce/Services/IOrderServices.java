package EcommerceSystem.ECommerce.Services;

import EcommerceSystem.ECommerce.Models.Customer;
import EcommerceSystem.ECommerce.Models.Order;

import java.util.List;

public interface IOrderServices {
    Order addOrder(Order o);
    Order deleteOrder(Order o);
    Order getOrder (int id);
    List<Order> getAllOrdersForCustomer(Customer c);

    Order getUnFinishedOrderForCustomer(Customer customer);
}
