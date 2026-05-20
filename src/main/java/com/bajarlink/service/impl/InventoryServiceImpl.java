package com.bajarlink.service.impl;

import com.bajarlink.mapper.InventoryMapper;
import com.bajarlink.model.Branch;
import com.bajarlink.model.Inventory;
import com.bajarlink.model.Product;
import com.bajarlink.payload.dto.InventoryDto;
import com.bajarlink.repo.BranchRepo;
import com.bajarlink.repo.InventoryRepo;
import com.bajarlink.repo.ProductRepo;
import com.bajarlink.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepo inventoryRepo;
    private final ProductRepo productRepo;
    private final BranchRepo branchRepo;


    @Override
    public InventoryDto createInventory(InventoryDto inventoryDto) throws Exception {
        Branch branch = branchRepo.findById(inventoryDto.getBranchId()).orElseThrow(
                () -> new Exception("Branch not found..")
        );
        Product product = productRepo.findById(inventoryDto.getProductId()).orElseThrow(
                () -> new Exception("Product not found..")
        );
        Inventory inventory = InventoryMapper.toEntity(inventoryDto, product, branch);
        Inventory savedInventory = inventoryRepo.save(inventory);
        return InventoryMapper.toDto(savedInventory);
    }

    @Override
    public InventoryDto updateInventory(Long id, InventoryDto inventoryDto) throws Exception {
        Inventory existing = inventoryRepo.findById(id).orElseThrow(
                () -> new Exception("inventory not found..")
        );
        existing.setQuantity(inventoryDto.getQuantity());
        return InventoryMapper.toDto(inventoryRepo.save(existing));
    }

    @Override
    public void deleteInventory(Long id) throws Exception {
        Inventory existing = inventoryRepo.findById(id).orElseThrow(
                () -> new Exception("inventory not found..")
        );
        inventoryRepo.delete(existing);
    }

    @Override
    public InventoryDto getInventoryById(Long id) throws Exception {
        Inventory existing = inventoryRepo.findById(id).orElseThrow(
                () -> new Exception("inventory not found..")
        );
        return InventoryMapper.toDto(existing);
    }

    @Override
    public InventoryDto getInventoryByProductIdAndBranchId(Long productId, Long branchId) {
        Inventory inventory = inventoryRepo.findByProductIdAndBranchId(productId, branchId);
        return InventoryMapper.toDto(inventory);
    }

    @Override
    public List<InventoryDto> getAllInventoryByBranchId(Long branchId) {
        List<Inventory> inventories = inventoryRepo.findByBranchId(branchId);
        return inventories.stream().map(InventoryMapper::toDto).collect(Collectors.toList());
    }
}
