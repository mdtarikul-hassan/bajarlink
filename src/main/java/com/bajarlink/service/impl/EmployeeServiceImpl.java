package com.bajarlink.service.impl;


import com.bajarlink.domain.UserRole;
import com.bajarlink.mapper.UserMapper;
import com.bajarlink.model.Branch;
import com.bajarlink.model.Store;
import com.bajarlink.model.User;
import com.bajarlink.payload.dto.UserDto;
import com.bajarlink.repo.BranchRepo;
import com.bajarlink.repo.StoreRepo;
import com.bajarlink.repo.UserRepo;
import com.bajarlink.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final UserRepo userRepo;
    private final BranchRepo branchRepo;
    private final StoreRepo storeRepo;
    public final PasswordEncoder passwordEncoder;

    @Override
    public UserDto createStoreEmployee(UserDto employee, Long storeId) throws Exception {
        Store store = storeRepo.findById(storeId).orElseThrow(
                () -> new Exception("Store Not found ..")
        );
        Branch branch = null;
        if(employee.getRole() == UserRole.ROLE_BRANCH_MANAGER){
            if(employee.getBranchId() == null){
                throw new Exception("Branch Id is Required to create branch");
            }
            branch = branchRepo.findById(employee.getBranchId()).orElseThrow(
                    () -> new Exception("Branch Not found ..")
            );
        }
        User user = UserMapper.toEntity(employee);
        user.setBranch(branch);
        user.setStore(store);
        user.setPassword(passwordEncoder.encode(employee.getPassword()));
        User savedEmployee = userRepo.save(user);

        if(employee.getRole() == UserRole.ROLE_BRANCH_MANAGER && branch != null){
            branch.setManager(savedEmployee);
            branchRepo.save(branch);
        }
        return UserMapper.toDTO(savedEmployee);
    }

    @Override
    public UserDto createBranchEmployee(UserDto employee, Long branchId) throws Exception {
        Branch branch = branchRepo.findById(branchId).orElseThrow(
                () -> new Exception("Branch not found ..")
        );
        if(employee.getRole() == UserRole.ROLE_BRANCH_CASHIER || employee.getRole() == UserRole.ROLE_BRANCH_MANAGER) {
            User user = UserMapper.toEntity(employee);
            user.setBranch(branch);
            user.setStore(branch.getStore());
            user.setPassword(passwordEncoder.encode(employee.getPassword()));
            User savedEmployee = userRepo.save(user);
            return UserMapper.toDTO(savedEmployee);
        }
        throw new Exception("Branch Role not supported");
    }

    @Override
    public UserDto updateEmployee(Long employeeId, UserDto employeeDetails) throws Exception {
        User existingEmployee = userRepo.findById(employeeId).orElseThrow(
                () -> new Exception("User not found")
        );
        existingEmployee.setEmail(employeeDetails.getEmail());
        existingEmployee.setFullName(employeeDetails.getFullName());
        existingEmployee.setPhoneNumber(employeeDetails.getPhoneNumber());
        existingEmployee.setRole(employeeDetails.getRole());

        // update branch and store
        if(employeeDetails.getBranchId() != null){
            Branch branch = branchRepo.findById(employeeDetails.getBranchId()).orElseThrow(
                    () -> new Exception("Branch not found ..")
            );
            existingEmployee.setBranch(branch);
            existingEmployee.setStore(branch.getStore());
        }

        // update password if provided
        if(employeeDetails.getPassword() != null && !employeeDetails.getPassword().isBlank()){
            existingEmployee.setPassword(passwordEncoder.encode(employeeDetails.getPassword()));
        }


        return  UserMapper.toDTO(userRepo.save(existingEmployee));
    }

    @Override
    public void deleteEmployee(Long employeeId) throws Exception {
        User employee = userRepo.findById(employeeId).orElseThrow(
                () -> new Exception("Employee not found")
        );
        userRepo.delete(employee);
    }

    @Override
    public List<UserDto> getStoreEmployees(Long storeId, UserRole role) throws Exception {
        Store store = storeRepo.findById(storeId).orElseThrow(
                () -> new Exception("Store Not found ..")
        );
        return userRepo.findByStore(store).stream()
                .filter(user -> role==null || user.getRole() == null)
                .map(UserMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDto> getBranchEmployees(Long branchId, UserRole role) throws Exception {
        Branch branch = branchRepo.findById(branchId).orElseThrow(
                () -> new Exception("Branch not found ..")
        );
        return userRepo.findByBranchId(branchId)
                .stream().filter(
                        user -> role==null || user.getRole() == null
                ).map(UserMapper::toDTO)
                .collect(Collectors.toList());
    }
}
