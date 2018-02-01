public class Main {

    public static void main(String[] args) {

        PersistentStore myPersistent = new PersistentStore();
        StoreManager myStorage = new StoreManager();
        myStorage.addStorage(myPersistent);

        myStorage.addCDProduct("Hard rock",100,12);
        myStorage.addBookProduct("Slash", 1000, 480);

        myPersistent.store("Products.xml");
        myPersistent.loadProducts("Products.xml");
        System.out.println(myStorage.listProducts());
        System.out.println(myStorage.getTotalProductPrice());
    }
}