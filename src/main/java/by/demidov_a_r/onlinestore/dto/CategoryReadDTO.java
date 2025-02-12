package by.demidov_a_r.onlinestore.dto;

import by.demidov_a_r.onlinestore.mapper.Mapper;
import by.demidov_a_r.onlinestore.model.entity.Category;
import jakarta.persistence.*;
import lombok.Value;


@Value
public class CategoryReadDTO {
    Integer id;
    CategoryReadDTO parentCategory;
    String name;
}
