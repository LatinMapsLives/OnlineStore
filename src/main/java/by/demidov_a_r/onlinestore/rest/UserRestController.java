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
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ADMIN')")
public class UserRestController {

    private final UserService userService;

    @GetMapping
    public PageResponse<UserReadDTO> findAllUsers(UserFilter filter, Pageable pageable) {
        Page<UserReadDTO> page = userService.findAll(filter, pageable);
        return PageResponse.of(page);
    }

    @PutMapping(value = "/registration")
    public UserReadDTO registration(@RequestBody @Validated UserCreateEditDTO user) {
        return userService.register(user).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
    }


    @GetMapping("/{id}")
    public UserReadDTO findById(@PathVariable @RequestBody Long id) {
        return userService.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }


    @PutMapping("/{id}")
    public UserReadDTO updateUser(@PathVariable("id") Long id, @Validated @RequestBody UserCreateEditDTO userCreateEditDTO) {
        return userService.update(id, userCreateEditDTO).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.delete(id);
    }
}
