package by.demidov_a_r.onlinestore.service;


import by.demidov_a_r.onlinestore.dto.CartReadDTO;
import by.demidov_a_r.onlinestore.mapper.CartReadMapper;
import by.demidov_a_r.onlinestore.model.entity.Cart;
import by.demidov_a_r.onlinestore.model.entity.CartItem;
import by.demidov_a_r.onlinestore.model.entity.Product;
import by.demidov_a_r.onlinestore.model.entity.User;
import by.demidov_a_r.onlinestore.model.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CartService {

    private final CartRepository cartRepository;
    private final CartReadMapper cartReadMapper;

    @Transactional
    public void addToCart(User user, CartItem cartItem){
        Cart cart = cartRepository.findById(user.getId()).orElse(Cart.builder().user(user).build());
        cart.addCartItem(cartItem);
        cartRepository.saveAndFlush(cart);
    }

    public Optional<CartReadDTO> findByUserId(Long userId){
        return cartRepository.findByUserId(userId).map(cartReadMapper::mapTo);
    }

}
