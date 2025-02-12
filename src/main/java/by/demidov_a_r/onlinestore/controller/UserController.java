package by.demidov_a_r.onlinestore.controller;


import by.demidov_a_r.onlinestore.dto.PageResponse;
import by.demidov_a_r.onlinestore.dto.UserFilter;
import by.demidov_a_r.onlinestore.dto.UserReadDTO;
import by.demidov_a_r.onlinestore.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public String onPageLoad(Model model, UserFilter filter, Pageable pageable) {
        Page<UserReadDTO> page = userService.findAll(filter, pageable);
        model.addAttribute("users", PageResponse.of(page));
        model.addAttribute("filter", filter);
        return "/users";
    }



    @GetMapping("/{id}")
    public String findById(@PathVariable Long id, Model model) {
        model.addAttribute("user", userService.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
        return "/user";
    }

}
