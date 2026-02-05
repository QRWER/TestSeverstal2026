package com.severstal.testseverstal.service;

import com.severstal.testseverstal.entity.Product;
import com.severstal.testseverstal.entity.Provider;
import com.severstal.testseverstal.entity.Supply;
import com.severstal.testseverstal.exception.ProductMismatchException;
import com.severstal.testseverstal.exception.ProviderMismatchException;
import com.severstal.testseverstal.repository.ProductRepository;
import com.severstal.testseverstal.repository.ProviderRepository;
import com.severstal.testseverstal.repository.SupplyRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SupplyService implements CRUDService<Supply, Integer> {

    @Autowired
    private SupplyRepository supplyRepository;

    @Autowired
    private ProviderRepository providerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Supply save(Supply entity) {
        return supplyRepository.save(entity);
    }

    public Supply save(Date date, int providerId, List<Integer> productsId) {
        Provider provider = providerRepository.findById(providerId)
                .orElseThrow(() -> new EntityNotFoundException("Provider not found: " + providerId));
        List<Product> products = new ArrayList<>();
        for (Integer productId : productsId) {
            Product product  = productRepository.findById(productId)
                    .orElseThrow(() -> new EntityNotFoundException("Product not found: " + productId));
            if (!product.getProvider().equals(provider)) throw new ProviderMismatchException("Provider: " + providerId + " doesn't have product: " + productId);
            if (product.getDate().getTime() != date.getTime()) throw new ProductMismatchException("Product's with id: " + product.getId() + " and time: " + product.getDate() + " is mismatch with supplier's date: " + date);
            products.add(product);
        }
        Supply supply = new Supply(date, provider, products);
        return supplyRepository.save(supply);
    }

    @Override
    public Optional<Supply> findById(Integer id) {
        return supplyRepository.findById(id);
    }

    @Override
    public List<Supply> findAll() {
        return supplyRepository.findAll();
    }

    @Override
    public void delete(Supply entity) {
        supplyRepository.delete(entity);
    }

    public List<Supply> findByProviderId(int providerId) {
        Provider provider = providerRepository.findById(providerId)
                .orElseThrow(() -> new EntityNotFoundException("Provider not found: " + providerId));
        return provider.getSupplies();
    }
}
