package by.demidov_a_r.onlinestore.mapper;

import by.demidov_a_r.onlinestore.dto.CartReadDTO;
import by.demidov_a_r.onlinestore.model.entity.Cart;
import org.springframework.stereotype.Component;

@Component
public class CartReadMapper implements Mapper<Cart, CartReadDTO> {

    @Override
    public CartReadDTO mapTo(Cart object) {
        return new CartReadDTO(object.getId(), object.getCartItems());
    }
}
