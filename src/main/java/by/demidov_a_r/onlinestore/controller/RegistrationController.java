package by.demidov_a_r.onlinestore.controller;


import by.demidov_a_r.onlinestore.dto.UserCreateEditDTO;
import by.demidov_a_r.onlinestore.dto.UserReadDTO;
import by.demidov_a_r.onlinestore.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
@SessionAttributes({"user"})
public class RegistrationController {

    private final UserService userService;

    @GetMapping("/registration")
    public String pageLoad() {
        return "/registration";
    }

    @PostMapping("/registration")
    public String registration(Model model, @ModelAttribute("createUser") @Validated UserCreateEditDTO userCreateEditDTO,
                               BindingResult bindingResult,
                               @RequestParam String confirmPassword) {

        if (!confirmPassword.equals(userCreateEditDTO.getPassword())) {
            bindingResult.addError(new ObjectError("user", "Пароли не совпадают!"));
        }
        if (bindingResult.hasErrors()) {
            model.addAttribute("createUser", userCreateEditDTO);
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "/registration";
        }
        UserReadDTO user = userService.register(userCreateEditDTO).orElse(null);
        model.addAttribute("user", user);
        return "redirect:/user/profile";
    }
}
