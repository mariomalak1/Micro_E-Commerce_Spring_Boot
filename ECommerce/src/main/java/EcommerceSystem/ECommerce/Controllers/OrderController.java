package EcommerceSystem.ECommerce.Controllers;

import EcommerceSystem.ECommerce.Models.*;
import EcommerceSystem.ECommerce.Services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public String createNewOrder(@RequestParam String email, @RequestParam(required = false) Boolean composite){
        Order order;
        Customer customer = customerServices.getCustomerIsLogged(email);
        if (customer == null){
            return null;
        }

        // get the old order he was unfinished it
        Order o = orderServices.getUnFinishedOrderForCustomer(customer);
        if (o != null){
            return o.toString();
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
        return order.toString();
    }

    @PostMapping("/checkout/")
    public Boolean checkout(@RequestParam String email){
        Customer customer = customerServices.getCustomerIsLogged(email);
        if (customer == null){
            return null;
        }
        Order order = orderServices.getUnFinishedOrderForCustomer(customer);
        if (order == null){
            return false;
        }
        return order.finishOrder();
    }

    @GetMapping("/getOrderForCustomer/")
    public String getOrder(@RequestParam String email){
        Customer customer = customerServices.getCustomer(email);
        if (customer == null){
            return null;
        }
        Order order = orderServices.getUnFinishedOrderForCustomer(customer);
        if (order == null){
            return null;
        }
        return order.toString();
    }

    @PostMapping("/addOrderToCompound")
    public String addOrderToCompound(@RequestParam int compoundOrderID, @RequestParam String emailOfFriend){
        try {
            Customer customer = customerServices.getCustomer(emailOfFriend);
            if (customer == null){
                return null;
            }

            Order order1;
            order1 = orderServices.getOrder(compoundOrderID);

            SingleOrder singleOrder = new SingleOrder(customer);

            if (order1 == null){
                return null;
            }

            if (order1 instanceof CompoundOrder compoundOrder){
                compoundOrder.addOrder(singleOrder);
                compoundOrder = orderServices.addOrderNeedToConfirm(compoundOrder.getCustomer(), compoundOrderID);
                if (compoundOrder == null){
                    return null;
                }
                orderServices.addOrderNeedToConfirm(customer, compoundOrder.getOrderID());
                return compoundOrder.toString();
            }else{
                return null;
            }
        }
        catch (Exception e){
            return null;
        }
    }

    @GetMapping("/getAllOrderNeededToConfirmByCustomer/")
    public String getAllOrdersNeededToConfirmToCustomer(@RequestParam String email){
        Customer customer = customerServices.getCustomerIsLogged(email);
        if (customer == null){
            return null;
        }
        return orderServices.getAllOrdersNeededToConfirmForCustomer(customer).toString();
    }

    @PostMapping("ConfirmOrder")
    public String confirmOrderByCustomer(@RequestParam String email, @RequestParam int orderID){
        Customer customer = customerServices.getCustomerIsLogged(email);
        if (customer == null){
            return null;
        }
        return orderServices.confirmOrderByCustomer(customer, orderID).toString();
    }
}
