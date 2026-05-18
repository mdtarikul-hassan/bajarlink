package com.bajarlink.repo;

import com.bajarlink.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product, Long> {

    List<Product> findByStoreId(Long storeId);

    @Query("SELECT p FROM Product p WHERE p.store.id = :storeId AND (" +
            "LOWER(p.name) LIKE LOWER(CONCAT('%', :q, '%')) " +
            "OR LOWER(p.name) LIKE LOWER(CONCAT('%', :q, '%'))" +
            "OR LOWER(p.sku) LIKE LOWER(CONCAT('%', :q, '%'))" +
            "OR LOWER(p.brand) LIKE LOWER(CONCAT('%', :q, '%')))")
    List<Product> searchByKeyword(@Param("storeId") Long storeId, @Param("q") String keyword);
}
