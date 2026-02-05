package com.severstal.testseverstal.repository;

import com.severstal.testseverstal.entity.Supply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SupplyRepository extends JpaRepository<Supply, Integer> {
    @Query("SELECT s FROM Supply s WHERE s.provider.id = :providerId")
    List<Supply> findByProviderId(@Param("providerId") Integer providerId);
}
