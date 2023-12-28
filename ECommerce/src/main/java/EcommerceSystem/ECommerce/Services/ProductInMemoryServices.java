package EcommerceSystem.ECommerce.Services;

import EcommerceSystem.ECommerce.Models.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class ProductInMemoryServices implements IProductServices{

    @Override
    public Product addProduct(Product p) {
        // check that the customer is newly added
        Product product = DataBaseInMemory.productList.get(p.getSerialNumber());
        if (product == null){
            return DataBaseInMemory.productList.put(p.getSerialNumber(), p);
        }
        return product;
    }

    @Override
    public Product deleteProduct(Product o) {
        return DataBaseInMemory.productList.remove(o.getSerialNumber());
    }

    @Override
    public Product getProduct(String serial) {
        return DataBaseInMemory.productList.get(serial);
    }

    @Override
    public List<Product> getAllProducts() {
        Set<String> productsSerialNumber = DataBaseInMemory.productList.keySet();
        List<Product> productList = new ArrayList<>();
        for (String serial: productsSerialNumber){
            productList.add(DataBaseInMemory.productList.get(serial));
        }
        return productList;
    }
}
