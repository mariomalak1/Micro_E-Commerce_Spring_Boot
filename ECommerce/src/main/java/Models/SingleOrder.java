package Models;

import java.util.ArrayList;

public class SingleOrder extends Order {
    public SingleOrder(Customer customer){
        OrdersNumber++;
        OrderID = OrdersNumber;
        super.Customer = customer;
        customer.addOrder(this);
        super.ParentOrder = null;
        super.orderItemList = new ArrayList<>();
    }

    @Override
    public double getTotalPrice() {
        double total = 0;
        for (OrderItem o: super.orderItemList) {
            total += o.getTotalPrice();
        }
        return total;
    }

    @Override
    public void finishOrder() {
        // decrement the total price of the order from the customer
        Double newBalance = getCustomer().getBalance() - getTotalPrice();
        getCustomer().setBalance(newBalance);
        Finished = true;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Order : ID -> ").append(getOrderID()).append(" Customer Email -> ").append(getCustomer().getEmail());
        if (ParentOrder != null){
            str.append("Have Parent ID -> ").append(getParentOrder().getOrderID());
        }
        str.append("\n");
        for (OrderItem i: super.orderItemList) {
            str.append("\t");
            str.append(i.toString());
            str.append("\n");
        }
        return str.toString();
    }
}
