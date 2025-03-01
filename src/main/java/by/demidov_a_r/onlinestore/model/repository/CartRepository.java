package by.demidov_a_r.onlinestore.model.repository;

import by.demidov_a_r.onlinestore.model.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {

    @Query("select c from Cart c where c.user.id = :userId")
    Optional<Cart> findByUserId(Long userId);
}
