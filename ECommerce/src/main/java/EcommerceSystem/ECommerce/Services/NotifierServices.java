package EcommerceSystem.ECommerce.Services;

import EcommerceSystem.ECommerce.Models.NotificationTemplateModel;
public abstract class NotifierServices {
    protected NotificationTemplateModel notifiacationTemplateModel;
    protected QueueServices queueServices = new QueueServices();
    public abstract void sendNotification(NotificationTemplateModel notificationTemplateModel);
}
