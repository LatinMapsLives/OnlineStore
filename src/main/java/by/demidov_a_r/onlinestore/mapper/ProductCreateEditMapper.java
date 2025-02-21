package by.demidov_a_r.onlinestore.mapper;

import by.demidov_a_r.onlinestore.dto.ProductCreateEditDTO;
import by.demidov_a_r.onlinestore.model.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductCreateEditMapper implements Mapper<ProductCreateEditDTO, Product> {

    private final CategoryReadMapper categoryReadMapper;

    @Override
    public Product mapTo(ProductCreateEditDTO object) {
        return Product.builder()
                .name(object.name())
                .description(object.description())
                .category(
                        categoryReadMapper.reverseMap(object.category()))
                .stockQuantity(object.stockQuantity())
                .build();
    }

    public Product copy(ProductCreateEditDTO object, Product subject) {
        subject.setName(object.name());
        subject.setDescription(object.description());
        subject.setPrice(object.price());
        subject.setCategory(categoryReadMapper.reverseMap(object.category()));
        subject.setStockQuantity(object.stockQuantity());
        return subject;
    }
}
