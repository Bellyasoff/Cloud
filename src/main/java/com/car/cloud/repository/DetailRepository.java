package com.car.cloud.repository;

import com.car.cloud.model.Detail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DetailRepository extends JpaRepository<Detail, Long> {
    Optional<Detail> findByManufacturer(String url);
    @Query("SELECT c FROM Detail c WHERE c.manufacturer LIKE CONCAT('%', :query, '%') " +
            "or c.name LIKE CONCAT('%', :query, '%')" +
            "or c.oemNumber LIKE CONCAT('%', :query, '%')")
    List<Detail> searchDetails(String query);
}
