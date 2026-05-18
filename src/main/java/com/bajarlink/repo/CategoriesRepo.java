package com.bajarlink.repo;

import com.bajarlink.model.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoriesRepo extends JpaRepository<Categories, Long> {

    List<Categories> findByStoreId(Long storeId);
}
