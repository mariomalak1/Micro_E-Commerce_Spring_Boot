package EcommerceSystem.ECommerce.Services;

import EcommerceSystem.ECommerce.Models.Customer;
import EcommerceSystem.ECommerce.Models.NotificationTemplateModel;
import EcommerceSystem.ECommerce.Models.Product;

public class NotifierServices {
    protected NotificationTemplateModel notifiacationTemplateModel;
    private Customer customer;
    private Product prodcts[];
    private QueueServices queueServices;

    public NotifierServices(Customer customer, Product[] prodcts) {
        this.customer = customer;
        this.prodcts = prodcts;
    }

    public String sendNotification(NotificationTemplateModel notificationTemplateModel){
        return queueServices.addNotification(notificationTemplateModel);
    }

}
