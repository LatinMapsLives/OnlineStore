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
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserRestController {

    private final UserService userService;

    @GetMapping
    public PageResponse<UserReadDTO> onPageLoad(UserFilter filter, Pageable pageable) {
        Page<UserReadDTO> page = userService.findAll(filter, pageable);
        return PageResponse.of(page);
    }



    @GetMapping("/{id}")
    public UserReadDTO findById(@PathVariable @RequestBody Long id) {
        return userService.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }


    @PutMapping("/{id}")
    public Optional<UserReadDTO> update(@PathVariable("id") Long id, @Validated @RequestBody UserCreateEditDTO userCreateEditDTO) {
        return userService.update(id, userCreateEditDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }
}
