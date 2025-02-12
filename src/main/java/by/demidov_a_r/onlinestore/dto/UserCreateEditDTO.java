package by.demidov_a_r.onlinestore.dto;


import by.demidov_a_r.onlinestore.model.entity.Cart;
import by.demidov_a_r.onlinestore.model.entity.PersonalInfo;
import by.demidov_a_r.onlinestore.model.entity.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Value;

@Value
public class UserCreateEditDTO {
    @NotBlank
    @Size(min = 3, max = 30)
    String username;
    @NotBlank
    @Size(min = 3, max = 30)
    String password;
    PersonalInfo personalInfo;
    Role role;
    Cart cart;
}
