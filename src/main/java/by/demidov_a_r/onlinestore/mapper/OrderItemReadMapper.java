package by.demidov_a_r.onlinestore.mapper;

import by.demidov_a_r.onlinestore.dto.OrderItemDTO;
import by.demidov_a_r.onlinestore.model.entity.OrderItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class OrderItemReadMapper implements Mapper<OrderItem, OrderItemDTO> {

    private final ProductReadMapper productReadMapper;

    @Override
    public OrderItemDTO mapTo(OrderItem object) {
        return new OrderItemDTO(object.getId(), object.getQuantity(), object.getPrice(),
                productReadMapper.mapTo(object.getProduct()));
    }
}
