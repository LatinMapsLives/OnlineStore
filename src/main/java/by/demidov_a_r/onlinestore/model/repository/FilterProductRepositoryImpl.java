package by.demidov_a_r.onlinestore.model.repository;

import by.demidov_a_r.onlinestore.dto.ProductFilter;
import by.demidov_a_r.onlinestore.model.entity.Product;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static by.demidov_a_r.onlinestore.model.entity.QCategory.category;
import static by.demidov_a_r.onlinestore.model.entity.QProduct.product;


@RequiredArgsConstructor
public class FilterProductRepositoryImpl implements FIlterProductRepository{


    private final EntityManager entityManager;

    @Override
    public List<Product> findAllByFilter(ProductFilter filter) {
        Predicate predicate = QPredicates.builder().add(filter.name(), product.name::containsIgnoreCase)
                .add(filter.description(), product.description::containsIgnoreCase)
                .add(filter.category().getId(), category.id::eq).build();
        return new JPAQuery<Product>(entityManager).select(product)
                .from(product)
                .join(category)
                .where(predicate)
                .where(product.price.between(filter.bottomPrice(), filter.topPrice()))
                .fetch();
    }
}
