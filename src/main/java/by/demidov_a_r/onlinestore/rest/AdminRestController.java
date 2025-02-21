package by.demidov_a_r.onlinestore.rest;


import by.demidov_a_r.onlinestore.dto.*;
import by.demidov_a_r.onlinestore.service.ProductService;
import by.demidov_a_r.onlinestore.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminRestController {

    private final UserService userService;
    private final ProductService productService;

    // USERS

    @GetMapping("/users")
    public PageResponse<UserReadDTO> findAllUsers(UserFilter filter, Pageable pageable) {
        Page<UserReadDTO> page = userService.findAll(filter, pageable);
        return PageResponse.of(page);
    }

    @GetMapping("/users/{id}")
    public UserReadDTO findUserById(@PathVariable @RequestBody Long id) {
        return userService.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }


    @PutMapping("/users/{id}")
    public UserReadDTO updateUser(@PathVariable("id") Long id, @RequestBody @Validated UserCreateEditDTO userCreateEditDTO) {
        return userService.update(id, userCreateEditDTO).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.BAD_REQUEST));
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.delete(id);
    }

    // PRODUCTS

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
