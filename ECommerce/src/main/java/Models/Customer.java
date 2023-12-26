package Models;

public class Customer {
    private static int CustomersNumber;

    private int CustomerID;
    private String Name;
    private String Password;
    private String Email;
    private Double Balance;
    private Boolean Logged;

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

    public static int getCustomersNumber() {
        return CustomersNumber;
    }

    public static void setCustomersNumber(int customersNumber) {
        CustomersNumber = customersNumber;
    }

    public int getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(int customerID) {
        CustomerID = customerID;
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

    @Override
    public String toString() {
        return "Customer Data : ID -> " + " Name -> " + this.getName() + " Email -> " + this.getEmail() + " Balance -> " + getBalance();
    }
}
