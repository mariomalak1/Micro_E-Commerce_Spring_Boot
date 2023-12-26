package Models;

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

    private List<Order>orders;
    private List<Order>NeedConfirmOrders;

    public static int getCustomersNumber() {
        return CustomersNumber;
    }

    public Customer(String name, String password, String email, Double balance) {
        Name = name;
        Password = password;
        Email = email;
        this.Logged = true;

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

    public Boolean isLogged(){
        return Logged;
    }

    public void logout(){
        Logged = false;
    }

    public void login(){
        Logged = true;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public void addOrder(Order order){
        orders.add(order);
    }

    public void deleteOrder(Order order){
        orders.remove(order);
    }

    @Override
    public String toString() {
        return "Customer Data : ID -> " + " Name -> " + this.getName() + " Email -> " + this.getEmail() + " Balance -> " + getBalance();
    }
}
