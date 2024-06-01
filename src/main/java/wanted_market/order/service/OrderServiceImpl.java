package wanted_market.order.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wanted_market.order.domain.Order;
import wanted_market.order.dto.OrderDto;
import wanted_market.order.repository.OrderRepository;
import wanted_market.product.dto.ProductDto;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Transactional(readOnly = true)
    public List<OrderDto> getOrders(Long memberId) {
        List<Order> orders = orderRepository.findAllByMemberOrders(memberId);

        return orders.stream()
                .map(o -> {
                    OrderDto orderDto = new OrderDto();
                    orderDto.setId(o.getId());
                    orderDto.setOrdererId(o.getOrdererId());
                    orderDto.setSellerId(o.getSellerId());

                    ProductDto productDto = new ProductDto();
                    productDto.setProductId(o.getProduct().getProductId());
                    productDto.setMemberId(o.getProduct().getMemberId());
                    productDto.setProductName(o.getProduct().getProductName());
                    productDto.setProductPrice(o.getProduct().getProductPrice());

                    orderDto.setProduct(productDto);
                    return orderDto;
                }).toList();
    }

}
