package repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import model.Cart;
import model.Product;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JCartRepository implements Repository<Cart> {

    private final String FILE_PATH = "src/main/resources/carts.json";
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private Map<Integer, Cart> cartMap = new HashMap<>();

    public JCartRepository() {
        loadData();
    }

    private void saveData() {
        try (Writer writer = new FileWriter(FILE_PATH)) {
            gson.toJson(cartMap, writer);
        } catch (IOException e) {
            throw new RuntimeException("CRITICAL: Failed to save cart data to file.", e);
        }
    }

    private void loadData() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            cartMap = new HashMap<>(); // Initialize empty if file doesn't exist
            return;
        }

        try (Reader reader = new FileReader(file)) {
            // "TypeToken" is the Gson way to handle Map<Integer, Cart>
            Type type = new TypeToken<Map<Integer, Cart>>(){}.getType();
            cartMap = gson.fromJson(reader, type);

            if (cartMap == null) {
                cartMap = new HashMap<>();
            }
        } catch (IOException e) {
            throw new RuntimeException("CRITICAL: Failed to load cart data from file.", e);
        }
    }

    @Override
    public Cart getById(int id) {
        return cartMap.get(id);
    }

    @Override
    public void updateItem(Cart cart) {
        cartMap.put(cart.getCartId(), cart);
        saveData();
    }

    @Override
    public void addItem(Cart cart) {
        cartMap.put(cart.getCartId(), cart);
        saveData();
    }

    @Override
    public void removeItem(Cart cart) {
        cartMap.remove(cart.getCartId());
        saveData();
    }

    @Override
    public List<Cart> getAll() {
        return new ArrayList<>(cartMap.values());
    }

    public boolean hasProduct(Product product) {
        return cartMap.containsKey(product.getProductId());
    }


}