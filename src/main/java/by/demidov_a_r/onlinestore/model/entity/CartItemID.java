package by.demidov_a_r.onlinestore.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;


@Embeddable
public class CartItemID implements Serializable {

    @Column(name = "product_id")
    private Integer productId;

    @Column(name = "cart_id")
    private Long cartId;

}
