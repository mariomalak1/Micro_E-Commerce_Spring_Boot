package Controllers;

import Models.Product;
import Services.IProductServices;
import Services.ProductInMemoryServices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("api/product/")
public class ProductController {

    @Autowired
    IProductServices productServices = new ProductInMemoryServices();

    @PostMapping("")
    Product addProduct(@RequestBody Product product){
        return productServices.addProduct(product);
    }

    @DeleteMapping("delete/<serial>")
    Boolean deleteProduct(@RequestParam String serial){
        Product p = productServices.getProduct(serial);
        if (p == null){
            return false;
        }
        productServices.deleteProduct(p);
        return true;
    }

    @GetMapping("getProduct/<serial>")
    Product getProduct(@RequestParam String serial){
        return productServices.getProduct(serial);
    }

    @GetMapping("getAllProduct/")
    List<Product> getAllProducts(){
        return productServices.getAllProducts();
    }
}
