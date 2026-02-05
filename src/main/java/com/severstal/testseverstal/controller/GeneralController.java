package com.severstal.testseverstal.controller;

import com.severstal.testseverstal.dto.CreateSupplyRequest;
import com.severstal.testseverstal.entity.Product;
import com.severstal.testseverstal.entity.Provider;
import com.severstal.testseverstal.entity.Supply;
import com.severstal.testseverstal.service.ProductService;
import com.severstal.testseverstal.service.ProviderService;
import com.severstal.testseverstal.service.SupplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@CrossOrigin
public class GeneralController {

    @Autowired
    ProductService productService;

    @Autowired
    ProviderService providerService;

    @Autowired
    SupplyService supplyService;

    @GetMapping("/providers")
    public ResponseEntity<?> getAllProviders() {
        List<Provider> providers = providerService.findAll();
        return providers.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(providers);
    }

    @GetMapping("/products")
    public ResponseEntity<?> getAllProducts() {
        List<Product> products = productService.findAll();
        return products.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(products);
    }

    @GetMapping("/supplies")
    public ResponseEntity<?> getAllSupplies() {
        List<Supply> supplies = supplyService.findAll();
        return supplies == null ? ResponseEntity.noContent().build() : ResponseEntity.ok(supplies);
    }

    @GetMapping("/provider/{providerId}/products")
    public ResponseEntity<?> getProductForProvider(@PathVariable int providerId) {
        List<Product> products = productService.getProductsForProvider(providerId);
        return products == null ? ResponseEntity.noContent().build() : ResponseEntity.ok(products);
    }

    @GetMapping("/provider/{providerId}/supplies")
    public ResponseEntity<?> getSuppliesForProvider(@PathVariable int providerId) {
        List<Supply> supplies = supplyService.findByProviderId(providerId);
        return supplies == null ? ResponseEntity.noContent().build() : ResponseEntity.ok(supplies);
    }

    @PostMapping("/provider/{providerId}/product")
    public ResponseEntity<?> addProduct(@PathVariable int providerId, @Valid @RequestBody Product product) {
        Product savedProduct = productService.save(providerId, product);
        return ResponseEntity.ok(savedProduct);
    }

    @PostMapping("/provider/{providerId}/supply")
    public ResponseEntity<?> createSupply(@PathVariable int providerId, @Valid @RequestBody CreateSupplyRequest request) {
        System.out.println(request);
        Supply supply = supplyService.save(
                request.getDate(),
                providerId,
                request.getProductIds()
        );
        return ResponseEntity.ok(supply);
    }

    @PostMapping("/provider")
    public ResponseEntity<?> addProvider(@Valid @RequestBody Provider provider) {
        Provider savedProvider = providerService.save(provider);
        return ResponseEntity.ok(savedProvider);
    }

    @DeleteMapping("/product")
    public ResponseEntity<?> deleteProduct(@RequestBody Product product) {
        productService.delete(product);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/provider")
    public ResponseEntity<?> deleteProvider(@RequestBody Provider provider) {
        providerService.delete(provider);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/supply")
    public ResponseEntity<?> deleteSupply(@RequestBody Supply supply) {
        supplyService.delete(supply);
        return ResponseEntity.ok().build();
    }

}
