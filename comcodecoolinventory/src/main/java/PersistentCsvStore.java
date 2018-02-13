public class PersistentCsvStore extends CsvStore {

    protected void storeProduct(Product product) {
        getAllProduct().add(product);
    }
}
