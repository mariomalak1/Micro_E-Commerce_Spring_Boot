package EcommerceSystem.ECommerce.Controllers;

import EcommerceSystem.ECommerce.Models.Customer;

import EcommerceSystem.ECommerce.Models.Order;
import org.springframework.http.ResponseEntity;
import EcommerceSystem.ECommerce.Services.CustomerInMemoryServices;
import EcommerceSystem.ECommerce.Services.ICustomerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    @Autowired
    ICustomerServices customerServices = new CustomerInMemoryServices();

    @PostMapping("")
    public String createNewCustomer(@RequestParam String name, @RequestParam String password, @RequestParam String email, @RequestParam String address, @RequestParam(required = false) double balance ){
        Customer customer = new Customer(name, password, email, balance, address);
        customer = customerServices.addCustomer(customer);
        if (customer == null){
            return null;
        }
        return customer.toString();
    }

    @GetMapping("/login/")
    public String logIn(@RequestParam String email, @RequestParam String password){
        Customer customer = customerServices.getCustomer(email);
        if (customer != null){
            if (customer.getPassword().equals(password)){
                return customer.toString();
            }else{
                return null;
            }
        }else{
            return null;
        }
    }

    @GetMapping("/getCustomer/")
    public String getCustomer(@RequestParam String email){
        Customer customer = customerServices.getCustomer(email);
        if (customer != null) {
            return customer.toString();
        }
        return "No Customer with this id.";
    }

    @GetMapping("/getBalance/")
    public Double getBalance(@RequestParam String email) {
        Customer customer = customerServices.getCustomerIsLogged(email);
        if (customer != null){
            return customer.getBalance();
        }else{
            return null;
        }
    }

    @PostMapping("/putBalance/")
    public Double setBalance(@RequestParam double balance, @RequestParam String email){
        Customer customer = customerServices.getCustomer(email);
        if (customer != null){
            if (customer.isLogged()){
                customer.setBalance(balance);
                return customer.getBalance();
            }else{
                return 0.0;
            }
        }else{
            return 0.0;
        }
    }

    @GetMapping("/getAllCustomers/")
    public List<Customer> getAllCustomer(){
        return customerServices.getAllCustomers();
    }

    @PostMapping("/addBalance/")
    public Double addBalance(@RequestParam String email, @RequestParam Double balance){
        if (balance < 0 ){
            return null;
        }
        Customer customer = customerServices.getCustomer(email);
        customer.setBalance(customer.getBalance() + balance);
        return customer.getBalance();
    }
}
