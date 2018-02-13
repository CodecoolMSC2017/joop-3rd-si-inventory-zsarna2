import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CsvStoreTest {

    private CsvStore store;
    private List<Product> productList;
    private String fileName = "testCSV.csv";
    private StoreManager storage;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        productList = new ArrayList<>();
        storage = new StoreManager();
        store = new PersistentCsvStore();
        File file = new File(fileName);
        try {
            file.createNewFile();
        } catch (IOException io) {
            io.getMessage();
        }
        storage.addStorage(store);
    }

    @org.junit.jupiter.api.Test
    void loadProducts() {
        storage.addBookProduct("testBook", 10, 450);
        storage.addCDProduct("testCD", 5, 12);
        store.store(fileName);
        productList = store.loadProducts(fileName);
        assertEquals("testBook", productList.get(0).getName());
        assertEquals(10, productList.get(0).getPrice());
        assertEquals("testCD", productList.get(1).getName());
        assertEquals(5, productList.get(1).getPrice());
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        Path path = Paths.get(fileName);
        try {
            Files.delete(path);
        } catch (Exception e) {
            e.getMessage();
        }
    }
}