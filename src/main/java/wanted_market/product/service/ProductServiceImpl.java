package wanted_market.product.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wanted_market.member.domain.Member;
import wanted_market.member.repository.MemberRepository;
import wanted_market.order.domain.Order;
import wanted_market.order.dto.OrderDto;
import wanted_market.order.repository.OrderRepository;
import wanted_market.product.domain.Product;
import wanted_market.product.dto.ProductDto;
import wanted_market.product.repository.ProductRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final MemberRepository memberRepository;
    private final OrderRepository orderRepository;

    @Transactional(readOnly = true)
    public List<ProductDto> searchProductList() {
        return productRepository.findAll().stream()
                .map(p -> {
                    ProductDto productDto = new ProductDto();
                    productDto.setProductId(p.getProductId());
                    productDto.setProductPrice(p.getProductPrice());
                    productDto.setProductName(p.getProductName());
                    productDto.setMemberId(p.getMemberId());
                    return productDto;
                })
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ProductDto searchProductDetail(Long productId, Long memberId) {
        Optional<Product> product = productRepository.findByProductId(productId);

        return product.map(p -> {
            ProductDto productDto = new ProductDto();
            productDto.setProductId(p.getProductId());
            productDto.setProductPrice(p.getProductPrice());
            productDto.setProductName(p.getProductName());
            productDto.setMemberId(p.getMemberId());

            productDto.setOrderDtos(p.getOrders().stream()
                    .map(o -> {
                        if (Objects.equals(o.getOrdererId(), memberId) || Objects.equals(o.getSellerId(), memberId)) {
                            OrderDto orderDto = new OrderDto();
                            orderDto.setId(o.getId());
                            orderDto.setOrdererId(o.getOrdererId());
                            orderDto.setSellerId(o.getSellerId());
                            orderDto.setStatus(o.getStatus());
                            return orderDto;
                        }
                        return null;
                    })
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList()));
            return productDto;
        }).orElse(null);
    }

    @Transactional
    public boolean productRegister(ProductDto productDto) {
        Optional<Member> memberDto = memberRepository.findByMemberId(productDto.getMemberId());

        if (memberDto.isEmpty()) {
            return false;
        }

        Product product = Product.builder()
                .memberId(productDto.getMemberId())
                .productName(productDto.getProductName())
                .productPrice(productDto.getProductPrice())
                .build();

        productRepository.save(product);
        return true;
    }

    @Transactional
    public boolean productBuy(ProductDto productDto) {
        Optional<Member> member = memberRepository.findByMemberId(productDto.getMemberId());

        if (member.isEmpty()) {
            return false;
        }

        Optional<Product> product = productRepository.findByProductId(productDto.getProductId());

        if (product.isEmpty()) {
            return false;
        }

        orderRepository.save(Order.builder()
                .product(product.get())
                .ordererId(member.get().getMemberId())
                .sellerId(product.get().getMemberId())
                .status(1)
                .build());

        return true;
    }

    @Transactional
    public boolean productSellAgree(ProductDto productDto) {
        Optional<Member> memberDto = memberRepository.findByMemberId(productDto.getMemberId());

        if (memberDto.isEmpty()) {
            return false;
        }

        Optional<Product> product = productRepository.findByProductIdAndMemberId(productDto.getProductId(), productDto.getMemberId());

        if (product.isEmpty()) {
            return false;
        }

        product.ifPresent(m -> m.getOrders().forEach(o -> o.changeStatus(2)));
        return true;
    }
}
