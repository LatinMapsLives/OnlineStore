package by.demidov_a_r.onlinestore.dto;

import java.math.BigDecimal;

public record CartItemReadDTO(Long id, ProductReadDTO product, Integer quantity, BigDecimal price) {
}
