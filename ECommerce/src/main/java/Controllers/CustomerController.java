package Controllers;

import Models.Customer;

import org.springframework.http.ResponseEntity;
import Services.CustomerInMemoryServices;
import Services.ICustomerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    @Autowired
    ICustomerServices customerServices = new CustomerInMemoryServices();

    @PostMapping("")
    public ResponseEntity<Customer> createNewCustomer(@RequestBody String name, @RequestBody String password, @RequestBody String email, @RequestBody(required = false) double balance ){
        Customer customer = new Customer(name, password, email, balance);
        customerServices.addCustomer(customer);
        return ResponseEntity.ok(customer);
    }

    @GetMapping("/login/")
    public Customer logIn(@RequestBody String email, @RequestBody String password){
        Customer customer = customerServices.getCustomer(email);
        if (customer != null){
            if (customer.getPassword().equals(password)){
                return customer;
            }else{
                return null;
            }
        }else{
            return null;
        }
    }

    @GetMapping("/getBalance/")
    public Double getBalance(@RequestBody String email) {
        Customer customer = customerServices.getCustomer(email);
        if (customer != null){
            if (customer.isLogged()){
                return customer.getBalance();
            }else{
                return 0.0;
            }
        }else{
            return 0.0;
        }
    }

    @PostMapping("/putBalance/")
    public Double setBalance(@RequestParam double balance, @RequestBody String email){
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
}
