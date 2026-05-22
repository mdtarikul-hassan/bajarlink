package com.bajarlink.service.impl;

import com.bajarlink.model.Customer;
import com.bajarlink.repo.CustomerRepo;
import com.bajarlink.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepo customerRepo;
    @Override
    public Customer createCustomer(Customer customer) {
        return customerRepo.save(customer);
    }

    @Override
    public Customer updateCustomer(Long id, Customer customer) throws Exception {
        Customer customer1 = customerRepo.findById(id).orElseThrow(
                () -> new Exception("Customer Not found")
        );
        customer1.setFullName(customer.getFullName());
        customer1.setPhoneNumber(customer.getPhoneNumber());
        customer1.setEmail(customer.getEmail());
        customer1.setUpdatedAt(LocalDateTime.now());
        return customerRepo.save(customer1);
    }

    @Override
    public void deleteCustomer(Long id) throws Exception {
        Customer customer = customerRepo.findById(id).orElseThrow(
                () -> new Exception("Customer Not found")
        );
        customerRepo.delete(customer);
    }

    @Override
    public Customer getCustomerById(Long id) throws Exception {
        return customerRepo.findById(id).orElseThrow(
                () -> new Exception("Customer Not found")
        );
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepo.findAll();
    }

    @Override
    public List<Customer> searchCustomers(String keyword) {
        return customerRepo.findByFullNameContainingIgnoreCaseOrPhoneNumberContainingIgnoreCaseOrEmailContainingIgnoreCase(keyword, keyword, keyword);
    }
}
