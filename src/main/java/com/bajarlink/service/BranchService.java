package com.bajarlink.service;

import com.bajarlink.exception.UserException;
import com.bajarlink.model.User;
import com.bajarlink.payload.dto.BranchDto;

import java.util.List;

public interface BranchService {

    BranchDto createBranch(BranchDto branchDto) throws UserException;
    BranchDto updateBranch(Long id, BranchDto branchDto) throws Exception;
    void deleteBranch(Long id) throws Exception;
    List<BranchDto> getAllBranchByStoreId(Long storeId);
    BranchDto getBranchById(Long id) throws Exception;
}
