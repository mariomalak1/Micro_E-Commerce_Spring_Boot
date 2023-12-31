package EcommerceSystem.ECommerce.Services;

import EcommerceSystem.ECommerce.Models.Product;

import java.util.List;

public interface IProductServices {
    Product addProduct(Product o);
    Product deleteProduct(Product o);
    Product getProduct (String serial);
    List<Product> getAllProducts();
    void IncreaseProductQuantity(String productSerial , int Quantity);
}
