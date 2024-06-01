package wanted_market.order.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import wanted_market.product.domain.Product;

@Entity
@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long ordererId;
    private Long sellerId;
    private Integer status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Builder
    public Order(Long id, Long ordererId, Long sellerId, Integer status, Product product) {
        this.id = id;
        this.ordererId = ordererId;
        this.sellerId = sellerId;
        this.status = status;
        this.product = product;
    }

    public void changeStatus(Integer status) {
        this.status = status;
    }

}
