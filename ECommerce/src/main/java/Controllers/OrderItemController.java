package Controllers;

import Models.Customer;
import Models.Order;
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

    @PostMapping("")
    public OrderItem addItemInOrder(@RequestBody String email, @RequestParam String productSerialNumber, @RequestParam int orderID, @RequestParam Integer quantity){

        // check if the customer is found and is logged
        Customer customer = customerServices.getCustomerIsLogged(email);
        if (customer == null){
            return null;
        }

        // check that product is found
        Product product = productServices.getProduct(productSerialNumber);
        if (product == null){
            return null;
        }

        // check that order id his provide it is found
        Order order = orderServices.getOrder(orderID);
        if (order == null){
            return null;
        }

        // check that the order not finished
        if (order.isFinished()){
            return null;
        }

        // check that quantity he needs is less than or equal the available number of product
        if (quantity > product.getAvailableNumber()){
            return null;
        }

        // minus the available number of this product
        OrderItem orderItem = new OrderItem(quantity, product, order);
        int availableNumberOfProduct = product.getAvailableNumber() - quantity;
        product.setAvailableNumber(availableNumberOfProduct);
        orderItemServices.AddOrderItem(orderItem, order);

        return orderItem;
    }
}
