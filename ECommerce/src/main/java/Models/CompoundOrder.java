package Models;

import java.util.ArrayList;
import java.util.List;

public class CompoundOrder extends Order{
    List<Order> Orders;
    public CompoundOrder(Customer customer){
        OrdersNumber++;
        OrderID = OrdersNumber;
        super.Customer = customer;
        super.ParentOrder = null;
        super.orderItemList = new ArrayList<>();
        Orders = new ArrayList<>();
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
