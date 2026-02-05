package com.severstal.testseverstal.service;

import com.severstal.testseverstal.entity.Product;
import com.severstal.testseverstal.entity.Provider;
import com.severstal.testseverstal.repository.ProductRepository;
import com.severstal.testseverstal.repository.ProviderRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductService implements CRUDService<Product, Integer> {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProviderRepository providerRepository;

    @Override
    public Product save(Product entity) {
        return productRepository.save(entity);
    }

    public Product save(int providerId, Product product) {
        Provider provider = providerRepository.findById(providerId)
                .orElseThrow(() -> new EntityNotFoundException("Provider not found: " + providerId));
        product.setProvider(provider);
        provider.getProducts().add(product);
        return productRepository.save(product);
    }

    @Override
    public Optional<Product> findById(Integer integer) {
        return productRepository.findById(integer);
    }

    public List<Product> getProductsForProvider(int providerId) {
        Provider provider = providerRepository.findById(providerId)
                .orElseThrow(() -> new EntityNotFoundException("Provider not found: " + providerId));
        return provider.getProducts();
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public void delete(Product entity) {
        productRepository.delete(entity);
    }

}
