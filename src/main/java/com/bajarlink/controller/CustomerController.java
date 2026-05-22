package com.bajarlink.controller;

import com.bajarlink.model.Customer;
import com.bajarlink.payload.response.ApiResponse;
import com.bajarlink.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customer")
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping()
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) throws Exception{
        return  ResponseEntity.ok(customerService.createCustomer(customer));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id,
            @RequestBody Customer customer) throws Exception{
        return  ResponseEntity.ok(customerService.updateCustomer(id, customer));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCustomer(@PathVariable Long id) throws Exception{
        customerService.deleteCustomer(id);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Customer deleted successfully");
        return  ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) throws Exception{
        return  ResponseEntity.ok(customerService.getCustomerById(id));
    }

    @GetMapping()
    public ResponseEntity<List<Customer>> getAllCustomer() throws Exception{
        return  ResponseEntity.ok(customerService.getAllCustomers());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Customer>> searchCustomer(@RequestParam String q) throws Exception{
        return  ResponseEntity.ok(customerService.searchCustomers(q));
    }


}
