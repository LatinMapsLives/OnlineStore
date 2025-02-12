package by.demidov_a_r.onlinestore.dto;


import by.demidov_a_r.onlinestore.model.entity.PaymentMethod;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Value
public class PaymentReadDTO {
    Long id;
    LocalDateTime date;
    BigDecimal total;
    PaymentMethod method;

}
