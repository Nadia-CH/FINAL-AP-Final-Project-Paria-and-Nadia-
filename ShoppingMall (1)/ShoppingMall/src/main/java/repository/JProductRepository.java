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