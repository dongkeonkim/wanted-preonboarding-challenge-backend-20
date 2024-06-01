package wanted_market.product.dto;

import lombok.Getter;
import lombok.Setter;
import wanted_market.order.dto.OrderDto;

import java.util.List;

@Getter
@Setter
public class ProductDto {

    private Long productId;
    private Long memberId;
    private String productName;
    private Integer productPrice;
    private List<OrderDto> orderDtos;

}
