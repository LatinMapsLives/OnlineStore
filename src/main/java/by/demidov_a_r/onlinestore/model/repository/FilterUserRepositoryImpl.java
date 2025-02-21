package by.demidov_a_r.onlinestore.model.repository;

import by.demidov_a_r.onlinestore.dto.UserFilter;
import by.demidov_a_r.onlinestore.model.entity.User;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import java.util.List;

import static by.demidov_a_r.onlinestore.model.entity.QUser.user;


@RequiredArgsConstructor
public class FilterUserRepositoryImpl implements FilterUserRepository {

    private final EntityManager entityManager;

    @Override
    public List<User> findAllByFilter(UserFilter filter) {
        Predicate predicate = QPredicates.builder()
                .add(filter.firstName(), user.personalInfo.firstName::containsIgnoreCase)
                .add(filter.lastName(), user.personalInfo.lastName::containsIgnoreCase)
                .add(filter.email(), user.personalInfo.email::containsIgnoreCase)
                .build();

        return new JPAQuery<User>(entityManager)
                .select(user)
                .from(user)
                .where(predicate)
                .fetch()
                .stream()
                .map(tuple -> (User) tuple)
                .toList();
    }
}
