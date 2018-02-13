public class Main {

    public static void main(String[] args) {

        PersistentStore persistent = new PersistentStore();
        StoreManager myStorage = new StoreManager();
        myStorage.addStorage(persistent);

        persistent.store("product.csv");
        persistent.loadProducts("Products.xml");

        PersistentCsvStore csvpersistent = new PersistentCsvStore();
        StoreManager storage = new StoreManager();
        StoreManager csvstorage = new StoreManager();

        storage.addStorage(persistent);
        csvstorage.addStorage(csvpersistent);

        csvstorage.addCDProduct("Hard rock",100,12);
        csvstorage.addBookProduct("Slash", 1000, 480);

        persistent.loadProducts("Products.xml");
        csvpersistent.loadProducts("product.csv");
        System.out.println("Products from CSV file:");
        System.out.println(csvstorage.listProducts());
        System.out.println("Total price of the products: $"+csvstorage.getTotalProductPrice());
        System.out.println("-------------------------------------");
        System.out.println();
        System.out.println("Products from XML file:");
        System.out.println(storage.listProducts());
        System.out.println("Total price of the products: $"+storage.getTotalProductPrice());

        System.out.println(myStorage.listProducts());
        System.out.println(myStorage.getTotalProductPrice());
    }
}