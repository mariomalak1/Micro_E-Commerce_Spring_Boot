package Models;

import java.util.List;

public abstract class Order {
    private Customer Customer;
    private Order ParentOrder = null;
    private List<OrderItem> orderItemList;

    public abstract double getTotalPrice();

    public Models.Customer getCustomer() {
        return Customer;
    }

    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public Order getParentOrder() {
        return ParentOrder;
    }

    public void setCustomer(Models.Customer customer) {
        Customer = customer;
    }

    public void setOrderItemList(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }

    public void setParentOrder(Order parentOrder) {
        ParentOrder = parentOrder;
    }

    public void addOrderItem(OrderItem orderItem){
        orderItemList.add(orderItem);
    }

    public void removeOrderItem(OrderItem orderItem){

    }
}
