package service;

import model.Product;
import repository.ProductRepository;

// implementation of admin services
// changing the product image??
public class AdminService {
    private final ProductRepository productRepo;

    public AdminService(ProductRepository productRepo) {
        this.productRepo = productRepo;
    }

    //admin adds to the product
    public void add(Product product){
        if (! productRepo.hasProduct(product)){
            productRepo.addItem(product);
        }
    }

    //admin edits the product
    public void editPrice(int id, double price){
        Product product = productRepo.getById(id);
        if (product != null){
            product.setPrice(price);
            productRepo.updateItem(product);
        }
        else {
            throw new IllegalArgumentException("Product with id " + id + " does not exist");
        }

    }

    public void editStockQuantity(int id, int quantity){
        Product product = productRepo.getById(id);
        if (product != null){
            product.setStockQuantity(quantity);
            productRepo.updateItem(product);
        }
        else {
            throw new IllegalArgumentException("Product with id " + id + " does not exist");
        }
    }

    //admin deletes the product
    public void delete(int id){
        Product product = productRepo.getById(id);
        if (product != null){
            productRepo.removeItem(product);
        }
        else {
            throw new IllegalArgumentException("Product with id " + id + " does not exist");
        }
    }
}
