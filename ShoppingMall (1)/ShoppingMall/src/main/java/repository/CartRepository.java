package repository;

import model.Product;
import model.Cart;

import java.util.ArrayList;
import java.util.List;


// in-memory implementation of cart repository
public class CartRepository implements Repository<Cart> {
    private  List<Cart> carts =  new ArrayList<>();

    public CartRepository(){
        this.carts = new ArrayList<>();
    }

    public void removeItem(Cart cart) {
        carts.remove(cart);
    }

    public void addItem(Cart cart) {
        carts.add(cart);
    }

    public void updateItem(Cart cart) {
        for (int i = 0; i < carts.size(); i++) {
            if (carts.get(i).getCartId() == cart.getCartId()) {
                carts.set(i, cart);
                return;
            }
        }

    }

    public List<Cart> getAll() {
        return carts;
    }

    public Cart getById(int id) {
        for (Cart cart : carts) {
            if (cart.getCartId() == id) {
                return cart;
            }
        }
        return null;
    }

    public boolean hasCart(Cart cart) {
        return carts.contains(cart);
    }
}
