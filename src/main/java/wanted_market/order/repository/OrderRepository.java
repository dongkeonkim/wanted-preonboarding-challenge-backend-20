package wanted_market.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import wanted_market.order.domain.Order;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("select o from Order o where o.ordererId = :ordererId and (o.status = 1 or o.status = 2)")
    List<Order> findAllByMemberOrders(@Param("ordererId") Long ordererId);

}
