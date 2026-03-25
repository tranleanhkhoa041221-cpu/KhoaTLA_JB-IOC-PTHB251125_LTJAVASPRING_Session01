package re.edu.service;

import org.springframework.stereotype.Service;
import re.edu.model.Product;
import re.edu.repository.ProductRepository;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(int id) {
        return productRepository.findById(id);
    }

    public Product addProduct(Product product) {
        productRepository.save(product);
        return product;
    }

    public Product updateProduct(int id, Product product) {
        Product productUpdate = productRepository.findById(id);
        if (productUpdate == null) {
            return null;
        }
        product.setId(id);
        productUpdate.setName(product.getName());
        productUpdate.setPrice(product.getPrice());
        return productUpdate;

    }

    public boolean deleteProduct(int id) {
        Product productDelete = productRepository.findById(id);
        if (productDelete == null) {
            return false;
        }
        productRepository.delete(productDelete);
        return true;
    }



}
