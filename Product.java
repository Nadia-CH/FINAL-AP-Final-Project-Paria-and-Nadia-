package model;

public class Product {
    private String name;
    private String category;
    private double price;
    private int stockQuantity = 1;
    private int productId;

    private static int generatedId = 1000;

    public Product(String name, String category, double price) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.productId = ++generatedId;
    }

    public void increaseStock(int amount ) {
        stockQuantity =  stockQuantity + amount;
    }
    public void decreaseStock(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        if (amount > stockQuantity) {
            throw new IllegalStateException("Not enough stock available");
        }
        stockQuantity -= amount;
    }
    public void setStockQuantity(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }
        this.stockQuantity = quantity;
    }
    public int  getStockQuantity() {
        return stockQuantity;
    }

    public int getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }
    public String getCategory() {
        return category;
    }
    public double getPrice() {
        return price;
    }

    public void setName(String name) {
        if (name != null && name.trim().length() != 0) {
            this.name = name.trim();
        }
        else {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
    }
    public void setCategory(String category) {
        if (category != null && category.trim().length() != 0) {
            this.category = category.trim();
        }
        else {
            throw new IllegalArgumentException("Category cannot be null or empty");
        }
    }
    public void setPrice(double price) {
        if (price >= 0) {
            this.price = price;
        }
        else {
            throw new IllegalArgumentException("invalid price amount");
        }
    }

    public String getInfo() {
        String info = "Product Name: " + this.name + ", Category: " + this.category + ", Price: " + this.price +
                ", Quantity: " + getStockQuantity() + ", productId:" + getProductId();
        return info;
    }
}