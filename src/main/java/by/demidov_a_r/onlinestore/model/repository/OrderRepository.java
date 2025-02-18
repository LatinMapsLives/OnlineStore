package by.demidov_a_r.onlinestore.model.repository;

import by.demidov_a_r.onlinestore.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
