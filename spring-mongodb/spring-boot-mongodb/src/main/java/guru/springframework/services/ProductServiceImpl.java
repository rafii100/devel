package guru.springframework.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import guru.springframework.commands.ProductForm;
import guru.springframework.converters.ProductFormToProduct;
import guru.springframework.domain.Product;
import guru.springframework.repositories.ReactiveProductRepository;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

/**
 * Created by jt on 1/10/17.
 */
@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

	
    private ReactiveProductRepository productRepository;
    private ProductFormToProduct productFormToProduct;

    @Autowired
    public ProductServiceImpl(ReactiveProductRepository productRepository, ProductFormToProduct productFormToProduct) {
        this.productRepository = productRepository;
        this.productFormToProduct = productFormToProduct;
    }

    
    @Override
    public List<Product> listAll() {

        List<Product> products = productRepository.findAll().collectList().block();
        log.info("Number of products: " + productRepository.count().block());
        return products;
    }

    @Override
    public Product getById(String id) {
        return productRepository.findById(id).block();
    }

    @Override
    public Product saveOrUpdate(Product product) {
        productRepository.save(product);
        return product;
    }

    @Override
    public void delete(String id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product saveOrUpdateProductForm(ProductForm productForm) {
        Product savedProduct = saveOrUpdate(productFormToProduct.convert(productForm));

        System.out.println("Saved Product Id: " + savedProduct.getId());
        return savedProduct;
    }
}
