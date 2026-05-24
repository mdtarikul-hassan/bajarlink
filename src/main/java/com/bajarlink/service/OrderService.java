package com.bajarlink.service;

import com.bajarlink.domain.OrderStatus;
import com.bajarlink.domain.PaymentType;
import com.bajarlink.exception.UserException;
import com.bajarlink.payload.dto.OrderDto;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

public interface OrderService {

    OrderDto createOrder(OrderDto orderDto) throws UserException;
    OrderDto getOrderById(Long id) throws Exception;
    List<OrderDto> getOrdersByBranch(Long branchId,
                                     Long customerId,
                                     Long cashierId,
                                     PaymentType paymentType,
                                     OrderStatus orderStatus);
    List<OrderDto> getOrderByCashier(Long cashierId);
    void deleteOrder(Long orderId) throws Exception;
    List<OrderDto> getTodayOrdersByBranch(Long branchId);
    List<OrderDto> getOrdersByCustomerId(Long customerId);
    List<OrderDto> getTop5RecentOrdersByBranchId(Long branchId);
}
