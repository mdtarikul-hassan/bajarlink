package com.bajarlink.payload.dto;

import com.bajarlink.domain.PaymentType;
import com.bajarlink.model.Branch;
import com.bajarlink.model.Customer;
import com.bajarlink.model.OrderItem;
import com.bajarlink.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDto {

    private Long id;

    private Double totalAmount;

    private LocalDateTime createdAt;


    private BranchDto branch;

    private Long branchId;
    private Long customerId;

    private UserDto cashier;

    private Customer customer;

    private PaymentType paymentType;

    private List<OrderItemDto> items;
}
