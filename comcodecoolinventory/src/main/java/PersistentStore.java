public class PersistentStore extends Store {

    @Override
    public void storeProduct(Product product) {
        getAllProduct().add(product);
    }

}