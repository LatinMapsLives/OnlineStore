package by.demidov_a_r.onlinestore.dto;

import by.demidov_a_r.onlinestore.model.entity.Order;
import by.demidov_a_r.onlinestore.model.entity.PersonalInfo;
import by.demidov_a_r.onlinestore.model.entity.Role;

import java.util.List;

public record UserReadDTO(Long id, String username, String password,
                          PersonalInfo personalInfo, Role role,
                          CartReadDTO cart, List<Order> orderList) {
}
