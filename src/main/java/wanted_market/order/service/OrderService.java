package wanted_market.order.service;

import wanted_market.order.dto.OrderDto;

import java.util.List;

public interface OrderService {

    List<OrderDto> getOrders(Long memberId);

}
