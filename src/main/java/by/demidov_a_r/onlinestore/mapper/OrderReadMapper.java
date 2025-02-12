package by.demidov_a_r.onlinestore.mapper;

import by.demidov_a_r.onlinestore.dto.OrderReadDTO;
import by.demidov_a_r.onlinestore.model.entity.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class OrderReadMapper implements Mapper<Order, OrderReadDTO> {

    private final OrderItemReadMapper orderItemReadMapper;
    private final PaymentReadMapper paymentReadMapper;

    @Override
    public OrderReadDTO mapTo(Order object) {
        return new OrderReadDTO(object.getId(), object.getOrderDate(),
                object.getStatus(), object.getTotal(), object.getAddress(), object.getType(),
                object.getOrderItems().stream().map(orderItemReadMapper::mapTo).toList(),
                paymentReadMapper.mapTo(object.getPayment())
                );
    }
}
