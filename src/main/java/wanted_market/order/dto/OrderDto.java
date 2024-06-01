package wanted_market.order.dto;

import lombok.Getter;
import lombok.Setter;
import wanted_market.product.dto.ProductDto;

@Getter
@Setter
public class OrderDto {

    private Long id;
    private Long ordererId;
    private Long sellerId;
    private Integer status;
    private ProductDto product;

}
