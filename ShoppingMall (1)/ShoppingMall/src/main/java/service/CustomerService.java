package service;
import model.*;
import repository.JCartRepository;
import repository.JCustomerRepository;


public class CustomerService {
    private final  JCartRepository cartRepo;
    private final  JCustomerRepository customerRepo;



    public CustomerService(JCartRepository cartRepo,  JCustomerRepository customerRepo) {
        this.cartRepo = cartRepo;
        this.customerRepo = customerRepo;
    }

    public Cart getShoppingCart(Customer customer) {
        int currId = customerRepo.getId(customer.getUsername());
        if (cartRepo.getById(currId) == null){


            Cart newCart = new Cart(currId);
            cartRepo.addItem(newCart);
            return newCart;        }
        else {
            return cartRepo.getById(currId);
        }

    }

    public void addToCart(Customer customer, Product product){
        int currId = customerRepo.getId(customer.getUsername());

        Cart cart = getShoppingCart(customer);
        cart.addToItems(product);
        cartRepo.updateItem(cart);
    }

    public void removeFromCart(Customer customer, Product product){
        int currId = customerRepo.getId(customer.getUsername());

        Cart cart = getShoppingCart(customer);
        cart.removeFromItems(product);
        cartRepo.updateItem(cart);
    }

    
}
