package com.bajarlink.controller;

import com.bajarlink.exception.UserException;
import com.bajarlink.model.User;
import com.bajarlink.payload.dto.BranchDto;
import com.bajarlink.payload.response.ApiResponse;
import com.bajarlink.service.BranchService;
import com.bajarlink.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/branch")
public class BranchController {

    private final BranchService branchService;
    private final UserService userService;

    @PostMapping()
    public ResponseEntity<BranchDto> createBranch(@RequestBody BranchDto branchDto) throws UserException {

        return ResponseEntity.ok(
                branchService.createBranch(branchDto)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<BranchDto> getBranchById(@PathVariable Long id) throws Exception {

        return ResponseEntity.ok(
                branchService.getBranchById(id)
        );
    }

    @GetMapping("/store/{id}")
    public ResponseEntity<List<BranchDto>> getAllBranchByStoreId(@PathVariable Long id) throws Exception {

        List<BranchDto> branches = branchService.getAllBranchByStoreId(id);
        return ResponseEntity.ok(branches);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BranchDto> updateBranch(@PathVariable Long id,
                                                  @RequestBody BranchDto branchDto) throws Exception {

        return ResponseEntity.ok(
                branchService.updateBranch(id, branchDto)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteBranch(@PathVariable Long id) throws Exception {
        branchService.deleteBranch(id);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Branch deleted Successfully");
        return ResponseEntity.ok(apiResponse);
    }
}
