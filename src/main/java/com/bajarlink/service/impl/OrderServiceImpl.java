package com.bajarlink.service.impl;

import com.bajarlink.domain.OrderStatus;
import com.bajarlink.domain.PaymentType;
import com.bajarlink.exception.UserException;
import com.bajarlink.mapper.OrderMapper;
import com.bajarlink.model.*;
import com.bajarlink.payload.dto.OrderDto;
import com.bajarlink.repo.OrderRepo;
import com.bajarlink.repo.ProductRepo;
import com.bajarlink.service.OrderService;
import com.bajarlink.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepo orderRepo;
    private final UserService userService;
    private final ProductRepo productRepo;

    @Override
    public OrderDto createOrder(OrderDto orderDto) throws UserException {
        User cashier = userService.getCurrentUser();
        Branch branch = cashier.getBranch();
        if(branch == null) {
            throw new UserException("User is not logged in");
        }
        Order order =Order.builder()
                .branch(branch)
                .cashier(cashier)
                .paymentType(orderDto.getPaymentType())
                .build();
        List<OrderItem> orderItem = orderDto.getItems().stream().map(
                itemDto ->{
                    Product product = productRepo.findById(itemDto.getProductId()).orElseThrow(
                            () -> new EntityNotFoundException("Product not found.. ")
                    );
                    return OrderItem.builder()
                            .product(product)
                            .quantity(itemDto.getQuantity())
                            .price(product.getSellingPrice() * itemDto.getQuantity())
                            .order(order)
                            .build();
                }
        ).toList();
        double total = orderItem.stream().mapToDouble(OrderItem::getQuantity).sum();
        order.setTotalAmount(total);
        order.setItems(orderItem);
        Order savedOrder = orderRepo.save(order);

        return OrderMapper.toDto(savedOrder);
    }

    @Override
    public OrderDto getOrderById(Long id) throws Exception {
        return orderRepo.findById(id)
                .map(OrderMapper::toDto)
                .orElseThrow(
                () -> new Exception("Order not found")
        );
    }

    @Override
    public List<OrderDto> getOrdersByBranch(Long branchId, Long customerId, Long cashierId, PaymentType paymentType, OrderStatus orderStatus) {
        return orderRepo.findByBranchId(branchId).stream()
                .filter(order -> customerId == null ||
                        (order.getCustomer() != null && order.getCustomer().getId().equals(customerId)))
                .filter(order -> cashierId == null ||
                        (order.getCashier() != null && order.getCashier().getId().equals(customerId)))
                .filter(order -> paymentType == null || order.getPaymentType() == paymentType)
                .map(OrderMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> getOrderByCashier(Long cashierId) {
        return orderRepo.findByCashierId(cashierId).stream()
                .map(OrderMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteOrder(Long orderId) throws Exception {
        Order order = orderRepo.findById(orderId).orElseThrow(
                () -> new  Exception("Order not found.. ")
        );
        orderRepo.delete(order);
    }

    @Override
    public List<OrderDto> getTodayOrdersByBranch(Long branchId) {
        LocalDate today = LocalDate.now();
        LocalDateTime start =  today.atStartOfDay();
        LocalDateTime end = today.plusDays(1).atStartOfDay();

        return orderRepo.findByBranchIdAndCreatedAtBetween(branchId, start, end).stream()
                .map(OrderMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> getOrdersByCustomerId(Long customerId) {
        return orderRepo.findByCustomerId(customerId).stream()
                .map(OrderMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> getTop5RecentOrdersByBranchId(Long branchId) {
        return orderRepo.findTop5ByBranchIdOrderByCreatedAtDesc(branchId).stream()
                .map(OrderMapper::toDto)
                .collect(Collectors.toList());
    }
}
