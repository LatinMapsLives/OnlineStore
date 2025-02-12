package by.demidov_a_r.onlinestore.dto;

import java.time.LocalDate;

public record UserFilter(String firstName,
                         String lastName,
                         String email) {
}
