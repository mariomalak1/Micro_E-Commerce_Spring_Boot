package Controllers;

import Models.Customer;
import Models.OrderItem;
import Models.Product;
import Services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/orderItems/")
public class OrderItemController {
    @Autowired
    IOrderItemServices orderItemServices = new OrderItemInMemoryServices();

    @Autowired
    ICustomerServices customerServices = new CustomerInMemoryServices();

    @Autowired
    IOrderServices orderServices = new OrderInMemoryServices();

    @Autowired
    IProductServices productServices = new ProductInMemoryServices();

    @PostMapping("addProduct/")
    public OrderItem addItemInOrder(@RequestBody String email, @RequestParam String productSerialNumber, @RequestParam Integer quantity){
        Customer customer = customerServices.getCustomerIsLogged(email);
        if (customer == null){
            return null;
        }
        Product product = productServices.getProduct(productSerialNumber);
        if (product == null){
            return null;
        }

//        OrderItem orderItem = new OrderItem();

        return null;
    }
}
