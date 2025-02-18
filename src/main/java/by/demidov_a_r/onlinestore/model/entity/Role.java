package by.demidov_a_r.onlinestore.model.entity;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

@Getter
public enum Role implements GrantedAuthority {
    USER("Пользователь"), ADMIN("Администратор"), MODERATOR("Модератор");

    private final String displayName;

    Role(String displayName) {
        this.displayName = displayName;
    }


    @Override
    public String getAuthority() {
        return name();
    }
}
