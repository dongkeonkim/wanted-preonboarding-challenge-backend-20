package wanted_market.product.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import wanted_market.order.domain.Order;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    private Long memberId;          // 판매자 아이디

    private String productName;     // 제품명

    private Integer productPrice;   // 제품 가격

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<Order> orders = new ArrayList<>();

    @Builder
    public Product(Long productId, Long memberId, String productName, Integer productPrice, List<Order> orders) {
        this.productId = productId;
        this.memberId = memberId;
        this.productName = productName;
        this.productPrice = productPrice;this.orders = orders;
    }

}
