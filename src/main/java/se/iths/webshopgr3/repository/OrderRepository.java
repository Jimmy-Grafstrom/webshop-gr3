package se.iths.webshopgr3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.iths.webshopgr3.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
