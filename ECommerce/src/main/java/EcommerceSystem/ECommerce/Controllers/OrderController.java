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

    @Autowired
    NotificationSenderServices notificationSenderServices= new NotificationSenderServices();
    @Autowired
    QueueServices queueServices= new QueueServices();

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
    public String checkout(@RequestParam String email){
        Customer customer = customerServices.getCustomerIsLogged(email);
        if (customer == null){
            return "There is no customer with this id";
        }
        Order order = orderServices.getUnFinishedOrderForCustomer(customer);
        if (order == null){
            return "There is no order for this customer";
        }
        order.finishOrder();
        notificationSenderServices.SetOrder(order);
        return notificationSenderServices.notifyCustomer();
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

            if (order1 == null){
                return "No Compound Order Created With This ID.";
            }

            if (order1 instanceof CompoundOrder compoundOrder){
                Order singleOrder = null;
                compoundOrder = orderServices.addOrderNeedToConfirm(customer, compoundOrder.getOrderID());

                if (compoundOrder == null){
                    return "Error  While Adding Order.";
                }

                // add it to order list
                for (Order o : compoundOrder.getOrders()){
                    if (o.getCustomer() == customer){
                        singleOrder = o;
                    }
                }
                if (singleOrder == null){
                    singleOrder = new SingleOrder(customer);
                    compoundOrder.addOrder(singleOrder);
                    orderServices.addOrder(singleOrder);
                }
                return compoundOrder.toString();
            }else{
                return "This Order Not Compound Order.";
            }
        }
        catch (Exception e){
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
            return "There is no order with that id";
        }

        if(!order.GetStatus()){
            if (order instanceof CompoundOrder compoundOrder){
                System.out.println(compoundOrder.getOrders().size());
                for (Order o : compoundOrder.getOrders()){
                    System.out.println(o.getCustomer().getEmail());
                    // to delete it from need confirm orders
                    queueServices.DelMessages(o);
                    order.getCustomer().setBalance(order.getCustomer().getBalance()+order.getTotalPrice());
                    for (int i = 0 ; i < o.getAllProductsInTheOrder().size();i++){
                        productServices.IncreaseProductQuantity(o.getOrderItemList().get(i).getProduct().getSerialNumber(),o.getOrderItemList().get(i).getQuantity());
                    }
                    orderServices.confirmOrderByCustomer(o.getCustomer(), compoundOrder.getOrderID());
                    // delete all single orders that in this
                    orderServices.deleteOrder(o);
                }
            }
            for (int i = 0 ; i < order.getAllProductsInTheOrder().size();i++){
                productServices.IncreaseProductQuantity(order.getOrderItemList().get(i).getProduct().getSerialNumber(),order.getOrderItemList().get(i).getQuantity());
            }
            order.getCustomer().setBalance(order.getCustomer().getBalance()+order.getTotalPrice());
            order = orderServices.deleteOrder(order);
            queueServices.DelMessages(order);
            return "Order Deleted Successfully.";
        }
        else{
            return "This order is shipped, it can't be canceled";
        }
    }
}
