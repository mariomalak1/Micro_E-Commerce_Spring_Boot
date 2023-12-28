package EcommerceSystem.ECommerce.Models;

import java.util.List;

public abstract class Order {
    static int OrdersNumber = 0;
    int OrderID;
    protected Customer Customer;
    protected Order ParentOrder;
    protected List<OrderItem> orderItemList;
    protected Boolean Finished;


    // make the order finished and decrement the customer or customers balance
    public abstract void finishOrder();

    public Boolean isFinished(){
        if (Finished == null){
            return false;
        }
        return Finished;
    }


    // getters setters

    public int getOrderID() {
        return OrderID;
    }
    public void setOrderID(int orderID) {
        OrderID = orderID;
    }

    public abstract double getTotalPrice();

    public Customer getCustomer() {
        return Customer;
    }

    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public Order getParentOrder() {
        return ParentOrder;
    }

    public void setCustomer(Customer customer) {
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
        orderItemList.remove(orderItem);
    }

    public OrderItem getOrderItem(int id){
        for (OrderItem i:orderItemList) {
            if (i.getID() == id){
                return i;
            }
        }
        return null;
    }
}
