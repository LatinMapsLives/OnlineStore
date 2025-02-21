package by.demidov_a_r.onlinestore.model.repository;

import by.demidov_a_r.onlinestore.dto.ProductFilter;
import by.demidov_a_r.onlinestore.model.entity.Product;
import by.demidov_a_r.onlinestore.model.entity.QCategory;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static by.demidov_a_r.onlinestore.model.entity.QProduct.product;


@RequiredArgsConstructor
public class FilterProductRepositoryImpl implements FilterProductRepository {


    private final EntityManager entityManager;

    @Override
    public Page<Product> findAllFilter(ProductFilter filter, Pageable pageable) {

        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);


        Predicate predicate = QPredicates.builder()
                .add(filter.name() == null ? null : "%" + filter.name() + "%", product.name::likeIgnoreCase)
                .add(filter.description() == null ? null : "%" + filter.description() + "%", product.description::likeIgnoreCase)
                .add(filter.bottomPrice(), product.price::goe)
                .add(filter.topPrice(), product.price::loe)
                .add(findSubCategories(filter.categoryId(), queryFactory).isEmpty()
                        ? Collections.singletonList(filter.categoryId())
                        : findSubCategories(filter.categoryId(), queryFactory),
                        product.category.id::in)
                .build();

        List<Product> resultList = queryFactory
                .select(product)
                .from(product)
                .where(predicate)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        return new PageImpl<>(resultList, pageable, resultList.size());
    }

    private List<Integer> findSubCategories(Integer parentCategoryId, JPAQueryFactory queryFactory) {

        QCategory category = QCategory.category;
        List<Integer> subCategoriesIds = queryFactory
                .select(category.id)
                .from(category)
                .where(category.parentCategory.id.eq(parentCategoryId))
                .fetch();

        for (Integer subCategoryId : subCategoriesIds) {
            subCategoriesIds.addAll(findSubCategories(subCategoryId, queryFactory));
        }
        return subCategoriesIds;
    }
}
