package by.demidov_a_r.onlinestore.service;

import by.demidov_a_r.onlinestore.dto.CartItemReadDTO;
import by.demidov_a_r.onlinestore.mapper.CartItemReadMapper;
import by.demidov_a_r.onlinestore.model.entity.CartItem;
import by.demidov_a_r.onlinestore.model.entity.Product;
import by.demidov_a_r.onlinestore.model.repository.CartItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CartItemService {

    private final CartItemRepository cartItemRepository;
    private final CartItemReadMapper cartItemReadMapper;


    public CartItem createCartItem(Product product) {
        return CartItem.builder().product(product).build();
    }

    public List<CartItemReadDTO> findByCartId(Long cartId){
        return cartItemRepository.findByCartId(cartId).stream().map(cartItemReadMapper::mapTo).toList();
    }
}
