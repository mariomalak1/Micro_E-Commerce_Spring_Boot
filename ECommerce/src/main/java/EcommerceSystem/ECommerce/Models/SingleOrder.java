package EcommerceSystem.ECommerce.Models;

import java.util.ArrayList;
import java.util.List;

public class SingleOrder extends Order {
    public SingleOrder(Customer customer){
        OrdersNumber++;
        OrderID = OrdersNumber;
        super.Customer = customer;
        super.ParentOrder = null;
        super.orderItemList = new ArrayList<>();
    }
    @Override
    public List<Product> getAllProductsInTheOrder() {
        List<Product> productList = new ArrayList<>();
        for (OrderItem orderItem: super.orderItemList) {
            productList.add(orderItem.getProduct());
        }
        return productList;
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
    public Boolean finishOrder() {
        // decrement the total price of the order from the customer
        double newBalance = getCustomer().getBalance() - getTotalPrice();
        if (newBalance < 0){
            return false;
        }
        getCustomer().setBalance(newBalance);
        Finished = true;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Order : ID -> ").append(getOrderID()).append(" Customer Email -> ").append(getCustomer().getEmail());
        if (ParentOrder != null){
            str.append(" Have Parent ID -> ").append(getParentOrder().getOrderID());
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
