package by.demidov_a_r.onlinestore.dto;

import by.demidov_a_r.onlinestore.model.entity.OrderType;
import by.demidov_a_r.onlinestore.model.entity.Status;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record OrderReadDTO (Long id, LocalDateTime orderDate, Status status,
                            BigDecimal total, String address, OrderType orderType,
                            List<OrderItemDTO> orderItemList, PaymentReadDTO payment) {
}
