package by.demidov_a_r.onlinestore.controller;

import by.demidov_a_r.onlinestore.dto.UserReadDTO;
import by.demidov_a_r.onlinestore.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hello")
@RequiredArgsConstructor
public class HelloController {

    private final UserService userService;

    @GetMapping("/hello")
    private void hello(Model model) {
        model.addAttribute("userReadDTO", "Hello World");
    }
}
