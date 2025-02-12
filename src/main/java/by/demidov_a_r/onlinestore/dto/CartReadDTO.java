package by.demidov_a_r.onlinestore.dto;

import by.demidov_a_r.onlinestore.model.entity.CartItem;

import java.util.List;

public record CartReadDTO(Long userID, List<CartItem> cartItemList) {
}
