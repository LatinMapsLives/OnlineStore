package by.demidov_a_r.onlinestore.dto;

import by.demidov_a_r.onlinestore.model.entity.PersonalInfo;
import by.demidov_a_r.onlinestore.model.entity.Role;

public record UserReadRawDTO(Long id, String username, String password,
                             PersonalInfo personalInfo, Role role) {
}
