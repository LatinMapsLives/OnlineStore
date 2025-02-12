package by.demidov_a_r.onlinestore.mapper;

import by.demidov_a_r.onlinestore.dto.UserCreateEditDTO;
import by.demidov_a_r.onlinestore.model.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserCreateMapper implements Mapper<UserCreateEditDTO, User> {

    @Override
    public User mapTo(UserCreateEditDTO object) {
        User user = new User();
        user.setUsername(object.getUsername());
        user.setPassword(object.getPassword());
        user.setPersonalInfo(object.getPersonalInfo());
        user.setRole(object.getRole());
        user.setCart(object.getCart());
        return user;
    }
}
