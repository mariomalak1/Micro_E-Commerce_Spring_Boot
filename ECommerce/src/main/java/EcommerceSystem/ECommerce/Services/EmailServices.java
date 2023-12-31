package EcommerceSystem.ECommerce.Services;

import EcommerceSystem.ECommerce.Models.NotificationTemplateModel;
public class EmailServices extends NotifierServices{

    @Override
    public void sendNotification(NotificationTemplateModel notificationTemplateModel) {
        notificationTemplateModel.AddToContent("From Email");
        queueServices.addNotification(notificationTemplateModel);
    }
}
