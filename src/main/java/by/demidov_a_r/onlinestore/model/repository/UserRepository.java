package by.demidov_a_r.onlinestore.model.repository;

import by.demidov_a_r.onlinestore.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    @Query("select u from User u where u.personalInfo.email = :email")
    Optional<User> findByEmail(String email);
    Optional<User> findByUsernameAndPassword(String username, String password);
    @Query("select u from User u where u.personalInfo.email = :email and u.password = :password")
    Optional<User> findByEmailAndPassword(String email, String password);

    @Query("select u from User u where u.personalInfo.firstName ilike %:firstname%")
    Optional<User> findByFirstnameLike(String firstname);
    @Query("select u from User u where u.personalInfo.lastName ilike %:lastname%")
    Optional<User> findByLastnameLike(String lastname);
    @Query("select u from User u where u.personalInfo.patronymic ilike %:patronymic%")
    Optional<User> findByPatronymicLike(String patronymic);



}
