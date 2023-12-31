package EcommerceSystem.ECommerce.Models;

import java.util.ArrayList;
import java.util.List;

public class CompoundOrder extends Order {
    private List<Order> Orders;
    private List<Customer> confirmedCustomers;

    public CompoundOrder(Customer customer) {
        OrdersNumber++;
        OrderID = OrdersNumber;
        super.Customer = customer;
        super.ParentOrder = null;
        super.orderItemList = new ArrayList<>();
        Orders = new ArrayList<>();
        confirmedCustomers = new ArrayList<>();
    }
    @Override
    public List<Product> getAllProductsInTheOrder() {
        List<Product> productList = new ArrayList<>();
        for (OrderItem orderItem: super.orderItemList) {
            productList.add(orderItem.getProduct());
        }
        for (Order o: Orders) {
            productList.addAll(o.getAllProductsInTheOrder());
        }
        return productList;
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

    @Override
    public Boolean finishOrder() {
        double newBalance;
        for (Order o: Orders) {
            // decrement the total price of the order from the customer and finish it
            if(!o.finishOrder()){
                return false;
            }
        }
        newBalance = getCustomer().getBalance() - totalPriceForOrderItemsOnly();
        getCustomer().setBalance(newBalance);
        Finished = true;
        return true;
    }


    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Compound Order : ID -> ").append(getOrderID()).append(" Customer Email -> ").append(getCustomer().getEmail()).append("\n");
        for (OrderItem o: super.orderItemList) {
            str.append("\t");
            str.append(o.toString());
            str.append("\n");
        }
        for (Order o: this.Orders) {
            str.append("\t");
            str.append(o.toString());
            str.append("\n");
        }
        return str.toString();
    }

    // setters getters

    public List<Order> getOrders() {
        return Orders;
    }

    public void setOrders(List<Order> orders) {
        Orders = orders;
    }

    public void addOrder(Order order) {
        for (Order o :Orders) {
            if (o == order){
                return;
            }
        }
        order.setParentOrder(this);
        Orders.add(order);
    }

    public void deleteOrder(Order order) {
        order.setParentOrder(null);
        Orders.remove(order);
    }

    public Order getChildOrder(int id) {
        for (Order o : Orders) {
            if (o.getOrderID() == id) {
                return o;
            }
        }
        return null;
    }

    public void setConfirmedCustomers(List<Customer> customerList) {
        confirmedCustomers = customerList;
    }

    public void customerConfirm(Customer customer) {
        confirmedCustomers.add(customer);
    }

    public void deleteConfirmedCustomer(Customer customer) {
        confirmedCustomers.remove(customer);
    }

    public Customer getCustomerConfirmed(int id) {
        for (Customer o : confirmedCustomers) {
            if (o.getCustomerID() == id) {
                return o;
            }
        }
        return null;
    }

    public List<EcommerceSystem.ECommerce.Models.Customer> getConfirmedCustomers() {
        return confirmedCustomers;
    }

    public double totalPriceForOrderItemsOnly() {
        double total = 0;
        for (OrderItem o : orderItemList) {
            total += o.getTotalPrice();
        }
        return total;
    }
}
