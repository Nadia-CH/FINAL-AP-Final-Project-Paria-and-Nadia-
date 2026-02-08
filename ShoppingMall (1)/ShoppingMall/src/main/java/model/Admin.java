package model;

public class Admin extends User {

    public Admin(String username, String password) {
        super(username, password);
    }

    public String getName() {
        String name = super.getUsername().replace("ADMIN", "");
        return name;
    }

    @Override
    public String getRole() {
        return "ADMIN";
    }
}