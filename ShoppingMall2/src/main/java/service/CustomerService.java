package service;

import model.Cart;
import model.Product;
import repository.JCartRepository;


public class CustomerService {
    private final  JCartRepository cartRepo;


    public CustomerService(JCartRepository cartRepo) {
        this.cartRepo = cartRepo;
    }

    public Cart getShoppingCart(int currId){
        if (cartRepo.getById(currId) == null){
            Cart newCart = new Cart(currId);
            cartRepo.addItem(newCart);
            return newCart;        }
        else {
            return cartRepo.getById(currId);
        }

    }

    public void addToCart(int currId, Product product){
        Cart cart = getShoppingCart(currId);
        cart.addToItems(product);
        cartRepo.updateItem(cart);
    }

    public void removeFromCart(int currId, Product product){
        Cart cart = getShoppingCart(currId);
        cart.removeFromItems(product);
        cartRepo.updateItem(cart);
    }

    
}
