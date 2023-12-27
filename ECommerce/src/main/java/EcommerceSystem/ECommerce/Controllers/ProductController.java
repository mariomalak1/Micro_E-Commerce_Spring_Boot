package EcommerceSystem.ECommerce.Controllers;

import EcommerceSystem.ECommerce.Models.Product;
import EcommerceSystem.ECommerce.Services.*;

import java.util.List;

import com.fasterxml.jackson.databind.node.BinaryNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/product/")
public class ProductController {

    @Autowired
    IProductServices productServices = new ProductInMemoryServices();

    @PostMapping("")
    Product addProduct(@RequestParam String serialNumber, @RequestParam String name, @RequestParam String vendor, @RequestParam String category, @RequestParam double price, @RequestParam int availableNumber){
        Product product = new Product(serialNumber, name, vendor, category, price, availableNumber);
        System.out.println("Product after add : " + product);
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
        List<Product> products = productServices.getAllProducts();
        System.out.println(products);
        return products;
    }
}
