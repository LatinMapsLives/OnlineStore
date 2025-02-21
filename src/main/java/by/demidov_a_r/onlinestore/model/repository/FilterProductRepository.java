package by.demidov_a_r.onlinestore.model.repository;

import by.demidov_a_r.onlinestore.dto.ProductFilter;
import by.demidov_a_r.onlinestore.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FilterProductRepository {

    Page<Product> findAllFilter(ProductFilter filter, Pageable pageable);
}
