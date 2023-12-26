package Models;

public class OrderItem {
    private static int OrderItemsNumber = 0;
    private int ID;
    private int Quantity;
    private Product product;
    private Order order;


    public OrderItem(int quantity, Product product, Order order) {
        OrderItemsNumber++;
        this.ID = OrderItemsNumber;
        this.Quantity = quantity;
        this.product = product;
        this.order = order;
    }

    public static int getOrderItemsNumber() {
        return OrderItemsNumber;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public double totalPrice(){
        return Quantity * product.getPrice();
    }


}
