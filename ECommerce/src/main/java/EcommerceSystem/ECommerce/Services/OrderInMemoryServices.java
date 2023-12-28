package EcommerceSystem.ECommerce.Services;

import EcommerceSystem.ECommerce.Models.CompoundOrder;
import EcommerceSystem.ECommerce.Models.Customer;
import EcommerceSystem.ECommerce.Models.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

@Service
public class OrderInMemoryServices implements IOrderServices{
    @Override
    public Order addOrder(Order o) {
        Order order = DataBaseInMemory.orderList.get(o.getOrderID());
        if (order == null){
            return DataBaseInMemory.orderList.put(o.getOrderID(), o);
        }
        return order;
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

    @Override
    public Order getUnFinishedOrderForCustomer(Customer customer) {
        List<Order> orders = getAllOrdersForCustomer(customer);
        for (Order order : orders){
            if (!order.isFinished() && order.getParentOrder() == null){
                return order;
            }
        }
        return null;
    }

    @Override
    public List<CompoundOrder> getAllOrdersNeededToConfirmForCustomer(Customer customer) {
        return DataBaseInMemory.orderNeededToConfirm.get(customer);
    }

    @Override
    public CompoundOrder confirmOrderByCustomer(Customer customer, int orderID){
        List<CompoundOrder> orders = DataBaseInMemory.orderNeededToConfirm.get(customer);
        for (CompoundOrder o : orders) {
            if (o.getOrderID() == orderID){
                orders.remove(o);
                DataBaseInMemory.orderNeededToConfirm.put(customer, orders);
                return o;
            }
        }
        return null;
    }

    @Override
    public CompoundOrder addOrderNeedToConfirm(Customer customer, int compoundOrderID){
        Order order = getOrder(compoundOrderID);
        if (order instanceof CompoundOrder compoundOrder){
            List<CompoundOrder> compoundOrders = DataBaseInMemory.orderNeededToConfirm.get(customer);
            if (compoundOrders == null){
                compoundOrders = new ArrayList<>();
                addIsNewInList(compoundOrders, compoundOrder);
                DataBaseInMemory.orderNeededToConfirm.put(customer, compoundOrders);
            }else{

                compoundOrders.add(compoundOrder);
            }
            return compoundOrder;
        }
        return null;
    }

    <T> void addIsNewInList(List<T> list, T entity){
        for (T i : list){
            if (i == entity){
                return;
            }
        }
        list.add(entity);
    }
}
