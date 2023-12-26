package Services;

import Models.Order;
import Models.OrderItem;

import java.util.List;

public interface IOrderItemServices {
    OrderItem AddOrderItem(OrderItem orderItem, Order order);
    OrderItem removeOrderItem(OrderItem orderItem);
    List<OrderItem> getAllOrderItemsInOrder(Order order);
    OrderItem getOrderItem(int id);
}
