package Controllers;

import Models.*;
import Services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/orders/")
public class OrderController {
    @Autowired
    ICustomerServices customerServices = new CustomerInMemoryServices();

    @Autowired
    IOrderServices orderServices = new OrderInMemoryServices();

    @Autowired
    IOrderItemServices orderItemServices = new OrderItemInMemoryServices();

    @Autowired
    IProductServices productServices = new ProductInMemoryServices();


    @PostMapping("")
    public Order addOrder(@RequestBody String email, @RequestParam(required = false) Boolean composite){
        Order order;
        Customer customer = customerServices.getCustomerIsLogged(email);
        if (customer == null){
            return null;
        }
        else{
            if (composite){
                order = new CompoundOrder(customer);
            }else{
                order = new SingleOrder(customer);
            }
            return order;
        }
    }

    @PostMapping("addProduct/")
    public Product addProductInOrder(@RequestBody String email, @RequestParam String productSerialNumber, @RequestParam Integer quantity){
        Customer customer = customerServices.getCustomerIsLogged(email);
        if (customer == null){
            return null;
        }
        else{
            Product product = productServices.getProduct(productSerialNumber);

        }
    }
}
