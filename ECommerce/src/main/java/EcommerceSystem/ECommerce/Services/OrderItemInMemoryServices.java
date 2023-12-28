package EcommerceSystem.ECommerce.Services;

import EcommerceSystem.ECommerce.Models.CompoundOrder;
import EcommerceSystem.ECommerce.Models.Customer;
import EcommerceSystem.ECommerce.Models.Order;
import EcommerceSystem.ECommerce.Models.OrderItem;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class OrderItemInMemoryServices implements IOrderItemServices{
    @Override
    public OrderItem AddOrderItem(OrderItem orderItem, Order order) {
        // check that the order item is newly added
        OrderItem orderItem1 = DataBaseInMemory.orderItemList.get(orderItem.getID());
        if (orderItem1 == null){
            DataBaseInMemory.orderItemList.put(orderItem.getID(), orderItem);
            return orderItem;
        }
        return orderItem1;
    }

    @Override
    public OrderItem removeOrderItem(OrderItem orderItem) {
        return DataBaseInMemory.orderItemList.remove(orderItem.getID());
    }

    @Override
    public List<OrderItem> getAllOrderItemsInOrder(Order order) {
        Set<Integer> setOfIDs = DataBaseInMemory.orderItemList.keySet();
        ArrayList<OrderItem> orderItemArrayList = new ArrayList<>();
        for (Integer i: setOfIDs) {
            OrderItem orderItem = DataBaseInMemory.orderItemList.get(i);
            if (orderItem.getOrder() == order){
                orderItemArrayList.add(orderItem);
            }
        }
        return orderItemArrayList;
    }

    @Override
    public OrderItem getOrderItem(int id) {
        return DataBaseInMemory.orderItemList.get(id);
    }
}
