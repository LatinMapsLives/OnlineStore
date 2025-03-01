package by.demidov_a_r.onlinestore.rest;


import by.demidov_a_r.onlinestore.dto.PageResponse;
import by.demidov_a_r.onlinestore.dto.UserCreateEditDTO;
import by.demidov_a_r.onlinestore.dto.UserFilter;
import by.demidov_a_r.onlinestore.dto.UserReadDTO;
import by.demidov_a_r.onlinestore.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class UserAdminRestController {

    private final UserService userService;

    @GetMapping("/users")
    public PageResponse<UserReadDTO> findAllUsers(UserFilter filter, Pageable pageable) {
        Page<UserReadDTO> page = userService.findAll(filter, pageable);
        return PageResponse.of(page);
    }

    @GetMapping("/users/{id}")
    public UserReadDTO findUserById(@PathVariable @RequestBody Long id) {
        return userService.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }


    @PutMapping("/users/{id}")
    public UserReadDTO updateUser(@PathVariable("id") Long id, @RequestBody @Validated UserCreateEditDTO userCreateEditDTO) {
        return userService.update(id, userCreateEditDTO).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.BAD_REQUEST));
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.delete(id);
    }
}
