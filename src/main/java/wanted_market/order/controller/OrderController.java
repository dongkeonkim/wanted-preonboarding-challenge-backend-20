package wanted_market.order.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wanted_market.order.dto.OrderDto;
import wanted_market.order.service.OrderService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    /**
     * 회원의 거래 내역 조회 (거래 완료, 예약중)
     */
    @GetMapping("/{memberId}")
    public ResponseEntity<List<OrderDto>> getOrders(@PathVariable Long memberId) {
        return ResponseEntity.ok(orderService.getOrders(memberId));
    }
}
