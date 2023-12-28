package EcommerceSystem.ECommerce.Models;

public class OrderPlacementNotificationModel extends NotificationTemplateModel {
    public OrderPlacementNotificationModel(Customer customerAccount, Product[] product) {
        super(customerAccount, product);
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

    @Override
    public void setSubject(String subject) {
        super.setSubject(subject);
    }
}
