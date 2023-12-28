package EcommerceSystem.ECommerce.Repositories;

import EcommerceSystem.ECommerce.Models.NotificationTemplateModel;

import java.util.Queue;

public class NotificationQueue {
    private Queue<NotificationTemplateModel> notifications;

    public String sendMessage(NotificationTemplateModel notification){
         return "";
    }

    public Queue<NotificationTemplateModel> getNotifications() {
        return notifications;
    }
}
