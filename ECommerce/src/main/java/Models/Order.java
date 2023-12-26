package Models;

import java.util.List;

public abstract class Order {
    static int OrdersNumber = 0;
    int OrderID;
    protected Customer Customer;
    protected Order ParentOrder;
    protected List<OrderItem> orderItemList;
    protected Boolean Finished;


    public int getOrderID() {
        return OrderID;
    }

    public void setOrderID(int orderID) {
        OrderID = orderID;
    }
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

    public void finishOrder(){
        Finished = true;
    }

    public Boolean isFinished(){
        return Finished;
    }
}
