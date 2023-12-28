package EcommerceSystem.ECommerce.Repositories;

import EcommerceSystem.ECommerce.Models.Order;

public class OrdersTable {
    private Order orders[];

    public Order[] getOrders() {
        return orders;
    }

    public void setOrders(Order[] orders) {
        this.orders = orders;
    }
}
