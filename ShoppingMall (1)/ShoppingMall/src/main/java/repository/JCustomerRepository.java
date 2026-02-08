package repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import model.Customer;

import java.io.*;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class JCustomerRepository {

    private final String FILE_PATH = "src/main/resources/customer_map.json";
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private Map<String, Integer> userMap = new HashMap<>();

    public JCustomerRepository() {
        loadData();

        // Seed default user if empty
        if (userMap.isEmpty()) {
            register("parya", "1234");
        }
    }

//    public Customer login(String username, String password) {
//        // 1. Check if user exists in the map
//        if (userMap.containsKey(username)) {
//            int savedId = userMap.get(username);
//
//            // 2. Re-create the customer with the SAVED ID
//            return new Customer(savedId, username, password);
//        }
//        return null; // User not found
//    }



    public void register(String username, String password) {
        Customer newCustomer = new Customer(username, password);
        userMap.put(username, newCustomer.getId());
        saveData();

//        return newCustomer;
    }

    public int getId(String username) {
        return userMap.get(username);
    }

    private void saveData() {
        try (Writer writer = new FileWriter(FILE_PATH)) {
            gson.toJson(userMap, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadData() {
        File file = new File(FILE_PATH);
        if (!file.exists()) return;

        try (Reader reader = new FileReader(file)) {
            Type type = new TypeToken<HashMap<String, Integer>>(){}.getType();
            userMap = gson.fromJson(reader, type);
            if (userMap == null) userMap = new HashMap<>();

            // Sync the static generator so we don't reuse IDs
            int maxId = 2000;
            for (int id : userMap.values()) {
                if (id > maxId) maxId = id;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}