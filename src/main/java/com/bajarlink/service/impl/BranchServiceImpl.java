package com.bajarlink.service.impl;

import com.bajarlink.exception.UserException;
import com.bajarlink.mapper.BranchMapper;
import com.bajarlink.model.Branch;
import com.bajarlink.model.Store;
import com.bajarlink.model.User;
import com.bajarlink.payload.dto.BranchDto;
import com.bajarlink.repo.BranchRepo;
import com.bajarlink.repo.StoreRepo;
import com.bajarlink.service.BranchService;
import com.bajarlink.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BranchServiceImpl implements BranchService {

    private final BranchRepo branchRepo;
    private final StoreRepo storeRepo;
    private final UserService userService;

    @Override
    public BranchDto createBranch(BranchDto branchDto) throws UserException {
        User currentUser = userService.getCurrentUser();
        Store store = storeRepo.findByStoreAdminId(currentUser.getId());

        Branch branch =BranchMapper.toEntity(branchDto, store);
        Branch saveBranch = branchRepo.save(branch);
        return BranchMapper.toDto(saveBranch);
    }

    @Override
    public BranchDto updateBranch(Long id, BranchDto branchDto) throws Exception {
        Branch existing = branchRepo.findById(id).orElseThrow(
                () -> new Exception("Branch not found..")
        );

        existing.setName(branchDto.getName());
        existing.setAddress(branchDto.getAddress());
        existing.setEmail(branchDto.getEmail());
        existing.setPhone(branchDto.getPhone());
        existing.setUpdatedAt(LocalDateTime.now());
        existing.setWorkingDays(branchDto.getWorkingDays());
        existing.setOpenTime(branchDto.getOpenTime());
        existing.setCloseTime(branchDto.getCloseTime());

        Branch saveBranch = branchRepo.save(existing);
        return BranchMapper.toDto(saveBranch);
    }

    @Override
    public void deleteBranch(Long id) throws Exception {
        Branch existing = branchRepo.findById(id).orElseThrow(
                () -> new Exception("Branch not found..")
        );
        branchRepo.delete(existing);
    }

    @Override
    public List<BranchDto> getAllBranchByStoreId(Long storeId) {
        List<Branch> branches = branchRepo.findByStoreId(storeId);

        return branches.stream().map(BranchMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public BranchDto getBranchById(Long id) throws Exception {
        Branch existing = branchRepo.findById(id).orElseThrow(
                () -> new Exception("Branch not found..")
        );
        return BranchMapper.toDto(existing);
    }
}
