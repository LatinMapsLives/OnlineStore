package by.demidov_a_r.onlinestore.mapper;

import by.demidov_a_r.onlinestore.dto.ProductReadDTO;
import by.demidov_a_r.onlinestore.model.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductReadMapper implements Mapper<Product, ProductReadDTO> {

    private final CategoryReadMapper categoryReadMapper;

    @Override
    public ProductReadDTO mapTo(Product object) {
        return new ProductReadDTO(object.getId(), object.getName(), object.getDescription(),
                object.getPrice(), object.getStockQuantity(), categoryReadMapper.mapTo(object.getCategory()));
    }
}
