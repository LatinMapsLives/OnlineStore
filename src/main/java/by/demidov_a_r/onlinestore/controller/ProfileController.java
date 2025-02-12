package by.demidov_a_r.onlinestore.controller;


import by.demidov_a_r.onlinestore.dto.UserReadDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@RequestMapping("/user")
public class ProfileController {

    @GetMapping("/profile")
    public String onPageLoad(@SessionAttribute("user") UserReadDTO user){
        if (user == null){
            return "redirect:/user/login";
        }
        return "/profile";
    }

}
