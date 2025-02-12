package by.demidov_a_r.onlinestore.model.repository;

import by.demidov_a_r.onlinestore.dto.UserFilter;
import by.demidov_a_r.onlinestore.model.entity.User;

import java.util.List;

public interface FilterUserRepository {

    List<User> findAllByFilter(UserFilter filter);
}
