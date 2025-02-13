package by.demidov_a_r.onlinestore.model.entity;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.context.annotation.Lazy;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"cart", "orders"})
@ToString(exclude = {"cart", "orders"})
@Entity
@Table(name = "users", schema = "public")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String image;
    @Embedded
    private PersonalInfo personalInfo;
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Cart cart;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Order> orders;

    public void setCart(Cart cart){
        this.cart = cart;
        this.cart.setUser(this);
    }
}
