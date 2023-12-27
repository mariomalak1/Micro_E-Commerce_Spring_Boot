package EcommerceSystem.ECommerce.Services;

import EcommerceSystem.ECommerce.Models.Customer;
import EcommerceSystem.ECommerce.Models.NotificationTemplateModel;
import EcommerceSystem.ECommerce.Models.Product;

public class BaseDecoratorServices extends NotifierServices{

    private NotifierServices notifier;
    public BaseDecoratorServices(Customer customer, Product[] prodcts) {
        super(customer, prodcts);
    }

    @Override
    public String sendNotification(NotificationTemplateModel notificationTemplateModel) {
        return super.sendNotification(notificationTemplateModel);
    }
}
