package EcommerceSystem.ECommerce.Models;

public class OrderShippmentNotificationModel extends NotificationTemplateModel{
    public OrderShippmentNotificationModel(Customer customerAccount, Product[] product) {
        super(customerAccount, product);
    }

    @Override
    public void setSubject(String subject) {
        super.setSubject(subject);
    }

    @Override
    public void setContent(String content) {
        super.setContent(content);
    }

    @Override
    public void setLangauges(String[] langauges) {
        super.setLangauges(langauges);
    }

    @Override
    public void setPlaceHolders(String[] placeHolders) {
        super.setPlaceHolders(placeHolders);
    }
}
