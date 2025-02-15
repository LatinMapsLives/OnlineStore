package by.demidov_a_r.onlinestore.model.repository;

import by.demidov_a_r.onlinestore.dto.ProductFilter;
import by.demidov_a_r.onlinestore.model.entity.Category;
import by.demidov_a_r.onlinestore.model.entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long>, QuerydslPredicateExecutor<Product> {
}
