package EcommerceSystem.ECommerce.Services;

import EcommerceSystem.ECommerce.Models.Customer;
import EcommerceSystem.ECommerce.Models.NotificationTemplateModel;

public class NotificationSenderServices {
    private EmailServices emailServices;
    private BaseDecoratorServices baseDecoratorServices;
    private Customer customer;
    private NotificationTemplateModel notificationTemplateModel;

    public NotificationSenderServices(EmailServices emailServices, BaseDecoratorServices baseDecoratorServices, Customer customer, NotificationTemplateModel notificationTemplateModel) {
        this.emailServices = emailServices;
        this.baseDecoratorServices = baseDecoratorServices;
        this.customer = customer;
        this.notificationTemplateModel = notificationTemplateModel;
    }

    public void notifyCustomer(){

    }
}
