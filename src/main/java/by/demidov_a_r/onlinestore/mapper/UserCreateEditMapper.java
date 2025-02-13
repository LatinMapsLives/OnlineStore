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
                .username(object.getUsername())
                .password(object.getPassword())
                .personalInfo(object.getPersonalInfo())
                .role(object.getRole())
                .build();
    }

    public User copy(UserCreateEditDTO from, User user) {
        user.setUsername(from.getUsername());
        user.setPassword(from.getPassword());
        user.setPersonalInfo(from.getPersonalInfo());
        user.setRole(from.getRole());

        Optional.ofNullable(from.getImage()).filter(Predicate.not(MultipartFile::isEmpty))
                .ifPresent(image -> user.setImage(image.getOriginalFilename()));

        return user;
    }
}
