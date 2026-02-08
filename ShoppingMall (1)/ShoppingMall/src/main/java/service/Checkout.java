package service;

import repository.*;
import model.*;

public class Checkout {
    private final JProductRepository productRepo;
    private final JCartRepository cartRepo;

    public Checkout(JProductRepository productRepo, JCartRepository cartRepo) {
        this.productRepo = productRepo;
        this.cartRepo = cartRepo;
    }

    public void checkoutCustomerOrder(int customerId) {
        Cart customerCart = cartRepo.getById(customerId);
        if (customerCart == null || customerCart.getCartItems().isEmpty()) {
            throw new IllegalStateException("no items to checkout.");
        }

        for (Product item : customerCart.getCartItems()) {
            item.decreaseStock(1);
            productRepo.updateItem(item);
        }

        cartRepo.removeItem(customerCart);
    }

}
