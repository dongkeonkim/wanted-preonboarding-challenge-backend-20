package wanted_market.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wanted_market.product.domain.Product;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByProductId(Long id);

    Optional<Product> findByProductIdAndMemberId(Long productId, Long memberId);

}
