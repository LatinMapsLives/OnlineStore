package by.demidov_a_r.onlinestore.model.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Embeddable
public class PersonalInfo {

    @Column(name = "first_name", nullable = false)
    @NotBlank
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    private String patronymic;
    @Column(nullable = false, unique = true)
    @Digits(integer = 8, fraction = 2)
    private String phone;
    @Column(nullable = false, unique = true)
    @Email
    private String email;

}
