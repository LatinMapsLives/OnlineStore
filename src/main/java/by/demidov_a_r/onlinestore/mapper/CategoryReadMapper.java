package by.demidov_a_r.onlinestore.mapper;


import by.demidov_a_r.onlinestore.dto.CategoryReadDTO;
import by.demidov_a_r.onlinestore.model.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryReadMapper implements Mapper<Category, CategoryReadDTO> {

    @Override
    public CategoryReadDTO mapTo(Category object) {
        if (object == null){
            return null;
        }
        return new CategoryReadDTO(object.getId(), mapTo(object.getParentCategory()), object.getName());
    }
}
