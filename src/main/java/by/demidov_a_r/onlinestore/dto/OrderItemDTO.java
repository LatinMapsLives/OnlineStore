package by.demidov_a_r.onlinestore.dto;


import by.demidov_a_r.onlinestore.model.entity.Order;
import by.demidov_a_r.onlinestore.model.entity.OrderItemID;
import by.demidov_a_r.onlinestore.model.entity.Product;
import jakarta.persistence.*;
import lombok.Value;

import java.math.BigDecimal;

@Value
public class OrderItemDTO{

    OrderItemID id;
    Integer quantity;
    BigDecimal price;
    ProductReadDTO product;
}
