package by.demidov_a_r.onlinestore.rest;

import by.demidov_a_r.onlinestore.dto.PageResponse;
import by.demidov_a_r.onlinestore.dto.ProductCreateEditDTO;
import by.demidov_a_r.onlinestore.dto.ProductFilter;
import by.demidov_a_r.onlinestore.dto.ProductReadDTO;
import by.demidov_a_r.onlinestore.model.entity.Product;
import by.demidov_a_r.onlinestore.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductsRestController {

    private final ProductService productService;


    @PutMapping
    public ProductReadDTO createProduct(@RequestBody ProductCreateEditDTO productCreateEditDTO) {
       return productService.create(productCreateEditDTO).orElseThrow(() ->
               new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR));
    }


    public ProductReadDTO updateProduct(Long id, ProductCreateEditDTO productCreateEditDTO) {
        return productService.update(id, productCreateEditDTO).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @GetMapping
    public PageResponse<Product> findAll(ProductFilter filter, Pageable pageable){
        return PageResponse.of(productService.findAllByFilter(filter, pageable));
    }
}
