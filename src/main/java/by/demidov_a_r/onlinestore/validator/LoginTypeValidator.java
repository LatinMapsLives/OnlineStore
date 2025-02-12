package by.demidov_a_r.onlinestore.validator;

import org.springframework.stereotype.Component;

@Component
public class LoginTypeValidator {

    public LoginType validateLoginType(String login) {
        if (login.matches("[0-9]")){

        }

        if (login.matches("@")){}

        return null;
    }

}
