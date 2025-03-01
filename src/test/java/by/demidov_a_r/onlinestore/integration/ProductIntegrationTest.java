package by.demidov_a_r.onlinestore.integration;

import by.demidov_a_r.onlinestore.OnlineStoreApplication;
import by.demidov_a_r.onlinestore.model.repository.ProductRepository;
import by.demidov_a_r.onlinestore.rest.ProductsRestController;
import by.demidov_a_r.onlinestore.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.web.servlet.MockMvc;


@RequiredArgsConstructor
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@SpringBootTest(classes = OnlineStoreApplication.class)
@AutoConfigureWebMvc
public class ProductIntegrationTest {

    private final MockMvc mockMvc;
    private final ProductsRestController productsRestController;
    private final ProductService productService;
    private final ProductRepository productRepository;


    @Test



}
