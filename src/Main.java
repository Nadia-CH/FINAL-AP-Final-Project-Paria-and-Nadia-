import model.Product;
import repository.ProductRepository;

// this is a test run
public class Main {
    public static void main(String[] args) {
        Product yuyu = new Product("yuyu", "toy", 9.99);
        yuyu.setStockQuantity(10);
        yuyu.increaseStock(3);
        yuyu.decreaseStock(5);
        System.out.println(yuyu.getInfo());
        Product car = new Product("car", "toy", 12.0);
        System.out.println(car.getInfo());
        Product desk = new Product("desk", "furniture", 79.9);

        ProductRepository pr = new ProductRepository();
        pr.addItem(yuyu);
        pr.addItem(car);
        pr.addItem(desk);
        desk.setStockQuantity(60);
        desk.decreaseStock(5);
        pr.updateItem(desk);
        for (int i = 0; i < pr.getAll().size(); i++) {
            System.out.println(pr.getAll().get(i).getInfo());
        }

    }
}