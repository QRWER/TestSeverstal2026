package com.severstal.testseverstal.service;

import com.severstal.testseverstal.entity.Provider;
import com.severstal.testseverstal.repository.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProviderService implements CRUDService<Provider, Integer> {

    @Autowired
    private ProviderRepository providerRepository;

    @Override
    public Provider save(Provider entity) {
        return providerRepository.save(entity);
    }

    @Override
    public Optional<Provider> findById(Integer integer) {
        return providerRepository.findById(integer);
    }

    @Override
    public List<Provider> findAll() {
        return providerRepository.findAll();
    }

    @Override
    public void delete(Provider entity) {
        providerRepository.delete(entity);
    }
}
