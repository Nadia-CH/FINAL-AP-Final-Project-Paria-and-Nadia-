package model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private final int id;
    private List<Product> cartItems;

    public Cart(int id) {
        this.id = id;
        this.cartItems = new ArrayList<>();
    }

    public void addToItems(Product product) {
        cartItems.add(product);
    }

    public void removeFromItems(Product product) {
        cartItems.remove(product);
    }

    public int getItemsTotalPrice() {
        int totalPrice = 0;
        for (Product product : cartItems) {
            totalPrice += product.getPrice();
        }
        return totalPrice;
    }

    public int getCartId() {
        return id;
    }
    public List<Product> getCartItems() {
        return cartItems;
    }
}
