package Controllers;

import Models.Product;
import Services.IProductServices;
import Services.ProductInMemoryServices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/product")
public class ProductController {

    @Autowired
    IProductServices productServices = new ProductInMemoryServices();

    @PostMapping("")
    public Product addProduct(@RequestBody Product product){
        return productServices.addProduct(product);
    }

    @DeleteMapping("delete/{serial}")
    public Boolean deleteProduct(@PathVariable String serial){
        Product p = productServices.getProduct(serial);
        if (p == null){
            return false;
        }
        productServices.deleteProduct(p);
        return true;
    }

    @GetMapping("getProduct/{serial}")
    public Product getProduct(@PathVariable String serial){
        return productServices.getProduct(serial);
    }

    @GetMapping("getAllProduct/")
    public List<Product> getAllProducts(){
        return productServices.getAllProducts();
    }
}
