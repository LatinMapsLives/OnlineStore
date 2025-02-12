package by.demidov_a_r.onlinestore.mapper;

import by.demidov_a_r.onlinestore.dto.PaymentReadDTO;
import by.demidov_a_r.onlinestore.model.entity.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentReadMapper implements Mapper<Payment, PaymentReadDTO> {

    @Override
    public PaymentReadDTO mapTo(Payment object) {
        return new PaymentReadDTO(object.getId(), object.getDate(), object.getTotal(), object.getMethod());
    }
}
