package by.demidov_a_r.onlinestore.unit.model.repository;

import by.demidov_a_r.onlinestore.OnlineStoreApplication;
import by.demidov_a_r.onlinestore.model.entity.*;
import by.demidov_a_r.onlinestore.model.repository.CategoryRepository;
import by.demidov_a_r.onlinestore.model.repository.ProductRepository;
import by.demidov_a_r.onlinestore.model.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@RequiredArgsConstructor
@Transactional
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@SpringBootTest(classes = OnlineStoreApplication.class)
class ProductRepositoryTest {

    private final ProductRepository productRepository;

    @Mock
    private final CategoryRepository categoryRepository;

    @BeforeEach
    void setUp(){
        Product product = Product.builder()
                .name("Телевизор SmartTV")
                .description("Телевизор для настоящих сигм")
                .price(new BigDecimal("35999.99"))
                .stockQuantity(150)
                .build();
        Category category = Category.builder()
                .name("Электроника")
                .build();
        product.setCategory(category);
        categoryRepository.save(category);
        productRepository.save(product);
    }

    @AfterEach
    void tearDown(){
        productRepository.deleteAll();
        categoryRepository.deleteAll();
    }

    @Test
    void findAllByNameIsLikeIgnoreCase() {
        Assertions.assertTrue(productRepository.findAllByNameIsLikeIgnoreCase("Зор").size() == 1);
    }

    @Test
    void findAllBy() {
    }

    @Test
    void findAllByCategory() {
    }

    @Test
    void testFindAllByCategory() {
    }
}