package Models;

public class Product {
    String SerialNumber;
    String Name;
    String Vendor;
    String Category;
    Double Price;
    Integer AvailableNumber;

    
    public String getSerialNumber() {
        return SerialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        SerialNumber = serialNumber;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getVendor() {
        return Vendor;
    }

    public void setVendor(String vendor) {
        Vendor = vendor;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public Double getPrice() {
        return Price;
    }

    public void setPrice(Double price) {
        Price = price;
    }

    public Integer getAvailableNumber() {
        return AvailableNumber;
    }

    public void setAvailableNumber(Integer availableNumber) {
        AvailableNumber = availableNumber;
    }

    public Product(String serialNumber, String name, String vendor, String category, Double price, Integer availableNumber) {
        SerialNumber = serialNumber;
        Name = name;
        Vendor = vendor;
        Category = category;
        Price = price;
        AvailableNumber = availableNumber;
    }
}
