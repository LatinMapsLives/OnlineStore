package by.demidov_a_r.onlinestore.rest;

import by.demidov_a_r.onlinestore.dto.PageResponse;
import by.demidov_a_r.onlinestore.dto.ProductCreateEditDTO;
import by.demidov_a_r.onlinestore.dto.ProductFilter;
import by.demidov_a_r.onlinestore.dto.ProductReadDTO;
import by.demidov_a_r.onlinestore.model.entity.CartItem;
import by.demidov_a_r.onlinestore.model.entity.Product;
import by.demidov_a_r.onlinestore.model.entity.User;
import by.demidov_a_r.onlinestore.service.CartItemService;
import by.demidov_a_r.onlinestore.service.CartService;
import by.demidov_a_r.onlinestore.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductsRestController {

    private final ProductService productService;
    private final CartService cartService;
    private final CartItemService cartItemService;

    @PostMapping
    public PageResponse<ProductReadDTO> findProductsByFilter(@RequestBody ProductFilter filter,
                                                             @RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber,
                                                             @RequestParam(name = "pageSize", defaultValue = "20") Integer pageSize) {
        return PageResponse.of(productService.findAllByFilter(filter, PageRequest.of(pageNumber, pageSize)));
    }

    @GetMapping("/{id}")
    public ProductReadDTO getProductInfo(@PathVariable Long id){
        return productService.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
    }

    @PostMapping("/{id}/add")
    public void addProductToCart(Authentication authentication, @PathVariable Long id, @RequestAttribute Integer quantity){
        if (authentication.isAuthenticated()){
            User user = (User) authentication.getPrincipal();
            Product product = productService.findById(id).map(productService::dtoToEntity).orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
            );
            CartItem cartItem = cartItemService.createCartItem(product);
            cartService.addToCart(user, cartItem);
        } else{
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }


    @GetMapping
    public PageResponse<ProductReadDTO> findAll(ProductFilter filter, Pageable pageable){
        return PageResponse.of(productService.findAllByFilter(filter, pageable));
    }
}
