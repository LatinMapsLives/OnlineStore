package by.demidov_a_r.onlinestore.model.repository;

import by.demidov_a_r.onlinestore.model.entity.Category;
import by.demidov_a_r.onlinestore.model.entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByNameIsLikeIgnoreCase(String name);
    List<Product> findAllBy(Pageable pageable);
    List<Product> findAllByCategory(Category category);
    List<Product> findAllByCategory(Category category, Pageable pageable);
}
