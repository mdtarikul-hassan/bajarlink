package com.bajarlink.repo;

import com.bajarlink.model.Branch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BranchRepo extends JpaRepository<Branch, Long> {

    List<Branch> findByStoreId(Long storeId);
}
