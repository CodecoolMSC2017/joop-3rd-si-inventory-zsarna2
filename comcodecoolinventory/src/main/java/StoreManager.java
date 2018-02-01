import java.util.*;

public class StoreManager {

    private StorageCapable storage;

    public void addStorage(StorageCapable storage) {
        this.storage = storage;
    }

    public void addCDProduct(String name, int price, int tracks) {
        storage.storeCDProduct(name, price, tracks);
    }

    public void addBookProduct(String name, int price, int pages) {
        storage.storeBookProduct(name, price, pages);
    }

    public String listProducts() {
        List<Product> listOfProducts = storage.getAllProduct();
        String products = "";
        for (int i = 0; i < listOfProducts.size(); i++) {
            if (i == listOfProducts.size() - 1) {
                products += listOfProducts.get(i).getName();
            } else {
                products += listOfProducts.get(i).getName() + ", ";
            }
        }
        return products;
    }

    public int getTotalProductPrice() {
        int sum = 0;
        for(Product product : storage.getAllProduct()){
            sum += product.getPrice();
        }
        return sum;
    }
}