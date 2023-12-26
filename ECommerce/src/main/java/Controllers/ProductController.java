package Controllers;

import Models.Product;
import Services.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("api/product/")
public class ProductController {

    public ProductController(){
        System.out.println("from constructor");
    }

    @Autowired
    IProductServices productServices = new ProductInMemoryServices();

    @PostMapping("")
    public Product addProduct(){
        System.out.println("marioo");
        Product product = new Product("serial", "name", "vendor", "category", 12.0, 8);
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
