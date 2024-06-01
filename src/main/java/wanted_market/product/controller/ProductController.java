package wanted_market.product.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wanted_market.product.dto.ProductDto;
import wanted_market.product.service.ProductService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    /**
     * 제품 목록 조회
     */
    @GetMapping("")
    public ResponseEntity<List<ProductDto>> productList() {
        return ResponseEntity.ok(productService.searchProductList());
    }

    /**
     * 제품 상세 조회
     * 요청한 회원이 구매자 혹은 판매자라면 당사자간의 거래 내역도 함께 조회
     */
    @GetMapping("/{productId}")
    public ResponseEntity<ProductDto> productDetail(@PathVariable Long productId, @RequestParam(value = "memberId", required = false) Long memberId) {
        return ResponseEntity.ok(productService.searchProductDetail(productId, memberId));
    }

    /**
     * 제품 등록
     */
    @PostMapping("/register")
    public ResponseEntity<Boolean> productRegister(@RequestBody ProductDto productDto) {
        return ResponseEntity.ok(productService.productRegister(productDto));

    }

    /**
     * 제품 구매
     */
    @PostMapping("/buy")
    public ResponseEntity<Boolean> productBuy(@RequestBody ProductDto productDto) {
        return ResponseEntity.ok(productService.productBuy(productDto));
    }

    /**
     * 판매 승인
     */
    @PostMapping("/aprvSale")
    public ResponseEntity<Boolean> productSellAgree(@RequestBody ProductDto productDto) {
        return ResponseEntity.ok(productService.productSellAgree(productDto));
    }

}
