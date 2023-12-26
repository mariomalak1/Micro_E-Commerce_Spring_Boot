package Services;

import Models.Order;
import Models.OrderItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class OrderItemInMemoryServices implements IOrderItemServices{
    @Override
    public OrderItem AddOrderItem(OrderItem orderItem, Order order) {
        // check that the order item is newly added
        if (DataBaseInMemory.orderItemList.get(orderItem.getID()) == null){
            return DataBaseInMemory.orderItemList.put(orderItem.getID(), orderItem);
        }
        return null;
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
