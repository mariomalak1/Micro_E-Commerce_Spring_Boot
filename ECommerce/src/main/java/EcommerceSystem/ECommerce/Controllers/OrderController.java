package EcommerceSystem.ECommerce.Controllers;

import EcommerceSystem.ECommerce.Models.*;
import EcommerceSystem.ECommerce.Services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    public String checkout(@RequestParam String email){
        Customer customer = customerServices.getCustomerIsLogged(email);
        if (customer == null){
            return "please login first.";
        }
        Order order = orderServices.getUnFinishedOrderForCustomer(customer);
        if (order == null){
            return "No order created by this customer.";
        }
        if (order instanceof CompoundOrder compoundOrder){
            List<Customer> customerList = compoundOrder.getConfirmedCustomers();
            if (customerList.size() < compoundOrder.getOrders().size()){
                return "still some of your friends not confirm on the order.";
            }
        }
        if (order.finishOrder()){
            return "order finished";
        }
        return "can't  finish this order.";
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
        List<CompoundOrder> compoundOrderList = orderServices.getAllOrdersNeededToConfirmForCustomer(customer);
        if (compoundOrderList == null){
            return new ArrayList<>().toString();
        }
        return compoundOrderList.toString();
    }

    @PostMapping("/ConfirmOrder/")
    public String confirmOrderByCustomer(@RequestParam String email, @RequestParam int orderID){
        Customer customer = customerServices.getCustomerIsLogged(email);
        if (customer == null){
            return null;
        }

        Order order = orderServices.getOrder(orderID);
        if (order == null){
            return "No order with this id.";
        }
        if (order instanceof CompoundOrder compoundOrder){
            compoundOrder = orderServices.confirmOrderByCustomer(customer, orderID);
            if (compoundOrder == null){
                return "No order with this id for this customer to confirm it.";
            }
            compoundOrder.customerConfirm(customer);
            return compoundOrder.toString();
        }else{
            return "It's not compound order.";
        }
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
