package com.bajarlink.service;

import com.bajarlink.domain.UserRole;
import com.bajarlink.model.User;
import com.bajarlink.payload.dto.UserDto;

import java.util.List;

public interface EmployeeService {
    UserDto createStoreEmployee(UserDto employee, Long storeId) throws Exception;
    UserDto createBranchEmployee(UserDto employee, Long branchId) throws Exception;
    UserDto updateEmployee(Long employeeId, UserDto employeeDetails) throws Exception;
    void deleteEmployee(Long employeeId) throws Exception;
    List<UserDto> getStoreEmployees(Long storeId, UserRole role) throws Exception;
    List<UserDto> getBranchEmployees(Long branchId, UserRole role) throws Exception;
}
