package re.edu.repository;


import org.springframework.stereotype.Repository;
import re.edu.model.Product;

import java.util.ArrayList;
import java.util.List;
@Repository
public class ProductRepository {
    private final List<Product> productList = new ArrayList<>();

    public ProductRepository() {
        productList.add(new Product(1,"SamSung",400));
        productList.add(new Product(2,"Vivo",300));
    }

    public List<Product> findAll() {
        return productList;
    }

    public int getMaxId() {
        int maxId = 0;
        for (Product product : productList) {
            if (product.getId() > maxId) {
                maxId = product.getId();
            }
        }
            return maxId;
        }

    public Product findById(int id) {
        for (Product product : productList) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    public void save(Product product) {
        product.setId(getMaxId() + 1);
        productList.add(product);
    }

    public void delete(Product product) {
        productList.remove(product);
    }






}
