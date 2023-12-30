package EcommerceSystem.ECommerce.Models;

public class OrderShippmentNotificationModel extends NotificationTemplateModel{
    public OrderShippmentNotificationModel(Order order) {
        super(order,"the order {} for the customer {} had been shipped :)");
    }

    @Override
    public void setSubject() {
        super.subject="Shippment";
    }


    @Override
    public void setLangauges() {
        langauges.add("Arabic");
        langauges.add("English");

    }
    @Override
    public void setPlaceHolders(Order order) {
        String orderValue = String.valueOf(order.getOrderID());
        content = content.replaceFirst("\\{\\}", orderValue);
        content = content.replaceFirst("\\{\\}", order.getCustomer().getName());
    }

    @Override
    public NotificationTemplateModel clone() {
        return new OrderShippmentNotificationModel(order);
    }
}
