package by.demidov_a_r.onlinestore.rest;


import by.demidov_a_r.onlinestore.dto.PageResponse;
import by.demidov_a_r.onlinestore.dto.ProductCreateEditDTO;
import by.demidov_a_r.onlinestore.dto.ProductFilter;
import by.demidov_a_r.onlinestore.dto.ProductReadDTO;
import by.demidov_a_r.onlinestore.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class ProductAdminRestController {

    private final ProductService productService;

    @GetMapping("/products")
    public PageResponse<ProductReadDTO> findAllProducts(ProductFilter filter, Pageable pageable) {
        return PageResponse.of(productService.findAllByFilter(filter, pageable));
    }

    @GetMapping("/products/{id}")
    public Optional<ProductReadDTO> findProductById(@PathVariable Long id){
        return productService.findById(id);
    }

    @PutMapping("/products/create")
    public Optional<ProductReadDTO> createProduct(@RequestBody @Validated ProductCreateEditDTO productCreateEditDTO) {
        return productService.create(productCreateEditDTO);
    }

    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.delete(id);
    }

    @PutMapping("/products/{id}")
    public Optional<ProductReadDTO> updateProduct(@PathVariable Long id,
                                                  @RequestBody @Validated ProductCreateEditDTO productCreateEditDTO) {
        return productService.update(id, productCreateEditDTO);
    }
}
