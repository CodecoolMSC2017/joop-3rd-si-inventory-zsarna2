import java.util.*;

public interface StorageCapable {

    List<Product> getAllProduct();

    void storeCDProduct(String name, int price, int tracks);

    void storeBookProduct(String name, int price, int pages);
}