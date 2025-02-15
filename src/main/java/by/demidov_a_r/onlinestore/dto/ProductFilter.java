package by.demidov_a_r.onlinestore.dto;

import by.demidov_a_r.onlinestore.model.entity.Category;

import java.math.BigDecimal;

public record ProductFilter(String name, String description,
                            BigDecimal bottomPrice, BigDecimal topPrice,
                            Category category) {
}
