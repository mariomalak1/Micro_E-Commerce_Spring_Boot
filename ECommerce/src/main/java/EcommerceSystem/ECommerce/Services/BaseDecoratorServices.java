package EcommerceSystem.ECommerce.Services;

import EcommerceSystem.ECommerce.Models.NotificationTemplateModel;
public abstract class BaseDecoratorServices extends NotifierServices{
    protected NotifierServices notifier;
    public BaseDecoratorServices(NotifierServices notifierServices){
        this.notifier = notifierServices;
    }

}
