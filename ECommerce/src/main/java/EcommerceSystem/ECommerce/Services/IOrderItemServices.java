package EcommerceSystem.ECommerce.Services;

import EcommerceSystem.ECommerce.Models.CompoundOrder;
import EcommerceSystem.ECommerce.Models.Customer;
import EcommerceSystem.ECommerce.Models.Order;
import EcommerceSystem.ECommerce.Models.OrderItem;

import java.util.List;

public interface IOrderItemServices {
    OrderItem AddOrderItem(OrderItem orderItem, Order order);
    OrderItem removeOrderItem(OrderItem orderItem);
    List<OrderItem> getAllOrderItemsInOrder(Order order);
    OrderItem getOrderItem(int id);
}
