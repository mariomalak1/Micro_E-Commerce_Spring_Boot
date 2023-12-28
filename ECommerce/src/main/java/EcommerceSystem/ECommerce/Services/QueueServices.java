package EcommerceSystem.ECommerce.Services;

import EcommerceSystem.ECommerce.Models.NotificationTemplateModel;
import EcommerceSystem.ECommerce.Repositories.NotificationQueue;

public class QueueServices {
    private NotificationQueue notificationQueue;

    public String displayNotification(){
        return notificationQueue.getNotifications().toString();
    }
    public String addNotification(NotificationTemplateModel notification){
        return "";
    }
}
