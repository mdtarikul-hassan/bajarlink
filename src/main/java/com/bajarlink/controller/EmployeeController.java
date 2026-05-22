package com.bajarlink.controller;

import com.bajarlink.domain.UserRole;
import com.bajarlink.model.User;
import com.bajarlink.payload.dto.UserDto;
import com.bajarlink.payload.response.ApiResponse;
import com.bajarlink.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping("/store/{storeId}")
    public ResponseEntity<UserDto> createStoreEmployee(@PathVariable Long storeId, @RequestBody UserDto userDto) throws Exception{
        return ResponseEntity.ok(employeeService.createStoreEmployee(userDto,storeId));
    }

    @PostMapping("/branch/{branchId}")
    public ResponseEntity<UserDto> createBranchEmployee(@PathVariable Long branchId, @RequestBody UserDto userDto) throws Exception{
        return ResponseEntity.ok(employeeService.createBranchEmployee(userDto,branchId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateEmployee(@PathVariable Long id, @RequestBody UserDto userDto) throws Exception{
        return ResponseEntity.ok(employeeService.updateEmployee(id,userDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteEmployee(@PathVariable Long id) throws Exception{
        employeeService.deleteEmployee(id);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Delete employee successfully");
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/store/{storeId}")
    public ResponseEntity<List<UserDto>> storeEmployees(@PathVariable Long storeId,
                                               @RequestParam(required = false)UserRole userRole) throws Exception{
        List<UserDto> employees = employeeService.getStoreEmployees(storeId, userRole);
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/branch/{branchId}")
    public ResponseEntity<List<UserDto>> branchEmployees(@PathVariable Long branchId,
                                                     @RequestParam(required = false)UserRole userRole) throws Exception{
        List<UserDto> employees = employeeService.getBranchEmployees(branchId, userRole);
        return ResponseEntity.ok(employees);
    }

}
