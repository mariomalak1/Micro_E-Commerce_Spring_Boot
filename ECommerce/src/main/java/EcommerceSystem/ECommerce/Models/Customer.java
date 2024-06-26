package EcommerceSystem.ECommerce.Models;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private static int CustomersNumber;

    private int CustomerID;
    private String Name;
    private String Password;
    private String Email;
    private Double Balance;
    private Boolean Logged;
    private String Address;
    private ArrayList<Boolean> Channels;
    // list to hold all compound orders that need to confirm from the customer, if it confirmed will deleted from this list and add to the confirmed customers in compound order, and will added to orders

    public static int getCustomersNumber() {
        return CustomersNumber;
    }

    public Customer(String name, String password, String email, Double balance, String address , ArrayList<Boolean> channels) {
        Address = address;
        Name = name;
        Password = password;
        Email = email;
        this.Logged = true;
        this.Channels = channels;
        // assign the balance by a default value
        if (balance == 0){
            Balance = 1000.0;
        }else{
            Balance = balance;
        }

        // id auto increment
        CustomersNumber++;
        CustomerID = CustomersNumber;
    }

    public int getCustomerID() {
        return CustomerID;
    }

    public Boolean isLogged(){
        return Logged;
    }

    public void logout(){
        Logged = false;
    }

    public void login(){
        Logged = true;
    }


    @Override
    public String toString() {
        return "Customer Data : ID -> " + " Name -> " + this.getName() + " Email -> " + this.getEmail() + " Balance -> " + getBalance() + " Address -> " + getAddress();
    }

    // getters setters

    public void setCustomerID(int customerID) {
        CustomerID = customerID;
    }

    public String getAddress(){
        return Address;
    }

    public void setAddress(String address){
        Address = address;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public Double getBalance() {
        return Balance;
    }

    public void setBalance(Double balance) {
        Balance = balance;
    }
    public ArrayList<Boolean> GetChannels(){
        return Channels;
    }
}
