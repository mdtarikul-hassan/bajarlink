package com.bajarlink.controller;

import com.bajarlink.domain.OrderStatus;
import com.bajarlink.domain.PaymentType;
import com.bajarlink.exception.UserException;
import com.bajarlink.payload.dto.OrderDto;
import com.bajarlink.payload.response.ApiResponse;
import com.bajarlink.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;

    @PostMapping()
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto orderDto) throws UserException {
        return ResponseEntity.ok(orderService.createOrder(orderDto));
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable Long orderId) throws Exception {
        return ResponseEntity.ok(orderService.getOrderById(orderId));
    }

    @GetMapping("/branch/{branchId}")
    public ResponseEntity<List<OrderDto>> getOrderByBranch(@PathVariable Long branchId,
                                                           @RequestParam(required = false) Long customerId,
                                                           @RequestParam(required = false) Long cashierId,
                                                           @RequestParam(required = false)PaymentType paymentType,
                                                           @RequestParam(required = false)OrderStatus orderStatus) throws Exception {
        return ResponseEntity.ok(orderService.getOrdersByBranch(branchId, customerId, cashierId, paymentType, orderStatus));
    }

    @GetMapping("/cashier/{cashierId}")
    public ResponseEntity<List<OrderDto>> getOrderByCashier(@PathVariable Long cashierId) throws Exception {
        return ResponseEntity.ok(orderService.getOrderByCashier(cashierId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteOrder(@PathVariable Long cashierId) throws Exception {
        orderService.deleteOrder(cashierId);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Order deleted");
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/today/branch/{branchId}")
    public ResponseEntity<List<OrderDto>> getTodayOrderByBranch(@PathVariable Long branchId) throws Exception {
        return ResponseEntity.ok(orderService.getTodayOrdersByBranch(branchId));
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<OrderDto>> getOrderByCustomer(@PathVariable Long customerId) throws Exception {
        return ResponseEntity.ok(orderService.getOrdersByCustomerId(customerId));
    }

    @GetMapping("/recent/{branchId}")
    public ResponseEntity<List<OrderDto>> getRecentOrder(@PathVariable Long branchId) throws Exception {
        return ResponseEntity.ok(orderService.getTop5RecentOrdersByBranchId(branchId));
    }

}
