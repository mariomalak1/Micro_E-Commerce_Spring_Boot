package Services;

import Models.Product;

import java.util.List;

public interface IProductServices {
    Product addProduct(Product o);
    Boolean deleteProduct(Product o);
    Product getProduct (int id);
    List<Product> getAllProducts();
}
