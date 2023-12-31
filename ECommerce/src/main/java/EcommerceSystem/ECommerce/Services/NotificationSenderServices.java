package EcommerceSystem.ECommerce.Services;

import EcommerceSystem.ECommerce.Models.*;
import org.springframework.stereotype.Service;

@Service
public class NotificationSenderServices {
    private NotifierServices notifierServices ;
    private NotificationTemplateModel notificationTemplateModel ;
    private Order order;

    public NotificationSenderServices (){}
    public void SetOrder(Order order){
        this.order = order;
    }
    public String notifyCustomer(){
        notifierServices = new EmailServices();
        if(order.getCustomer().GetChannels().get(0).equals(true)){
            notifierServices= new SmsDecorator(notifierServices);
        }
        notificationTemplateModel = new OrderShippmentNotificationModel(order);
        notifierServices.sendNotification(notificationTemplateModel);

        notificationTemplateModel = new OrderPlacementNotificationModel(order);
        return notificationTemplateModel.getContent();
    }
}
