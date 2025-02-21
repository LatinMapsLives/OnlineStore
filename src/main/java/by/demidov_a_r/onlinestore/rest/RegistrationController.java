package by.demidov_a_r.onlinestore.rest;


import by.demidov_a_r.onlinestore.dto.UserCreateEditDTO;
import by.demidov_a_r.onlinestore.dto.UserReadDTO;
import by.demidov_a_r.onlinestore.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
public class RegistrationController {

    private final UserService userService;

    @PutMapping(value = "/registration")
    public UserReadDTO registration(@RequestBody @Validated UserCreateEditDTO user) {
        return userService.register(user).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
    }

}
