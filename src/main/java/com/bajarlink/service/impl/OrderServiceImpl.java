package com.bajarlink.service.impl;

import com.bajarlink.domain.OrderStatus;
import com.bajarlink.domain.PaymentType;
import com.bajarlink.payload.dto.OrderDto;
import com.bajarlink.repo.OrderRepo;
import com.bajarlink.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepo orderRepo;

    @Override
    public OrderDto createOrder(OrderDto orderDto) {
        return null;
    }

    @Override
    public OrderDto getOrderById(Long orderId) {
        return null;
    }

    @Override
    public List<OrderDto> getOrdersByBranch(Long branchId, Long customerId, Long cashierId, PaymentType paymentType, OrderStatus orderStatus) {
        return List.of();
    }

    @Override
    public List<OrderDto> getOrderByCashier(Long cashierId) {
        return List.of();
    }

    @Override
    public void deleteOrder(Long orderId) {

    }

    @Override
    public List<OrderDto> getTodayOrdersByBranch(Long branchId) {
        return List.of();
    }

    @Override
    public List<OrderDto> getOrdersByCustomerId(Long customerId) {
        return List.of();
    }

    @Override
    public List<OrderDto> getTop5RecentOrdersByBranchId(Long branchId) {
        return List.of();
    }
}
