package Models;

import java.util.ArrayList;

public class SingleOrder extends Order{
    public SingleOrder(Customer customer){
        OrdersNumber++;
        OrderID = OrdersNumber;
        super.Customer = customer;
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
}
