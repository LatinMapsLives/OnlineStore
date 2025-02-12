package by.demidov_a_r.onlinestore.controller;

import by.demidov_a_r.onlinestore.dto.UserReadDTO;
import by.demidov_a_r.onlinestore.model.entity.User;
import by.demidov_a_r.onlinestore.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
@SessionAttributes({"user"})
public class LoginController {

    private final UserService userService;

    @GetMapping("/login")
    public String onPageLoad(Model model) {
        return "/login";
    }

    @PostMapping("/login")
    public String authenticate(Model model, String username, String password) {
        UserReadDTO user = userService.authenticate(username, password).orElseThrow(
                () -> new ErrorResponseException(HttpStatus.INTERNAL_SERVER_ERROR));
        model.addAttribute("user", user);
        return "/profile";
    }

}
