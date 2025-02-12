package by.demidov_a_r.onlinestore.dto;


import by.demidov_a_r.onlinestore.model.entity.Category;
import jakarta.persistence.*;
import lombok.Value;

import java.math.BigDecimal;

@Value
public class ProductReadDTO {

    Long id;
    String name;
    String description;
    BigDecimal price;
    Integer stockQuantity;
    CategoryReadDTO category;
}
