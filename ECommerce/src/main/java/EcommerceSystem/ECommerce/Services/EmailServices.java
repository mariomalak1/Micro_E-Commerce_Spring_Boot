package EcommerceSystem.ECommerce.Services;

import EcommerceSystem.ECommerce.Models.Customer;
import EcommerceSystem.ECommerce.Models.NotificationTemplateModel;
import EcommerceSystem.ECommerce.Models.Product;

public class EmailServices extends NotifierServices{

    public EmailServices(Customer customer, Product[] prodcts) {
        super(customer, prodcts);
    }

    @Override
    public String sendNotification(NotificationTemplateModel notificationTemplateModel) {
        return super.sendNotification(notificationTemplateModel);
    }
}
