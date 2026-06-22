import java.util.HashMap;

class Product {
    int productId;
    String productName;
    int quantity;
    double price;

    Product(int productId, String productName, int quantity, double price) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }
}

public class InventoryManagementSystem {
    HashMap<Integer, Product> inventory = new HashMap<>();

    void addProduct(Product product) {
        inventory.put(product.productId, product);
    }

    void updateProduct(int id, int quantity) {
        if (inventory.containsKey(id)) {
            inventory.get(id).quantity = quantity;
        }
    }

    void deleteProduct(int id) {
        inventory.remove(id);
    }

    public static void main(String[] args) {
        InventoryManagementSystem ims = new InventoryManagementSystem();

        ims.addProduct(new Product(1, "Laptop", 10, 50000));
        ims.updateProduct(1, 20);
        ims.deleteProduct(1);
    }
}