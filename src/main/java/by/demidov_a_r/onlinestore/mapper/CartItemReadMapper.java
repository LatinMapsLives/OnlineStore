package by.demidov_a_r.onlinestore.mapper;

import by.demidov_a_r.onlinestore.dto.CartItemReadDTO;
import by.demidov_a_r.onlinestore.model.entity.CartItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class CartItemReadMapper implements Mapper<CartItem, CartItemReadDTO> {

    private final ProductReadMapper productReadMapper;

    @Override
    public CartItemReadDTO mapTo(CartItem object) {
        return new CartItemReadDTO(object.getId(), productReadMapper.mapTo(object.getProduct()),
                object.getQuantity(), object.getPrice());
    }
}
