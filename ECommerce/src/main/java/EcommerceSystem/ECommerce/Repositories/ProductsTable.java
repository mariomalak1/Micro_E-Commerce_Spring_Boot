package EcommerceSystem.ECommerce.Repositories;

import EcommerceSystem.ECommerce.Models.Product;

public class ProductsTable {
    private Product products[];

    public Product[] getProducts() {
        return products;
    }

    public void setProducts(Product[] products) {
        this.products = products;
    }
}
