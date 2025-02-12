package by.demidov_a_r.onlinestore.mapper;

import by.demidov_a_r.onlinestore.dto.UserReadDTO;
import by.demidov_a_r.onlinestore.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class UserReadMapper implements Mapper<User, UserReadDTO>{

    private final CartReadMapper cartReadMapper;

    @Override
    public UserReadDTO mapTo(User object) {
        return new UserReadDTO(
          object.getId(), object.getUsername(), object.getPassword(),
          object.getPersonalInfo(), object.getRole(), cartReadMapper.mapTo(object.getCart()),
          object.getOrders()
        );
    }
}
