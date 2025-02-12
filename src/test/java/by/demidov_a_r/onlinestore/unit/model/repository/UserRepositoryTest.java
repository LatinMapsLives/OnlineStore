package by.demidov_a_r.onlinestore.unit.model.repository;

import by.demidov_a_r.onlinestore.OnlineStoreApplication;
import by.demidov_a_r.onlinestore.model.entity.Cart;
import by.demidov_a_r.onlinestore.model.entity.PersonalInfo;
import by.demidov_a_r.onlinestore.model.entity.Role;
import by.demidov_a_r.onlinestore.model.entity.User;
import by.demidov_a_r.onlinestore.model.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@ActiveProfiles("test")
@SpringBootTest(classes = {OnlineStoreApplication.class})
@RequiredArgsConstructor
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@Transactional
class UserRepositoryTest {

    private final UserRepository userRepository;
    private User user;


    @BeforeEach
    void setUp(){
        user = User.builder()
                .username("latinmapslives")
                .password("Artem12341234")
                .personalInfo(PersonalInfo.builder()
                        .firstName("Артём")
                        .lastName("Демидов")
                        .phone("+79192319714")
                        .email("latinmaps2@mail.ru")
                        .patronymic("Русланович")
                        .build())
                .role(Role.ADMIN)
                .build();
        Cart cart = Cart.builder().build();
        user.setCart(cart);

        userRepository.save(user);
    }

    @AfterEach
    void tearDown(){
        userRepository.deleteAll();
    }

    @Test
    public void save(){
        User newUser = userRepository.findById(user.getId()).orElse(null);

        Assertions.assertEquals(user, newUser);

        Assertions.assertNotNull(newUser.getCart());

    }

    @Test
    public void update(){
        user.setRole(Role.USER);

        userRepository.save(user);
        Assertions.assertEquals(user, userRepository.findById(user.getId()).orElse(null));
    }

    @Test
    public void delete(){
        userRepository.delete(user);

        Assertions.assertNull(userRepository.findById(user.getId()).orElse(null));

    }

    @Test
    public void findByUsername(){
        Assertions.assertNotNull(userRepository.findByUsername("latinmapslives").orElse(null));
    }

    @Test
    public void findByEmail(){
        Assertions.assertNotNull(userRepository.findByEmail("latinmaps2@mail.ru").orElse(null));
    }

    @Test
    void findByUsernameAndPassword() {
        Assertions.assertNotNull(userRepository.findByUsernameAndPassword("latinmapslives",
                        "Artem12341234").orElse(null));
    }

    @Test
    void findByEmailAndPassword() {
        Assertions.assertNotNull(userRepository.findByEmailAndPassword("latinmaps2@mail.ru",
                "Artem12341234").orElse(null));
    }

    @Test
    void findByFirstnameLike() {
        Assertions.assertEquals(1, userRepository.findByFirstnameLike("Р").size());
        Assertions.assertTrue(userRepository.findByFirstnameLike("D").isEmpty());
    }

    @Test
    void findByLastnameLike() {
        Assertions.assertEquals(1, userRepository.findByLastnameLike("Дем").size());
        Assertions.assertTrue(userRepository.findByLastnameLike("Деб").isEmpty());
    }

    @Test
    void findByPatronymicLike() {
        Assertions.assertEquals(1, userRepository.findByPatronymicLike("рус").size());
        Assertions.assertTrue(userRepository.findByPatronymicLike("Деб").isEmpty());
    }

    @Test
    public void findById(){
        Assertions.assertNotNull(userRepository.findById(user.getId()).orElse(null));
    }
}