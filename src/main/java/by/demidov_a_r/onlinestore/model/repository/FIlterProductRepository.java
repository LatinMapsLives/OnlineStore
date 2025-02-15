package by.demidov_a_r.onlinestore.model.repository;

import by.demidov_a_r.onlinestore.dto.ProductFilter;
import by.demidov_a_r.onlinestore.model.entity.Product;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FIlterProductRepository {

    List<Product> findAllByFilter(ProductFilter filter);
}
