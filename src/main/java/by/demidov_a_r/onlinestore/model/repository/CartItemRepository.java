package by.demidov_a_r.onlinestore.model.repository;

import by.demidov_a_r.onlinestore.model.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
