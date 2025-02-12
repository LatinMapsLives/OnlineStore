package by.demidov_a_r.onlinestore.model.entity;

import lombok.Getter;

@Getter
public enum Role {
    USER("Пользователь"), ADMIN("Администратор"), MODERATOR("Модератор");

    private final String displayName;

    private Role(String displayName) {
        this.displayName = displayName;
    }

}
