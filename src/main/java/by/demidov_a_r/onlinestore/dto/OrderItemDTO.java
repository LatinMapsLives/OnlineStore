package by.demidov_a_r.onlinestore.dto;


import lombok.Value;

import java.math.BigDecimal;


public record OrderItemDTO(
        Long id,
        Integer quantity,
        BigDecimal price,
        ProductReadDTO product
        ){
}
