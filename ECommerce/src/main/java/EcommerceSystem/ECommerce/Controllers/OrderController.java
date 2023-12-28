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
                return "No Customer With This Email.";
            }

            Order order1;
            order1 = orderServices.getOrder(compoundOrderID);

            SingleOrder singleOrder = new SingleOrder(customer);

            if (order1 == null){
                return "No Compound Order Created With This ID.";
            }

            if (order1 instanceof CompoundOrder compoundOrder){
                compoundOrder.addOrder(singleOrder);
                compoundOrder = orderServices.addOrderNeedToConfirm(customer, compoundOrder.getOrderID());
                if (compoundOrder == null){
                    return "Error  While Adding Order.";
                }
                orderServices.addOrder(singleOrder);
                return compoundOrder.toString();
            }else{
                return "This Order Not Compound Order.";
            }
        }
        catch (Exception e){
            System.out.println("from catch.");
            return "Error While Adding To Compound Order.";
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

    @PostMapping("/ConfirmOrder/")
    public String confirmOrderByCustomer(@RequestParam String email, @RequestParam int orderID){
        Customer customer = customerServices.getCustomerIsLogged(email);
        if (customer == null){
            return null;
        }
        return orderServices.confirmOrderByCustomer(customer, orderID).toString();
    }

    @DeleteMapping("/delete/")
    public String deleteOrder(@RequestParam int orderID){
        Order order = orderServices.getOrder(orderID);
        if (order == null){
            return "No order with this id.";
        }
        if (order.isFinished()){
            return "This order is finished can't delete it.";
        }
        order = orderServices.deleteOrder(order);
        if (order == null){
            return "Can't delete this order.";
        }
        if (order instanceof CompoundOrder compoundOrder){
            for (Order o : compoundOrder.getOrders()){
                // to delete it from need confirm orders
                orderServices.confirmOrderByCustomer(o.getCustomer(), compoundOrder.getOrderID());
                // delete all single orders that in this
                orderServices.deleteOrder(o);
            }
            return "Order Deleted Successfully With all of it's orders.";
        }else{
            return "Order Deleted Successfully.";
        }
    }
}
