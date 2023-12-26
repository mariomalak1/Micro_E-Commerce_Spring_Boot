package Models;

import java.util.ArrayList;
import java.util.List;

public class CompoundOrder extends Order{
    private List<Order> Orders;
    private List<Customer> confirmedCustomers;

    public CompoundOrder(Customer customer){
        OrdersNumber++;
        OrderID = OrdersNumber;
        super.Customer = customer;
        super.ParentOrder = null;
        super.orderItemList = new ArrayList<>();
        Orders = new ArrayList<>();
        confirmedCustomers = new ArrayList<>();
    }

    public List<Order> getOrders() {
        return Orders;
    }

    public void setOrders(List<Order> orders) {
        Orders = orders;
    }

    public void addOrder(Order order){
        order.setParentOrder(this);
        Orders.add(order);
    }

    public void deleteOrder(Order order){
        order.setParentOrder(null);
        Orders.remove(order);
    }

    public Order getChildOrder(int id){
        for (Order o: Orders) {
            if (o.getOrderID() == id){
                return o;
            }
        }
        return null;
    }

    public void setConfirmedCustomers(List<Customer> customerList) {
        confirmedCustomers = customerList;
    }

    public void customerConfirm(Customer customer){
        confirmedCustomers.add(customer);
    }

    public void deleteConfirmedCustomer(Customer customer){
        confirmedCustomers.remove(customer);
    }

    public Customer getCustomerConfirmed(int id){
        for (Customer o: confirmedCustomers) {
            if (o.getCustomerID() == id){
                return o;
            }
        }
        return null;
    }


    @Override
    public double getTotalPrice() {
        double total = 0;

        for (Order o:Orders) {
            total += o.getTotalPrice();
        }

        for (OrderItem o: super.orderItemList) {
            total += o.getTotalPrice();
        }

        return total;
    }
}
