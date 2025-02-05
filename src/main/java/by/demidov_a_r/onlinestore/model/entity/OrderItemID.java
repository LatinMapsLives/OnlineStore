package by.demidov_a_r.onlinestore.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class OrderItemID {

    @Column(name = "order_id")
    private Long orderId;
    @Column(name = "product_id")
    private Long productId;

}
