package com.bajarlink.repo;

import com.bajarlink.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventoryRepo extends JpaRepository<Inventory, Long> {

    Inventory findByProductIdAndBranchId( Long productId, Long branchId);
    List<Inventory> findByBranchId(Long branchId);
}
