package service;

import repository.*;
import model.*;

public class Checkout {
    private final ProductRepository productRepo;
    private final CartRepository cartRepo;

    public Checkout(ProductRepository productRepo, CartRepository cartRepo) {
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
