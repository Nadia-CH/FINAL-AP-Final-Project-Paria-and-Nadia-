package model;

public class Customer extends User {

    private int id;

    private static int idgenerator = 2000;

    // 1. EMPTY CONSTRUCTOR (Crucial for GSON loading)
    public Customer() {
        super("", ""); // Just satisfying the parent User class
    }

    public Customer(String username, String password) {
        super(username, password);
        this.id = idgenerator++;
    }

    public Customer(int id, String username, String password) {
        super(username, password);
        this.id = id;
        // Important: Update generator so we don't re-use this ID later
        if (id >= idgenerator) {
            idgenerator = id + 1;
        }
    }


    public int getId() {
        return id;
    }

    @Override
    public String getRole() {
        return "CUSTOMER";
    }
}