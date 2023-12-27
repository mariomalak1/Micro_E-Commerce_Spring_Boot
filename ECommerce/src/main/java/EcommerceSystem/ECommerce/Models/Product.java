package EcommerceSystem.ECommerce.Models;

public class Product {
    String SerialNumber;
    String Name;
    String Vendor;
    String Category;
    Double Price;
    Integer AvailableNumber;

    public Product(String serialNumber, String name, String vendor, String category, Double price, Integer availableNumber) {
        SerialNumber = serialNumber;
        Name = name;
        Vendor = vendor;
        Category = category;
        Price = price;
        AvailableNumber = availableNumber;
    }


    @Override
    public String toString() {
        return "Product Data : " + "Serial Number -> " + getSerialNumber() + " Name -> " + getName() + " Vendor -> " + getVendor() + " Category -> " + getCategory() + " Price -> " + getPrice() + " Available Number -> " + getAvailableNumber();
    }

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
}
