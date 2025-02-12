package by.demidov_a_r.onlinestore.mapper;

import by.demidov_a_r.onlinestore.dto.UserCreateEditDTO;
import by.demidov_a_r.onlinestore.model.entity.User;
import org.springframework.stereotype.Component;


@Component
public class UserCreateEditMapper implements Mapper<UserCreateEditDTO, User> {

    @Override
    public User mapTo(UserCreateEditDTO object) {
        return User.builder()
                .username(object.getUsername())
                .password(object.getPassword())
                .personalInfo(object.getPersonalInfo())
                .role(object.getRole())
                .cart(object.getCart())
                .build();
    }
}
