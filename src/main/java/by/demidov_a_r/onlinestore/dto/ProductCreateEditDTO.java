package by.demidov_a_r.onlinestore.dto;

import java.math.BigDecimal;

public record ProductCreateEditDTO(
        String name,
        String description,
        BigDecimal price,
        Integer stockQuantity,
        CategoryReadDTO category
) {}
