package repository;

import model.Product;

import java.util.ArrayList;
import java.util.List;


// in-memory implementation of product repository
public class ProductRepository implements Repository<Product> {
    private  List<Product> products =  new ArrayList<>();

    public ProductRepository(){
        this.products = new ArrayList<>();
    }

    public void removeItem(Product product) {
        products.remove(product);
    }

    public void addItem(Product product) {
        products.add(product);
    }

    public void updateItem(Product product) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getProductId() == product.getProductId()) {
                products.set(i, product);
                return;
            }
        }

    }

    public List<Product> getAll() {
        return products;
    }

    public Product getById(int id) {
        for (Product product : products) {
            if (product.getProductId() == id) {
                return product;
            }
        }
        return null;
    }

    public boolean hasProduct(Product product) {
        return products.contains(product);
    }
}
