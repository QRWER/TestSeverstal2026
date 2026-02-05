package com.severstal.testseverstal.repository;

import com.severstal.testseverstal.entity.Product;
import com.severstal.testseverstal.entity.Provider;
import com.severstal.testseverstal.entity.Supply;
import com.severstal.testseverstal.entity.TypeOfProduct;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class SupplyRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private SupplyRepository supplyRepository;

    @Test
    void findByProviderId_ReturnsSupplies() {
        Provider provider = new Provider();
        provider.setName("Test Provider");
        provider = entityManager.persistAndFlush(provider);

        Product product1 = new Product();
        product1.setType(TypeOfProduct.APPLE_ANTONOVKA);
        product1.setProvider(provider);
        product1.setDate(new Date());
        product1.setCost(100.0);
        product1 = entityManager.persistAndFlush(product1);

        Product product2 = new Product();
        product2.setType(TypeOfProduct.APPLE_SPARTAN);
        product2.setProvider(provider);
        product2.setDate(new Date());
        product2.setCost(150.0);
        product2 = entityManager.persistAndFlush(product2);

        Supply supply = new Supply();
        supply.setDate(new Date());
        supply.setProvider(provider);
        supply.setProducts(List.of(product1, product2));
        supply.setTotalCost(250000.0);
        entityManager.persistAndFlush(supply);

        List<Supply> supplies = supplyRepository.findByProviderId(provider.getId());

        assertThat(supplies).hasSize(1);
        assertThat(supplies.get(0).getProducts()).hasSize(2);
    }

}


