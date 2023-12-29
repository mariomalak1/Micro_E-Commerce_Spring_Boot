package EcommerceSystem.ECommerce.Controllers;

import EcommerceSystem.ECommerce.Models.*;
import EcommerceSystem.ECommerce.Services.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;


@RestController
@RequestMapping("/api/orderItems/")
public class OrderItemController {

    @Autowired
    ICustomerServices customerServices = new CustomerInMemoryServices();

    @Autowired
    IOrderServices orderServices = new OrderInMemoryServices();

    @Autowired
    IProductServices productServices = new ProductInMemoryServices();

    @Autowired
    IOrderItemServices orderItemServices = new OrderItemInMemoryServices();

    @PostMapping("")
    public String addItemInOrder(@RequestParam String email, @RequestParam String productSerialNumber, @RequestParam int orderID, @RequestParam Integer quantity){

        // check if the customer is found and is logged
        Customer customer = customerServices.getCustomerIsLogged(email);
        if (customer == null){
            return "No Customer With This ID.";
        }

        // check that product is found
        Product product = productServices.getProduct(productSerialNumber);
        if (product == null){
            return "No Product With This ID.";
        }

        // check that order id his provide it is found
        Order order = orderServices.getOrder(orderID);
        if (order == null){
            return "No Order with this id.";
        }
        if (order.getCustomer() != customer){
            if (order.getParentOrder() != null){
                if (order.getParentOrder().getCustomer() != customer){
                    return "The Order Owner is not email provided.";
                }
            }
            else{
                return "This not email of the customer owner of order.";
            }
        }

        // check that the order not finished
        if (order.isFinished()){
            return "This  is finished order can't add to it.";
        }

        // check that quantity he needs is less than or equal the available number of product
        if (quantity > product.getAvailableNumber()){
            return "Sorry, We Have Less Quantity than you need in this product.";
        }

        OrderItem orderItem = new OrderItem(quantity, product, order);

        if (customer.getBalance() < orderItem.getTotalPrice()){
            return "Sorry, your balance is not enough.";
        }

        order.addOrderItem(orderItem);
        // minus the available number of this product
        int availableNumberOfProduct = product.getAvailableNumber() - quantity;
        product.setAvailableNumber(availableNumberOfProduct);
        orderItem = orderItemServices.AddOrderItem(orderItem, order);
        if (orderItem == null){
            return "Error While Create Add Your Item.";
        }
        return orderItem.toString();
    }

    @DeleteMapping("delete/{id}")
    public String deleteItemInOrder(@PathVariable int id){
        OrderItem orderItem = orderItemServices.getOrderItem(id);
        if (orderItem == null){
            return "No Order Item with this ID.";
        }
        if (orderItem.getOrder().isFinished()){
            return "This is finished order can't delete items from it.";
        }
        orderItem.getOrder().removeOrderItem(orderItem);
        orderItem = orderItemServices.removeOrderItem(orderItem);
        if (orderItem == null){
            return "can't delete this item.";
        }
        return "Order item delete successfully.";
    }
}
