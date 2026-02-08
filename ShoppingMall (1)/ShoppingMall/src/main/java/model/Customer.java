package model;

public class Customer extends User {

    private int id;
    private double balance = 100;
    private static int idgenerator = 2000;

    public Customer(String username, String password) {
        super(username, password);
        this.id = idgenerator++;
    }


    public int getId() {
        return id;
    }

    @Override
    public String getRole() {
        return "CUSTOMER";
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}