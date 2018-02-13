import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public abstract class CsvStore implements StorageCapable {

    private List<Product> listOfProducts = new ArrayList<>();

    public List<Product> getAllProduct() {
        return listOfProducts;
    }

    public void storeCDProduct(String name, int price, int tracks) {
        storeProduct(createProduct("cd", name, price, tracks));
    }

    public void storeBookProduct(String name, int price, int pages) {
        storeProduct(createProduct("book", name, price, pages));
    }

    abstract void storeProduct(Product product);

    private Product createProduct(String type, String name,int price, int size) {
        Product product = null;

        try {
            if (type.toLowerCase().equals("book")) {
                product = new BookProduct(name, price, size);
            } else if (type.toLowerCase().equals("cd")) {
                product = new CDProduct(name, price, size);
            }

        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
            System.out.println("Only CDs and Books are allowed!");
        }
        return product;
    }

    private void saveToCsv(String filename) {
        try {
            PrintWriter pw = new PrintWriter(filename);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < getAllProduct().size() ; i++) {
                sb.append("name="+getAllProduct().get(i).getName()+";");
                sb.append("price="+getAllProduct().get(i).getPrice()+";");
                if (getAllProduct().get(i) instanceof BookProduct) {
                    sb.append("pages="+((BookProduct) getAllProduct().get(i)).getNumOfPages()+"\n");
                } else if (getAllProduct().get(i) instanceof CDProduct) {
                    sb.append("tracks="+((CDProduct) getAllProduct().get(i)).getNumOfTracks()+"\n");
                } else {
                    throw new Exception("Product type not allowed");
                }

            }
            pw.write(sb.toString());
            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Product> loadProducts(String filename) {
        try {
            File CsvFile = new File(filename);
            BufferedReader br = new BufferedReader(new FileReader(CsvFile));
            String line = "";
            Product product;
            while ((line = br.readLine()) != null) {
                line = line.replaceAll("=", ";");
                String[] currentProduct = line.split(";");
                if (currentProduct[4].equals("tracks")) {
                    product = new CDProduct(currentProduct[1], Integer.parseInt(currentProduct[3]), Integer.parseInt(currentProduct[5]));
                    listOfProducts.add(product);
                } else {
                    product = new BookProduct(currentProduct[1], Integer.parseInt(currentProduct[3]), Integer.parseInt(currentProduct[5]));
                    listOfProducts.add(product);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listOfProducts;
    }

    public void store(String filename) {
        saveToCsv(filename);

    }
}