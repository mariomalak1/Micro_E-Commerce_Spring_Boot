package EcommerceSystem.ECommerce.Models;

public class OrderPlacementNotificationModel extends NotificationTemplateModel {
    public OrderPlacementNotificationModel(Order order) {
        super(order,"Dear {}, your booking of the product/s {} is confirmed, thanks for using our store :)");
    }

    @Override
    public void setSubject() {
        super.subject="Placement";
    }


    @Override
    public void setLangauges() {
        langauges.add("Arabic");
        langauges.add("English");
        langauges.add("Spanish");
    }
    @Override
    public void setPlaceHolders(Order order) {
        content = content.replaceFirst("\\{\\}", order.getCustomer().getName());
        String ProductsNames = "";
        for (int i = 0 ; i < order.getAllProductsInTheOrder().size();i++){
            ProductsNames+=order.getAllProductsInTheOrder().get(i).getName() +", ";
        }
        ProductsNames = ProductsNames.substring(0,ProductsNames.length()-2);
        content = content.replaceFirst("\\{\\}", ProductsNames);
    }

    @Override
    public NotificationTemplateModel clone() {
        return new OrderPlacementNotificationModel(order);
    }

}
