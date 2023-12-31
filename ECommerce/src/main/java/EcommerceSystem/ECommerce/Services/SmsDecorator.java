package EcommerceSystem.ECommerce.Services;

import EcommerceSystem.ECommerce.Models.NotificationTemplateModel;
public class SmsDecorator extends BaseDecoratorServices{
    public  SmsDecorator(NotifierServices notifierServices){super(notifierServices);}
    @Override
    public void sendNotification(NotificationTemplateModel notificationTemplateModel) {
        NotificationTemplateModel smsTemplateCopy = notificationTemplateModel.clone();
        notifier.sendNotification(notificationTemplateModel);
        smsTemplateCopy.AddToContent("From SMS");
        queueServices.addNotification(smsTemplateCopy);
    }
}
