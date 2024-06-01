package wanted_market.product.service;

import wanted_market.product.dto.ProductDto;

import java.util.List;

public interface ProductService {

    List<ProductDto> searchProductList();

    ProductDto searchProductDetail(Long productId, Long memberId);

    boolean productRegister(ProductDto productDto);

    boolean productBuy(ProductDto productDto);

    boolean productSellAgree(ProductDto productDto);

}
