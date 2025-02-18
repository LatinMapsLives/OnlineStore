package by.demidov_a_r.onlinestore.mapper;

import by.demidov_a_r.onlinestore.dto.UserCreateEditDTO;
import by.demidov_a_r.onlinestore.model.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;
import java.util.function.Predicate;


@Component
public class UserCreateEditMapper implements Mapper<UserCreateEditDTO, User> {

    @Override
    public User mapTo(UserCreateEditDTO object) {
        return User.builder()
                .username(object.username())
                .password(object.password())
                .personalInfo(object.personalInfo())
                .role(object.role())
                .build();
    }

    public User copy(UserCreateEditDTO from, User user) {
        user.setUsername(from.username());
        user.setPassword(from.password());
        user.setPersonalInfo(from.personalInfo());
        user.setRole(from.role());

        return user;
    }
}
