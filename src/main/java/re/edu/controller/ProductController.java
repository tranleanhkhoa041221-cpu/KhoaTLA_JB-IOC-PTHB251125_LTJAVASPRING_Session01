package re.edu.controller;

import org.springframework.web.bind.annotation.*;
import re.edu.model.ApiResponse;
import re.edu.model.Product;
import re.edu.service.ProductService;

import java.util.List;


@RestController
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/api/products")
    public ApiResponse<List<Product>> findAll() {
        List<Product> productList = productService.findAll();
        return ApiResponse.success(200, "List of products", productList);
    }

    @GetMapping("/api/products/{id}")
    public ApiResponse<Product> findById(@PathVariable int id) {
        Product product = productService.findById(id);

        if (product == null) {
            return ApiResponse.error(404, "Product not found with Id: " + id);
        }

        return ApiResponse.success(200, "Product found", product);
    }

    @PostMapping("/api/products")
    public ApiResponse<Product> addProduct(@RequestBody Product product) {
        if (product.getName() == null || product.getName().isBlank()) {
            return ApiResponse.error(400, "Product name is empty");
        }
        if (product.getPrice() <= 0) {
            return ApiResponse.error(400, "Price must be greater than 0");
        }
        Product productAdded = productService.addProduct(product);
        return ApiResponse.success(201, "Product added success with Id: " + productAdded.getId(), productAdded);
    }

    @PutMapping("/api/products/{id}")
    public ApiResponse<Product> updateProduct(@PathVariable int id ,@RequestBody Product product) {
        Product existingProduct = productService.findById(id);
        if (existingProduct == null) {
            return ApiResponse.error(404, "Product not found with Id: " + id);
        }
        if (product.getName() == null || product.getName().isBlank()) {
            return ApiResponse.error(400, "Product name is empty");
        }
        if (product.getPrice() <= 0) {
            return ApiResponse.error(400, "Price must be greater than 0");
        }
        Product productUpdated = productService.updateProduct(id,product);
        return ApiResponse.success(200, "Product updated success with Id: " + productUpdated.getId(), productUpdated);
    }

    @DeleteMapping("/api/products/{id}")
    public ApiResponse<Product> deleteProduct(@PathVariable int id) {
        boolean deleted = productService.deleteProduct(id);
        if (!deleted) {
            return ApiResponse.error(404, "Product not found with Id: " + id);

        }
        return ApiResponse.success(200,"Product deleted success with Id: " + id, null);
    }









}
