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
    IProductServices productServices = new ProductInMemoryServices();

    @PostMapping("")
    public Order createNewOrder(@RequestBody String email, @RequestParam(required = false) Boolean composite){
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

        if (composite){
            order = new CompoundOrder(customer);
        }else{
            order = new SingleOrder(customer);
        }
        orderServices.addOrder(order);
        return order;
    }

    @PostMapping("/checkout/")
    public Boolean checkout(@RequestBody String email){
        Customer customer = customerServices.getCustomerIsLogged(email);
        if (customer == null){
            return null;
        }
        Order order = orderServices.getUnFinishedOrderForCustomer(customer);
        order.finishOrder();
        return true;
    }

    @GetMapping("/getOrderForCustomer/")
    public Order getOrder(@RequestBody String email){
        Customer customer = customerServices.getCustomer(email);
        if (customer == null){
            return null;
        }
        return orderServices.getUnFinishedOrderForCustomer(customer);
    }

    @PostMapping("/addOrderToCompound")
    public Order addOrderToCompound(@RequestBody int compoundOrderID, @RequestBody int singleOrderID){
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
