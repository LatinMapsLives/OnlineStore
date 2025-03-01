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

    public Product reverseMap(ProductReadDTO productReadDTO) {
        return Product.builder()
                .id(productReadDTO.getId())
                .name(productReadDTO.getName())
                .description(productReadDTO.getDescription())
                .price(productReadDTO.getPrice())
                .stockQuantity(productReadDTO.getStockQuantity())
                .category(categoryReadMapper.reverseMap(productReadDTO.getCategory()))
                .build();
    }
}
