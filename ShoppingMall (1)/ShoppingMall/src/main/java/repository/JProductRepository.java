package repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import model.Product;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JProductRepository implements Repository<Product> {
    private Map<Integer, Product> productMap;
    private final String FILE_PATH = "src/main/resources/products.json";
    private final Gson gson;

    public JProductRepository() {
        this.gson = new Gson();
        this.productMap = new HashMap<>();

        new File("src/main/resources").mkdirs();

        loadData();
        seedDemoData();
    }
    private void seedDemoData() {
        Product yuyu = new Product("Yuyu", "Toys", 9.99, "yoyo.png");
        yuyu.setStockQuantity(10);

        Product car = new Product("Race Car", "Toys", 12.0, "car.png");
        car.setStockQuantity(5);

        Product desk = new Product("Wooden Desk", "Furniture", 79.9, "desk.png");
        desk.setStockQuantity(60);

        // Add them to the internal map/list
        addItem(yuyu);
        addItem(car);
        addItem(desk);

        // Save to file immediately so next time they load naturally
        // (Assuming addItem calls saveData(), otherwise call saveData() here)
    }



    private void saveData() {
        try (Writer writer = new FileWriter(FILE_PATH)) {
            gson.toJson(productMap, writer);
        } catch (IOException e) {
            throw new RuntimeException("CRITICAL: Failed to save product data to file.", e);
        }
    }

    private void loadData() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return;
        }

        try (Reader reader = new FileReader(file)) {
            Type type = new TypeToken<Map<Integer, Product>>(){}.getType();
            productMap = gson.fromJson(reader, type);

            if (productMap == null) {
                productMap = new HashMap<>();
            }
        } catch (IOException e) {
            throw new RuntimeException("CRITICAL: Failed to save product data to file.", e);
        }
    }


    @Override
    public void addItem(Product product) {
        productMap.put(product.getProductId(), product);
        saveData(); // Save immediately
    }

    @Override
    public void removeItem(Product product) {
        productMap.remove(product.getProductId());
        saveData();
    }

    @Override
    public void updateItem(Product product) {
        productMap.put(product.getProductId(), product);
        saveData();
    }

    @Override
    public Product getById(int id) {
        return productMap.get(id);
    }

    @Override
    public List<Product> getAll() {
        return new ArrayList<>(productMap.values());
    }

    public boolean hasProduct(Product product) {
        return productMap.containsKey(product.getProductId());
    }
}