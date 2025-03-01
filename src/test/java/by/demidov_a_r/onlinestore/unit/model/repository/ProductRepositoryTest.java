package by.demidov_a_r.onlinestore.unit.model.repository;

import by.demidov_a_r.onlinestore.OnlineStoreApplication;
import by.demidov_a_r.onlinestore.dto.ProductFilter;
import by.demidov_a_r.onlinestore.model.entity.*;
import by.demidov_a_r.onlinestore.model.repository.CategoryRepository;
import by.demidov_a_r.onlinestore.model.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@ActiveProfiles("test")
@RequiredArgsConstructor
@Transactional
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@SpringBootTest(classes = OnlineStoreApplication.class)
class ProductRepositoryTest {


    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @BeforeEach
    void setUp(){
        Product product = Product.builder()
                .name("Телевизор SmartTV")
                .description("Телевизор для настоящих сигм")
                .price(new BigDecimal("35999"))
                .stockQuantity(150)
                .build();
        Category category = Category.builder()
                .name("Электроника")
                .build();
        Category category1 = Category.builder()
                .name("Телевизоры")
                .parentCategory(category)
                .build();
        product.setCategory(category1);

        categoryRepository.saveAndFlush(category);
        categoryRepository.saveAndFlush(category1);
        productRepository.saveAndFlush(product);
    }

    @AfterEach
    void tearDown(){
        productRepository.deleteAll();
        categoryRepository.deleteAll();
    }

    @Test
    void findAllByFilter(){
        ProductFilter filter = new ProductFilter("Smart", "сигм", new BigDecimal(10000),
                new BigDecimal(40000),
                1);

        Pageable pageable = PageRequest.of(0, 10);

        System.out.println(productRepository.findAll());

        Page<Product> products = productRepository.findAllFilter(filter, pageable);

        Assertions.assertEquals(1, products.getTotalElements());

        ProductFilter filter2 = new ProductFilter("Smart", "сигма", new BigDecimal(36000),
                new BigDecimal(40000),
                1);

        Page<Product> products2 = productRepository.findAllFilter(filter2, pageable);

        Assertions.assertEquals(0, products2.getTotalElements());

        ProductFilter filter3 = new ProductFilter("Smart", "сигм", new BigDecimal(10000),
                new BigDecimal(40000),
                2);

        Page<Product> products3 = productRepository.findAllFilter(filter3, pageable);
        Assertions.assertEquals(1, products3.getTotalElements());

        ProductFilter filter4 = new ProductFilter("Скибиди", "сигма", new BigDecimal(10000),
                new BigDecimal(40000),
                2);

        Page<Product> products4 = productRepository.findAllFilter(filter4, pageable);
        Assertions.assertEquals(0, products4.getTotalElements());

    }
}