package EcommerceSystem.ECommerce.Controllers;

import EcommerceSystem.ECommerce.Models.NotificationTemplateModel;
import EcommerceSystem.ECommerce.Services.QueueServices;

public class QueueController {
    
    private QueueServices queueServices;

    public String displayNotifications(){
        return queueServices.displayNotification();
    }

    public String addNotification(NotificationTemplateModel notificationTemplateModel){
        return "";
    }
}
