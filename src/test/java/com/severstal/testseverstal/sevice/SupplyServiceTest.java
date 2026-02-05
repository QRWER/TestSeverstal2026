package com.severstal.testseverstal.sevice;

import com.severstal.testseverstal.entity.Product;
import com.severstal.testseverstal.entity.Provider;
import com.severstal.testseverstal.exception.ProviderMismatchException;
import com.severstal.testseverstal.repository.ProductRepository;
import com.severstal.testseverstal.repository.ProviderRepository;
import com.severstal.testseverstal.service.SupplyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class SupplyServiceTest {

    @Mock
    private ProviderRepository providerRepository;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private SupplyService supplyService;

    @Test
    void save_ProviderMismatch() {
        Provider provider = mock(Provider.class);
        Provider otherProvider = mock(Provider.class);

        when(provider.getId()).thenReturn(1);
        when(otherProvider.getId()).thenReturn(2);

        Product product = mock(Product.class);
        when(product.getProvider()).thenReturn(otherProvider);
        when(product.getDate()).thenReturn(new Date());

        when(providerRepository.findById(1)).thenReturn(Optional.of(provider));
        when(productRepository.findById(1)).thenReturn(Optional.of(product));

        assertThrows(ProviderMismatchException.class,
                () -> supplyService.save(new Date(), 1, List.of(1)));
    }
}

