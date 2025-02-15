package by.demidov_a_r.onlinestore.service;


import by.demidov_a_r.onlinestore.dto.ProductCreateEditDTO;
import by.demidov_a_r.onlinestore.dto.ProductFilter;
import by.demidov_a_r.onlinestore.dto.ProductReadDTO;
import by.demidov_a_r.onlinestore.mapper.ProductCreateEditMapper;
import by.demidov_a_r.onlinestore.mapper.ProductReadMapper;
import by.demidov_a_r.onlinestore.model.entity.Product;
import by.demidov_a_r.onlinestore.model.repository.ProductRepository;
import by.demidov_a_r.onlinestore.model.repository.QPredicates;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static by.demidov_a_r.onlinestore.model.entity.QProduct.product;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductCreateEditMapper productCreateEditMapper;
    private final ProductReadMapper productReadMapper;

    public Page<Product> findAllByFilter(ProductFilter filter,
                                         Pageable pageable) {
        Predicate predicate = QPredicates.builder()
                .add(filter.name(), product.name::containsIgnoreCase)
                .add(filter.description(), product.description::containsIgnoreCase)
                .add(filter.category().getId(), product.category.id::eq)
                .add(filter.bottomPrice(), product.price::goe)
                .add(filter.topPrice(), product.price::loe)
                .build();
        return productRepository.findAll(predicate, pageable);
    }

    @Transactional
    public Optional<ProductReadDTO> create(ProductCreateEditDTO productCreateEditDTO){
        return Optional.of(productRepository.saveAndFlush(productCreateEditMapper.mapTo(productCreateEditDTO)))
                .map(productReadMapper::mapTo);
    }

    public Optional<ProductReadDTO> update(Long id, ProductCreateEditDTO productCreateEditDTO) {
        return null;
    }
}
