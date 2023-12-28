package EcommerceSystem.ECommerce.Controllers;

import EcommerceSystem.ECommerce.Models.*;
import EcommerceSystem.ECommerce.Services.*;
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
    IProductServices productServices = new ProductInMemoryServices();

    @PostMapping("")
    public Order createNewOrder(@RequestParam String email, @RequestParam(required = false) Boolean composite){
        Order order;
        Customer customer = customerServices.getCustomerIsLogged(email);
        if (customer == null){
            return null;
        }

        // get the old order he was unfinished it
        Order o = orderServices.getUnFinishedOrderForCustomer(customer);
        if (o != null){
            return o;
        }

        if (composite == null){
            composite = false;
        }

        if (composite){
            order = new CompoundOrder(customer);
        }else{
            order = new SingleOrder(customer);
        }
        orderServices.addOrder(order);
        return order;
    }

    @PostMapping("/checkout/")
    public Boolean checkout(@RequestParam String email){
        Customer customer = customerServices.getCustomerIsLogged(email);
        if (customer == null){
            return null;
        }
        Order order = orderServices.getUnFinishedOrderForCustomer(customer);
        order.finishOrder();
        return true;
    }

    @GetMapping("/getOrderForCustomer/")
    public String getOrder(@RequestParam String email){
        Customer customer = customerServices.getCustomer(email);
        if (customer == null){
            return null;
        }
        return orderServices.getUnFinishedOrderForCustomer(customer).toString();
    }

    @PostMapping("/addOrderToCompound")
    public Order addOrderToCompound(@RequestParam int compoundOrderID, @RequestParam int singleOrderID){
        try {
            CompoundOrder compoundOrder;
            Order singleOrder, order1;
            order1 = orderServices.getOrder(compoundOrderID);
            singleOrder = orderServices.getOrder(singleOrderID);

            if (order1 == null || singleOrder == null){
                return null;
            }

            // check that the order not in another compound order
            if (singleOrder.getParentOrder() != null){
                return null;
            }
            if (order1 instanceof CompoundOrder){
                compoundOrder = (CompoundOrder) order1;
                compoundOrder.addOrder(singleOrder);
                return compoundOrder;
            }else{
                return null;
            }
        }
        catch (Exception e){
            return null;
        }
    }
}